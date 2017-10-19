var shoppingCart={
    data: function () {
        return {
            cartList: [],
            user: {},
            setup: {},
            couponSelected: {},
            usePoint:0,
            submitting:false
        }
    },
    template: '#shopping-cart-t',
    mixins: [hi.mixin],
    created: function () {
        var self = this;
        axios.all([hi.ax.get('/order/shoppingCart/list'),
            hi.ax.get('/sys/setup'),
            hi.ax.get('/user/info')]).then(axios.spread(function (r1, r2,r3) {
            //加载设置表
            if (!r2.data.entity) {
                hi.alert('系统正在维护中');
                return;
            }
            self.setup = r2.data.entity;
            //根据设置表，计算好购物车价格
            if (r1.data.entity) {
                var cartList = r1.data.entity;
                hi.cartService.computeCartList(cartList,self.setup);
                self.cartList = cartList;
            }
            if (r3.data.entity) {
                var user = r3.data.entity;
                hi.userService.computeUser(user,self.setup);
                self.user = user;
            }

        }));

    },
    computed: {
        //原价
        coursePriceSum:function(){
            var coursePriceSum=0;
            _.each(this.cartSelectedList,function(cart){
                coursePriceSum+= cart.product.videoCoursePriceN;
            });
            return coursePriceSum;
        },
        //折扣优惠
        diffPriceSum:function(){
            var sum=0;
            _.each(this.cartSelectedList,function(cart){
                sum+= cart.diffPrice;
            });
            return sum;
        },
        //组合优惠
        compoundDiffPriceSum:function(){
            var sum=0;
            _.each(this.cartSelectedList,function(cart){
                sum+= cart.compoundDiffPrice;
            });
            return sum;
        },
        //现金券最多可实际支付
        useAmountMax:function(){
            return this.coursePriceSum-this.diffPriceSum-this.compoundDiffPriceSum;
        },
        //现金券抵扣
        couponPrice:function(){
            var cp=0.0;
            if(this.empty(this.couponSelected)){
                return cp;
            }
            if(this.couponSelected.amountN>this.useAmountMax){
                cp=this.useAmountMax;
                this.usePoint=0;
            }else{
                cp=this.couponSelected.amountN;
            }
            return hi.round(cp,2);
        },
        //剩余积分
        pointLeft:function(){
            if(this.user.userPointsSum){
                var left=this.user.userPointsSum-this.usePoint;
                if(left<0){
                    left=0;
                }
                return left;
            }
            return 0;
        },
        //积分抵扣
        usePointPrice:function(){
            var rate=this.setup.userPointExchangeRate;
            rate=hi.round(rate,2);
            return rate*this.usePoint;
        },
        //已节省
        totalSave:function(){
            return this.diffPriceSum+this.compoundDiffPriceSum+this.couponPrice+this.usePointPrice;
        },
        //合计
        totalPrice:function(){
            return this.coursePriceSum-this.totalSave;
        },

        //已选择
        cartSelectedList: function () {
            return _.filter(this.cartList, {'checked': true});
        },
        //是否已全选
        allChecked: function () {
            var allChecked = true;
            _.each(this.cartList, function (c) {
                if (!c.checked) {
                    allChecked = false;
                    return false;
                }
            });
            return allChecked;
        }

    },
    methods: {
        //计算购物车价格
        compute:function(){
            hi.cartService.computeCartList(this.cartList,this.setup);
        },
        getProdIconSrc: function (pid) {
            return hi.root + "sys/file/img?type=img_product&id=" + pid;
        },
        check:function(cart){
            cart.checked=!cart.checked;
            this.compute();
        },
        //选择优惠券
        selectCoupon: function (coupon) {
            this.couponSelected = coupon;
            //this.compute();
        },
        //优惠券是否已选择
        hasSelected: function (couponId) {
            if (this.couponSelected.id === couponId) {
                return true;
            }
            return false;
        },
        //删除
        deleteCart: function (cartId) {
            var self = this;
            layer.confirm('确定要删除该商品吗？', {
                title: false,
                closeBtn: 0,
                btn: ['确定', '取消'] //按钮
            }, function () {
                var formData = new FormData();
                formData.append('cartId', cartId);
                hi.ax.post('/order/shoppingCart/remove', formData)
                    .then(function (r) {
                        if (r.data.status == 'fail') {
                            hi.alert(r.data.msg);
                            return;
                        }
                        hi.ax.get('/order/shoppingCart/list').then(function (r) {
                            if (r.data.entity) {
                                var cartList = r.data.entity;
                                hi.cartService.computeCartList(cartList,self.setup);
                                self.cartList = cartList;
                            }
                        });
                    });

                layer.close(layer.index);
            });
        },
        //全选
        checkAll: function () {
            if (this.allChecked) {
                _.each(this.cartList, function (c) {
                    c.checked = false;
                });
            } else {
                _.each(this.cartList, function (c) {
                    c.checked = true;
                });
            }
            this.compute();
        },
        //提交
        submit:function(){
            var self=this;
            if(self.submitting){
                return;
            }
            self.submitting=true;
            if(self.cartSelectedList.length==0){
                hi.alert('请选择产品');
                self.submitting=false;
                return;
            }
            if(hi.round(self.totalPrice,2)<0){
                hi.alert('实付金额不能小于0');
                self.submitting=false;
                return;
            }
            if(self.couponSelected.amountN>self.couponPrice&&self.usePointPrice>0){
                hi.alert('使用积分超额');
                self.submitting=false;
                return;
            }
            hi.ax.post('/order/shoppingCart/submit',{cartSelectedList:self.cartSelectedList,
                couponSelected:self.couponSelected,
                usePoint:self.usePoint,
                total:hi.round(self.totalPrice,2),
                diffPriceSum:hi.round(self.diffPriceSum,2),
                compoundDiffPriceSum:hi.round(self.compoundDiffPriceSum,2),
                couponPrice:self.couponPrice,
                usePointPrice:self.usePointPrice,
                totalSave:hi.round(self.totalSave,2),
                coursePriceSum:hi.round(self.coursePriceSum,2)

            }).then(function(r){
                if(r.data.status==='fail'){
                    hi.alert(r.data.msg);
                    self.submitting=false;
                    if(r.data.failType&&r.data.failType=='biz_auth_fail'){

                    }
                    return;
                }
                router.push('shopping-cart-order');

            });

        }
    },
    watch: {
        //使用积分
        usePoint:function(v,o){
            if(isNaN(v)){
                this.usePoint=0;
                return;
            }
            var point=parseInt(v);
            if(point<0){
                point=0;
            }else if(point>this.user.userPointsSum){
                point=this.user.userPointsSum;
            }
            var rate=this.setup.userPointExchangeRate;
            rate=hi.round(rate,2);
            var usePointRmb=hi.round(point*rate,2);
            var usePointRmbMax=hi.round(this.coursePriceSum-this.diffPriceSum-this.compoundDiffPriceSum-this.couponPrice,2);
            if(usePointRmb>usePointRmbMax){
                point=0;
            }
            this.usePoint=point;
        }

    }
}