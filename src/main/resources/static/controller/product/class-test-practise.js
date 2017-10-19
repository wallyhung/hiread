var appTestPractise = new Vue({
    el: '#class-test-practise',
    data: {
        practiseId:'',
        type:'',
        userProductId:'',
        productId:'',
        practises:null,
        product:null,
        track:null,
        inited:false

    },
    created:function(){
        var self=this;
        var userProductId=hi.queryString.userProductId;
        self.userProductId=userProductId;
        var productId=hi.queryString.productId;
        self.productId=productId;
        var type=hi.queryString.type;
        self.type=type;
        self.init();
    },
    computed:{
    },
    methods: {
        init:function(practiseId){
            var self=this;
            hi.ax.get('/product/product/load?id='+self.productId).then(function(r){
                if(r.data.status=='fail'){
                    console.log(r.data.msg);
                    return;
                }
                self.product=r.data.entity;
            });
            hi.ax.get('/product/class/testPractises?type='+self.type+'&productId='+self.productId).then(function(r){
                if(r.data.status=='fail'){
                    hi.alert(r.data.msg);
                    return;
                }
                var practises=r.data.entity;
                if(practises&&practises.length>0){
                    self.practises=practises;
                    if(practiseId){
                        self.practiseId=practiseId;
                    }else{
                        self.practiseId=practises[0].id;
                    }

                    var track=new hi.UserClassTrack({userProductId:self.userProductId,
                        productId:self.productId,
                        unitId:'',
                        studySection:self.type});
                    track.setStudyLog().then(function(r){
                        self.track=track;
                        self.track.practiseUserInit(self.practiseId);
                        if(!self.inited){
                            self.track.learningProgressStart();
                            self.track.studyLogStart(practises);
                            self.inited=true;
                        }

                    });
                }

            });
        },
        backToUserProduct:function(){
            window.location.href=hi.root+"product/classCenter/#/user-product?id="+this.userProductId;
        },
        onPractiseIdChange:function(v){
            this.init(v);
        }

    },
    mounted:function(){

    },
    watch:{
    }

})