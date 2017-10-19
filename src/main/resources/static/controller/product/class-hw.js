var appClassHW = new Vue({
    el: '#class-hw',
    data: {
        unit:null,
        unitId:'',
        practiseId:'',
        type:'',
        userProductId:'',
        track:null,
        shuffleP2:null,
        inited:false
    },
    created:function(){
        var self=this;
        var userProductId=hi.queryString.userProductId;
        self.userProductId=userProductId;
        var unitId=hi.queryString.unitId;
        self.unitId=unitId;
        var type=hi.queryString.type;
        self.type=type;
        self.init();
    },
    computed:{
    },
    methods: {
        init:function(practiseId){
            var self=this;

            hi.ax.get('/product/class/unit/load?type='+self.type+'&unitId='+self.unitId).then(function(r){
                if(r.data.status=='fail'){
                    hi.alert(r.data.msg);
                    return;
                }
                var unit=r.data.entity;
                if(unit){
                    var practises=unit.practises;
                    if(practises&&practises.length>0){

                        if(practises[0].questionType=='P2'){

                            var p2_arr=_.filter(practises,function(p){
                                return p.questionType=='P2'
                            });
                            var other_arr=_.filter(practises,function(p){
                                return p.questionType!='P2'
                            });
                            var new_arr=[];
                            if(self.shuffleP2==null){//如果单词卡片没有打乱过，则打乱后加入新数组
                                self.shuffleP2=_.shuffle(p2_arr);
                            }
                            _.each(self.shuffleP2,function(p){////如果单词卡片打乱过，则直接加入新数组
                                new_arr.push(p);
                            });
                            _.each(other_arr,function(p){
                                new_arr.push(p);
                            });
                            practises=new_arr;
                            unit.practises=new_arr;

                        }
                        if(practiseId){
                            self.practiseId=practiseId;
                        }else{
                            self.practiseId=practises[0].id;
                        }
                    }
                    self.unit=unit;
                    return Promise.resolve(r);
                }

            }).then(function(r){
                var track=new hi.UserClassTrack({userProductId:self.userProductId,
                    productId:self.unit.productId,
                    unitId:self.unit.id,
                    studySection:self.type});
                track.setStudyLog().then(function(r){
                    self.track=track;
                    self.track.practiseUserInit(self.practiseId);
                    if(!self.inited){
                        self.track.learningProgressStart();
                        self.track.studyLogStart(self.unit.practises);
                        self.inited=true;
                    }

                });

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