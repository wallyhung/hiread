hi.userService={
	computeUser:function(user,setup){
        if(user){
            var coupons=user.coupons;
            if(coupons){
                coupons=_.filter(coupons,function(o) {
                    return o.valid&&o.used==='0'&&parseInt(o.amount)>0;
                });
                coupons=_.sortBy(coupons,[function(c){
                    return -parseInt(c.amount);
                }]);
                _.each(coupons,function(coupon){
                    coupon.amountN=parseInt(coupon.amount);
                });
                user.coupons=coupons;
            }
            var userPoints=user.userPoints;
            if(userPoints){
                var userPointsSum=0;
                // userPoints=_.filter(userPoints,function(o) {
                //     return parseInt(o.point)>0;
                // });
                // userPoints=_.sortBy(userPoints,[function(c){
                //     return -parseInt(c.point);
                // }]);
                _.each(userPoints,function(o){
                    userPointsSum+=parseInt(o.point);
                    var rate=setup.userPointExchangeRate;
                    rate=hi.round(rate,2);
                    o.userPointPrice=rate*parseInt(o.point);
                });
                user.userPoints=userPoints;
                user.userPointsSum=userPointsSum;
            }
        }
    }
}

