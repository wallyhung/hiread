var shoppingCartOrder={
    data: function () {
        return {
            sub:{},
            orderId:'',
            channel:'wx',
            buyerMessage:'',
            selectedAddrId:'',
            submitting:false,

            addrList:[],
            addr:{
                id:'',
                name:'',
                mobile:'',
                provinceId:'',
                cityId:'',
                areaId:'',
                province:'',
                city:'',
                area:'',
                detailAddress:'',
                asDefault:'1',
                isDefault:true
            },
            addrSaving:false,
            addrMode:''
        }
    },
    template: '#shopping-cart-order-t',
    mixins: [hi.mixin],
    created: function () {
        var self=this;
        //加载订单信息（如果从支付页面跳回）
        var orderId=self.$route.query.orderId;
        if(orderId){
            hi.ax.get('/order/shoppingCartOrder/prodOrder?orderId='+orderId).then(function(r){
                if(r.data.status=='fail'){
                    hi.alert(r.data.msg);
                    return;
                }
                var order=r.data.entity;
                if(order){
                    self.orderId=order.id;
                    self.channel=order.channel;
                    self.buyerMessage=order.buyerMessage;
                    self.selectedAddrId=order.addressId;

                }
            });
        }
        //加载购物车信息
        hi.ax.get('/order/shoppingCartOrder/shoppingCartSubmit').then(function(r){
            var sub=r.data.entity;
            if(sub){
                self.sub=sub;
            }
        });
        self.loadAddrList();

        //地址验证
        var form = layui.form();
        form.verify({
            username: function(value, item){ //value：表单的值、item：表单的DOM对象
                if(!value){
                    return '用户名不能为空';
                }
                if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                    return '用户名格式不正确';
                }
            }
        });
        //保存或修改地址
        form.on('submit(saveAddr)', function(data){
            if(self.addrSaving){
                return false;
            }
            self.addrSaving=true;
            self.addr.province=hi.regionService.getProvince(self.addr.provinceId);
            self.addr.city=hi.regionService.getCity(self.addr.provinceId,self.addr.cityId);
            self.addr.area=hi.regionService.getArea(self.addr.cityId,self.addr.areaId);
            self.addr.asDefault=self.addr.isDefault?'1':'0';
            hi.ax.post('/order/shoppingCartOrder/addr/'+self.addrMode,self.addr).then(function(r){
                var sub=r.data.entity;
                if(r.data.status=='fail'){
                    layer.msg(r.data.msg, {
                        icon:5,
                        anim: 6
                    }, function(){
                        self.addrSaving=false;
                    });
                }else{
                    layer.msg(self.addrMode=='save'?"已保存":"修改成功", {
                        icon:6,
                        time:1500
                    }, function(){
                        self.addrSaving=false;
                        self.loadAddrList();
                        layer.closeAll();
                        $('.popup-content').hide();
                    });
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });


    },
    mounted:function(){
        var self=this;
        this.$nextTick(function(){
            //添加地址
            $(".order-add-addr").click(function () {
                self.addr.id='';
                self.addr.name='';
                self.addr.mobile='';
                self.addr.provinceId='';
                self.addr.cityId='';
                self.addr.areaId='';
                self.addr.province='';
                self.addr.city='';
                self.addr.area='';
                self.addr.detailAddress='';
                self.addr.asDefault='1';
                self.addr.isDefault=true;

                self.addrMode='save';
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    shadeClose: false,
                    area: ['auto', 'auto'],
                    content: $('.popup-content')

                })
            });
            //关闭地址
            $(".layui-layer-close").click(function () {
                $('.popup-content').hide();
            });
        })
    },
    computed: {
    },
    methods: {
        //加载地址
        loadAddrList:function(){
            var self=this;
            hi.ax.get('/order/shoppingCartOrder/addr/list').then(function(r){
                var addrList=r.data.entity;
                if(addrList&&addrList.length>0){
                    self.addrList=addrList;
                    self.selectedAddrId=addrList[0].id;
                }else{
                    self.addrList=[];
                    self.selectedAddrId='';
                }
            });
        },
        //选择地址
        selectAddr:function(id){
            this.selectedAddrId=id;
        },
        //删除地址
        deleteAddr:function(id){
            var self=this;
            layer.confirm('确定要删除该地址吗?', {title:'提示'}, function(index){
                var formData=new FormData();
                formData.append('addrId',id);
                hi.ax.post('/order/shoppingCartOrder/addr/delete',formData).then(function(r){
                    if(r.data.status=='success'){
                        self.loadAddrList();
                    }
                });

                layer.close(index);
            });

        },
        //修改地址
        updateAddr:function(addr){
            this.addr.id=addr.id;
            this.addr.name=addr.name;
            this.addr.mobile=addr.mobile;
            this.addr.provinceId=addr.provinceId;
            this.addr.cityId=addr.cityId;
            this.addr.areaId=addr.areaId;
            this.addr.province=addr.province;
            this.addr.city=addr.city;
            this.addr.area=addr.area;
            this.addr.detailAddress=addr.detailAddress;
            this.addr.asDefault=addr.asDefault;
            this.addr.isDefault=addr.asDefault=='1'?true:false;

            this.addrMode='update';
            layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                shadeClose: false,
                area: ['auto', 'auto'],
                content: $('.popup-content')

            })
        },
        getProdIconSrc: function (pid) {
            return hi.root + "sys/file/img?type=img_product&id=" + pid;
        },
        //提交
        submit:function(){
            var self=this;
            if(self.submitting){
                return;
            }
            if(self.selectedAddrId==''){
                hi.alert("请选择收货地址");
                return;
            }
            self.submitting=true;
            var fd=new FormData();
            fd.append('orderId',self.orderId);
            fd.append('channel',self.channel);
            fd.append('buyerMessage',self.buyerMessage);
            fd.append('addressId',self.selectedAddrId)
            hi.ax.post('/order/shoppingCartOrder/submit',fd).then(function(r){
                if(r.data.status=='fail'){
                    hi.alert(r.data.msg)
                    self.submitting=false;
                    return;
                }
                if(self.sub.noPayment){
                    router.push({ path: 'shopping-cart-payment-success', query: { orderId: r.data.entity }});
                }else{
                    router.push({ path: 'shopping-cart-payment', query: { orderId: r.data.entity }});
                }

            });
        }
    },
    watch: {
    }
}