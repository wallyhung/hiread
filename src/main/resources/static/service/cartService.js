hi.cartService={
    computeCart:function(cart,setup){
        if(cart){
            var p=cart.product;
            hi.prodService.computeProduct(p);
            cart.checked=cart.hasOwnProperty('checked')?cart.checked:true;
            cart.singlePrice=p.videoDiscountPriceN==0?p.videoCoursePriceN:p.videoDiscountPriceN;
            cart.diffPrice=p.videoDiscountPriceN==0?0:p.videoCoursePriceN-p.videoDiscountPriceN;
            if(!cart.diffPrice||cart.diffPrice<0){
                cart.diffPrice=0;
            }
        }
    },
    computeCartList: function (cartList,setup) {
        var self=this;
        _.each(cartList, function (cart) {
            self.computeCart(cart);
        });
        var checkedList=_.filter(cartList,{'checked':true});
        _.each(cartList, function (cart) {
            var singlePrice = cart.singlePrice;
            if (checkedList.length > 1) {
                var rate = checkedList.length === 2 ? setup.doubleItemDiscount : setup.tripleItemDiscount;
                rate = hi.round(rate, 2);
                var compoundPrice = rate * singlePrice;
            }else{
                var compoundPrice = singlePrice;
            }
            cart.compoundPrice=compoundPrice;
            cart.compoundDiffPrice=cart.singlePrice-compoundPrice;
            if(!cart.compoundDiffPrice||cart.compoundDiffPrice<0){
                cart.compoundDiffPrice=0;
            }
        });
    },
}
