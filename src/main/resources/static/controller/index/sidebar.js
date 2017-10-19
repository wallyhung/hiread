var sidebar = new Vue({
    el: '#sidebar',
    data: {
        cartList:[]
    },
    created:function(){
        var self=this;
        hi.ax.get('/order/shoppingCart/list')
            .then(function (r) {
                if (r.data.status == 'fail') {
                    hi.alert(r.data.msg);
                    return;
                }
                var cartList=r.data.entity;
                if(cartList){
                    self.cartList=cartList;
                }
            });

        hi.bus.$on('add-to-cart',function(pid,e){
            var formData = new FormData();
            formData.append('productId', pid);
            hi.ax.post('/order/shoppingCart/add', formData)
                .then(function (r) {
                    if (r.data.status == 'fail') {
                        hi.alert(r.data.msg);
                        return;
                    }
                    var cartList=r.data.entity;
                    self.cartList=cartList;

                    var flyer = $('<i class="fa fa-shopping-cart" style="font-size: 30px; color: red;"></i>');
                    flyer.fly({
                        start: {
                            left: e.clientX,
                            top: e.clientY
                        },
                        end: {
                            left: $("#badge").offset().left,
                            top: $("#badge").offset().top-$("body").scrollTop(),
                            width: 15,
                            height: 15
                        },
                        onEnd: function () {
                            flyer.hide('fast');
                        }
                    });
                    hi.bus.$emit('added-to-cart',pid);
                });



        });
    },
    methods: {

    }
})



