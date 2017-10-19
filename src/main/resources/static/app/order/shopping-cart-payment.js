var shoppingCartPayment={
    data: function () {
        return {
            prodOrder:{},
            codeUrl:'',
            fetchingCodeUrl:false,
            paymentSuccessFlag:false,
            lastPolling:null
        }
    },
    template: '#shopping-cart-payment-t',
    mixins: [hi.mixin],
    created: function () {
        this.fetchCodeUrl();
    },
    mounted:function(){
    },
    computed: {
    },
    methods: {

        fetchCodeUrl:function(){
            var self=this;
            var orderId=self.$route.query.orderId;
            self.fetchingCodeUrl=true;
            //请求订单
            hi.ax.get('/order/shoppingCartOrder/prodOrder?orderId='+orderId).then(function(r){
                if(r.data.status=='fail'){

                    hi.alert(r.data.msg);
                    self.fetchingCodeUrl=false;
                    return;
                }
                self.prodOrder=r.data.entity;

                //请求支付二维码
                var fd=new FormData();
                fd.append("orderId",orderId)
                hi.ax.post('/order/payment/prepay',fd).then(function(r){
                    if(r.data.status=='fail'){
                        hi.alert(r.data.msg);
                        self.fetchingCodeUrl=false;
                        return;
                    }
                    var codeUrl=r.data.entity;
                    self.codeUrl=codeUrl;

                    $("#qrcode").html("");
                    var qrcode = new QRCode(document.getElementById("qrcode"), {
                        width : 330,
                        height : 330
                    });
                    qrcode.makeCode(self.codeUrl);
                    self.fetchingCodeUrl=false;

                    if(self.lastPolling){
                        clearInterval(self.lastPolling);
                    }
                    self.poll();

                });

            });
        },
        poll:function(){
            var self=this;
            var polling=setInterval(function(){
                hi.ax.get('/order/shoppingCartOrder/prodOrder?orderId='+self.prodOrder.id).then(function(r){
                    if(r.data.status=="fail"){
                        hi.alert(r.data.msg);
                        clearInterval(polling);
                        return;
                    }
                    var order=r.data.entity;
                    if(!order){
                        hi.alert("订单为空");
                        clearInterval(polling);
                        return;
                    }

                    if(!self.paymentSuccessFlag){
                        var paid=order.paid;
                        var hasZeroAmountPay=order.hasZeroAmountPay;
                        if(paid=="0"&&hasZeroAmountPay=="1"){
                            hi.alert("支付异常，请您联系HiRead客服");
                            clearInterval(polling);
                            return;
                        }
                        if(paid=="1"){
                            self.paymentSuccessFlag=true;//防止polling时间过长，导致多次成功回调后多次执行后续动作
                            clearInterval(polling);
                            var orderId=self.prodOrder.id;
                            router.push({path:'shopping-cart-payment-success',query:{orderId:orderId}});
                        }

                    }

                })
            },1000);
            self.lastPolling=polling;
        },
        toCartOrder:function () {
            var self=this;
            if(!this.empty(self.prodOrder)){
                var orderId=self.prodOrder.id;
                router.push({path:'shopping-cart-order',query:{orderId:orderId}});
            }

        }
    },
    watch: {
    }
}