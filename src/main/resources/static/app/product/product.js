var product= {
    data: function () {
        return {
            product:{},
            user:{},
            compoundCarts:[],
            setup:{}
        }
    },
    template: '#product-t',
    mixins: [hi.mixin],
    created:function(){
        var self=this;
        var productId=self.$route.query.productId;

        hi.ax.get('/product/product/load?id='+productId).then(function(r){
            if(r.data.entity){
                var product=r.data.entity;
                hi.prodService.computeProduct(product);
                self.product=product;
            }


            axios.all([hi.ax.get('/product/products/list'),
                hi.ax.get('/sys/setup')]).then(axios.spread(function (r1, r2) {
                //组合购物加载
                var cart={product:self.product,checked:true};
                self.compoundCarts.push(cart);
                if(r1.data.entity){
                    var products=r1.data.entity;
                    _.each(products,function(p){
                        if(p.id!=self.product.id&&self.compoundCarts.length<3){
                            var cartTemp={product:p,checked:false};
                            self.compoundCarts.push(cartTemp);
                        }
                    });
                }
                //加载设置表
                if (!r2.data.entity) {
                    hi.alert('系统正在维护中');
                    return;
                }
                var setup = r2.data.entity;
                self.setup=setup;
                //根据设置表，计算好组合购物价格
                self.compute(self.compoundCarts,self.setup);

            }));

        });
        axios.all([hi.ax.get('/user/info'),
            hi.ax.get('/sys/setup')]).then(axios.spread(function (r1, r2) {
            if (r2.data.entity) {
                self.setup=r2.data.entity;
            }
            if(!self.setup){
                hi.alert('系统正在维护中');
                return;
            }
            if (r1.data.entity) {
                var user = r1.data.entity;
                hi.userService.computeUser(user,self.setup);
                self.user = user;
            }

        }));
    },
    mounted:function(){

    },
    computed:{
        compoundCartsSelected:function(){
            var self=this;
            return _.filter(self.compoundCarts,{'checked':true});
        },
        productsSelected:function(){
            var self=this;
            var prods=[];
            _.each(self.compoundCartsSelected,function(c){
                prods.push(c.product);
            });
            return prods;
        },
        //原价
        coursePriceSum:function(){
            var coursePriceSum=0;
            _.each(this.compoundCartsSelected,function(cart){
                coursePriceSum+= cart.product.videoCoursePriceN;
            });
            return coursePriceSum;
        },
        //折扣优惠
        diffPriceSum:function(){
            var sum=0;
            _.each(this.compoundCartsSelected,function(cart){
                sum+= cart.diffPrice;
            });
            return sum;
        },
        //组合优惠
        compoundDiffPriceSum:function(){
            var sum=0;
            _.each(this.compoundCartsSelected,function(cart){
                sum+= cart.compoundDiffPrice;
            });
            return sum;
        },
        //已节省
        totalSave:function(){
            return this.diffPriceSum+this.compoundDiffPriceSum;
        },
        //合计
        totalPrice:function(){
            return this.coursePriceSum-this.totalSave;
        }
    },
    methods: {
        compute:function(){
            hi.cartService.computeCartList(this.compoundCarts,this.setup);
        },
        getProdIconSrc:function(pid){
            return hi.root + "sys/file/img?type=img_product&id=" + pid;
        },
        checkCompound:function(cart){
            cart.checked=!cart.checked;
            this.compute();
        }
    },
    watch:{
    }
}