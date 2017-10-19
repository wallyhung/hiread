var app1 = new Vue({
    el: '#app-1',
    data: {
        userProductId:'',
        unit:null,
        unitId:'',
        practiseId:'',
        videoShow:true,
        practiseShow:false,
        sectionShow:false,
        gamiShow:false,
        hintShow:false,
        unitHintId:'',
        audioShow:false,
        unitAudioId:'',
        track:null,
        studyLogUpdateJob:null,
        studyLogUpdateAudioJob:null,
        trackGami:null
    },
    created:function(){
        var that=this;
        var userProductId=hi.queryString.userProductId;
        that.userProductId=userProductId;

        var unitId=hi.queryString.unitId;
        that.unitId=unitId;
        hi.ax.get('/user/login/refreshLoginUser',{}).then(function (r) {
            if(r.data.status=='fail'){
                window.location.href=hi.root+'user/login';
            }else{
                hi.ax.get('/product/class/unit/load?type=videoGami&unitId='+unitId).then(function(r){
                    if(r.data.status=='fail'){
                        hi.alert(r.data.msg);
                        return;
                    }
                    var unit=r.data.entity;
                    that.unit=unit;
                    var track=new hi.UserClassTrack({userProductId:userProductId,productId:that.unit.productId,unitId:that.unit.id,studySection:'video'});
                    track.setStudyLog().then(function(r){
                        track.setMediaTime(that.unit.mediaTime);
                        that.track=track;
                    });


                    var trackGami=new hi.UserClassTrack({userProductId:that.userProductId,
                        productId:that.unit.productId,
                        unitId:that.unit.id,
                        studySection:'videoGami'});
                    trackGami.setStudyLog().then(function(r){
                        that.trackGami=trackGami;
                    });
                });
            }
        });
    },
    computed:{
        practises:  function(){
            if(this.unit){
                return this.unit.practises;
            }
            return null;

        },
        practisesDone:function(){
            return _.filter(this.practises,['practiseDone',true]);
        },
        practiseUndone:function(){
            var self=this;
            return _.find(self.practises,function(p){
                var pid=p.id;
                var practiseDone=self.practisesDone.some(function(pd){
                    return pd.id==pid;
                });
                return !practiseDone;
            });
        },
        unitSections:function(){
            var self=this;
            if(this.unit){
                var unitSections=_.differenceBy(this.unit.unitSections,[{'computedSec':-1}] ,'computedSec');
                unitSections= _.sortBy(unitSections,['computedSec']);
                _.each(unitSections,function(us,index){
                    us.title=(index+1)+'. '+us.point+' ';
                    if(us.timeMin=='START'){
                        us.title+='(00:00)';
                    }else{
                        us.title+='('+us.timeMin+':'+us.timeSec+')';
                    }
                    if(self.practiseUndone&&self.practiseUndone.computedSec<us.computedSec){
                        us.lock=true;
                    }else{
                        us.lock=false;
                    }
                })

                return unitSections;
            }
            return null;
        },
        unitHints:function(){
            if(this.unit){
                return this.unit.unitHints;
            }
            return null;
        },
        unitAudios:function(){
            if(this.unit){
                return this.unit.unitAudios;
            }
            return null;
        }
    },
    methods: {
        onPractiseIdChange:function(v){
            var self=this;
            self.practiseId=v;
            self.trackGami.practiseUserInit(v);
            self.trackGami.studyLogStart(self.unit.practises);
            self.trackGami.learningProgressStart();
        },
        onPractiseSubmit:function(){
            var self=this;
            hi.ax.get('/product/class/unit/load?type=videoGami&unitId='+self.unit.id).then(function(r){
                if(r.data.status=='fail'){
                    hi.alert(r.data.msg);
                    return;
                }
                var unit=r.data.entity;
                var practises=unit.practises;
                if(practises&&practises.length>0) {
                    practises=_.differenceBy(unit.practises,[{'computedSec':-1}] ,'computedSec');
                    practises=_.sortBy(practises,['computedSec',function (p) {
                        var seq=99999;
                        try{
                            seq=parseInt(p.sequence);
                        }catch(e){}
                        return seq;
                    }]);
                    unit.practises=practises;
                }
                self.unit=unit;
            });
        },
        onVideoGamiFinish:function(){
            var self=this;
            self.trackGami.learningProgressEnd().then(function(){
                self.trackGami.studyLogEnd();
            });
        },
        onVideoShowChange:function(v){
            this.videoShow=v;
        },
        onPractiseShowChange:function(v){
            this.practiseShow=v;
        },
        onUnitHintIdChange:function(v){
            this.unitHintId=v;
        },
        onUnitAudioIdChange:function(id){
            this.unitAudioId=id;
            if(id!=''){
                this.audioShow=true;
            }
        },
        onVideoPlay:function(){
            var self=this;
            self.track.learningProgressStart();
            self.track.studyLogStart();
            clearInterval(self.studyLogUpdateJob);
            var studyLogUpdateJob=setInterval(function(){
                self.track.studyLogUpdate();
            },self.track.MEDIA_UPDATE_INTERVAL*1000);
            self.studyLogUpdateJob=studyLogUpdateJob;
            this.quickLinkChange('none');
        },
        onVideoEnded:function(){
            var self=this;
            self.track.learningProgressEnd().then(function(){
                self.track.studyLogEnd();
                self.track.userProductStatusUpdate().then(function(){
                    clearInterval(self.studyLogUpdateJob);
                    layer.confirm('恭喜您已完成英文原版导读课!', {
                        title: false,
                        closeBtn: 0,
                        btn: ['返回课程', '取消']
                    }, function () {
                        self.backToUserProduct();
                    });
                });
            });
        },
        onVideoPause:function(){
            if(this.track.studyLog.id){
                this.track.studyLogUpdate();
                clearInterval(this.studyLogUpdateJob);
            }
        },
        onAudioPlay:function(){
            var self=this;
            this.track.studyLogStart();
            clearInterval(self.studyLogUpdateAudioJob);
            var studyLogUpdateAudioJob=setInterval(function(){
                self.track.studyLogUpdate();
            },self.track.MEDIA_UPDATE_INTERVAL*1000);
            self.studyLogUpdateAudioJob=studyLogUpdateAudioJob;
        },
        onAudioPaused:function(){
            this.track.studyLogUpdate();
            clearInterval(this.studyLogUpdateAudioJob);
        },
        onAudioEnded:function(){
            this.track.studyLogUpdate();
            clearInterval(this.studyLogUpdateAudioJob);
        },
        quickLinkChange:function(v){
            if(v=='section'){
                this.sectionShow=!this.sectionShow;
                this.gamiShow=false;
                this.hintShow=false;
                this.audioShow=false;
            }else if(v=='gami'){
                this.sectionShow=false;
                this.gamiShow=!this.gamiShow;
                this.hintShow=false;
                this.audioShow=false;
            }else if(v=='hint'){
                this.sectionShow=false;
                this.gamiShow=false;
                this.hintShow=!this.hintShow;
                this.audioShow=false;
            }else if(v=='audio'){
                this.sectionShow=false;
                this.gamiShow=false;
                this.hintShow=false;
                this.audioShow=!this.audioShow;
            }else{
                this.sectionShow=false;
                this.gamiShow=false;
                this.hintShow=false;
                this.audioShow=false;
            }

        },
        appClick:function(e){
            var inQuickLinks=false;
            var ele=$(e.target);
            while(ele.prop('tagName')!='BODY'){
                if(ele.attr('class')=='mui-mbar-tabs'){
                    inQuickLinks=true;
                    break;
                }
                ele=ele.parent();
            }
            if(!inQuickLinks){
                this.quickLinkChange('none');
            }
        },
        sectionSelect:function(s){
            this.videoShow=true;
            this.practiseShow=false;
            this.quickLinkChange('none');
            hi.bus.$emit('audio-pause');
            hi.bus.$emit('section-select',s.computedSec);
        },
        practiseSelect:function(p){
            this.videoShow=false;
            this.practiseShow=true;
            this.quickLinkChange('none');
            hi.bus.$emit('video-pause');
            this.onPractiseIdChange(p.id);
        },
        resovleType:function(v){
            var result='';
            if(v=='G1-1'){
                result='True or False';
            }else if(v=='G2-1'){
                result='Choice Question';
            }else if(v=='G3-1'||v=='G3-2'){
                result='Fill In Blanks';
            }else if(v=='G4-1'){
                result='Make Sentence';
            }else if(v=='G6-1'){
                result='Timeline';
            }else if(v=='P1'){
                result='Opening Question';
            }else if(v=='P2'){
                result='Word Card';
            }else if(v=='P3'){
                result='Clikcing Pairs';
            }else if(v=='P4'){
                result='Moving Tabs';
            }
            return result;
        },
        getUnitAudioSrc:function(a){
            return hi.root + "sys/file/audio?type=audio_unit_audio&id=" + a.id;
        },
        backToUserProduct:function(){
            window.location.href=hi.root+"product/classCenter/#/user-product?id="+this.userProductId;
        }


    },
    mounted:function(){

    },
    watch:{
        unitHintId:function(v){
            if(v){this.hintShow=true;}
        }
    }

})