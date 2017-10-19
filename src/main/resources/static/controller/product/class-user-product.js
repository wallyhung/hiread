var classUserProduct = {
    data: function () {
        return {
            userProduct: {},
            product: {},
            showTab:'studyProgress',
            commentUnreadCount:0
        }
    },
    mixins: [hi.mixin],
    template: '#class-user-product-t',
    created: function () {
        var self = this;
        var id = self.$route.query.id;
        hi.ax.get('product/classCenter/userProduct?id=' + id).then(function (r) {
            if (r.data.status == 'fail') {
                hi.alert(r.data.msg);
                return;
            }
            var userProduct = r.data.entity;
            self.userProduct = r.data.entity;
            self.product = userProduct.product;
        });
    },
    methods: {
        getProductIconSrc:function(id){
            return hi.root + "sys/file/img?type=img_product&id=" + id;
        },
        continueClass:function(){
            var statusForUpdate=this.userProduct.statusForUpdate;
            if(statusForUpdate){
                if(statusForUpdate=='preTestPractise'||statusForUpdate=='postTestPractise'||statusForUpdate=='preTestFreeTalk'||statusForUpdate=='postTestFreeTalk'){
                    this.toClass(statusForUpdate);
                    return;
                }

                var unit=_.find(this.product.units,{'id':this.userProduct.unitIdForUpdate});
                this.toClass(statusForUpdate,unit);
            }
        },
        toClass: function (type,unit) {
            var self = this;
            if (type == 'preTestPractise' || type == 'postTestPractise') {
                if(type == 'postTestPractise'){
                    var postTestPractiseLocked=self.userProduct.postTestPractiseLocked;
                    if(postTestPractiseLocked){
                        hi.alert("尚未解锁");
                        return;
                    }
                }
                window.location.href = hi.root + 'product/classCenter/testPractise?type=' + type + '&productId=' + self.product.id + "&userProductId=" + self.userProduct.id;
                return;
            }
            if (type == 'preTestFreeTalk' || type == 'postTestFreeTalk') {
                if(type == 'preTestFreeTalk'){
                    var preTestFreeTalkLocked=self.userProduct.preTestFreeTalkLocked;
                    if(preTestFreeTalkLocked){
                        hi.alert("尚未解锁");
                        return;
                    }
                }
                if(type == 'postTestFreeTalk'){
                    var postTestFreeTalkLocked=self.userProduct.postTestFreeTalkLocked;
                    if(postTestFreeTalkLocked){
                        hi.alert("尚未解锁");
                        return;
                    }
                }
                window.location.href = hi.root + 'product/classCenter/freeTalk?type=' + type + '&productId=' + self.product.id + "&userProductId=" + self.userProduct.id;
                return;
            }
            var unitId=unit.id;
            if (type == 'video') {
                var videoLocked=unit.videoLocked;
                if(videoLocked){
                    hi.alert("尚未解锁");
                    return;
                }
                window.location.href = hi.root + 'product/class?unitId=' + unitId + "&userProductId=" + self.userProduct.id;
            } else if (type == 'preview') {
                var previewLocked=unit.previewLocked;
                if(previewLocked){
                    hi.alert("尚未解锁");
                    return;
                }
                window.location.href = hi.root + 'product/classCenter/preview?unitId=' + unitId + "&userProductId=" + self.userProduct.id;
            } else if (type == 'previewHW' || type == 'reviewHW') {
                if(type == 'previewHW'){
                    var previewHWLocked=unit.previewHWLocked;
                    if(previewHWLocked){
                        hi.alert("尚未解锁");
                        return;
                    }
                }
                if(type == 'reviewHW'){
                    var reviewHWLocked=unit.reviewHWLocked;
                    if(reviewHWLocked){
                        hi.alert("尚未解锁");
                        return;
                    }
                }
                window.location.href = hi.root + 'product/classCenter/hw?type=' + type + '&unitId=' + unitId + "&userProductId=" + self.userProduct.id;
            }else if (type == 'preTestPractise' || type == 'postTestPractise') {
                if(type == 'postTestPractise'){
                    var postTestPractiseLocked=userProduct.postTestPractiseLocked;
                    if(postTestPractiseLocked){
                        hi.alert("尚未解锁");
                        return;
                    }
                }
                window.location.href = hi.root + 'product/classCenter/testPractise?type=' + type + '&productId=' + self.product.id + "&userProductId=" + self.userProduct.id;
            }
        },
        commentUnread:function(count){
            this.commentUnreadCount=count;
        }
    }
}