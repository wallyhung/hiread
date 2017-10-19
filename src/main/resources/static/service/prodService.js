hi.prodService={
    computeProduct:function(product){
	    if(product){
            product.videoDiscountPriceN=hi.round(product.videoDiscountPrice||0,2);
            product.videoCoursePriceN=hi.round(product.videoCoursePrice||0,2);
        }

    }
}
