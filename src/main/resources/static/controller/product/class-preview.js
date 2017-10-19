var appClassPreview = new Vue({
    el: '#class-preview',
    data: {
        unit:null,
        userProductId:'',
        userProductProgressReq:false,
        learningProgressStartReq:false,
        learningProgressEndReq:false,
        track:null,
        studyLogUpdateJob:null
    },
    created:function(){
        var self=this;
        var userProductId=hi.queryString.userProductId;
        self.userProductId=userProductId;

        var unitId=hi.queryString.unitId;
        hi.ax.get('/product/class/unit/load?type=&unitId='+unitId).then(function(r){
            if(r.data.status=='fail'){
                hi.alert(r.data.msg);
                return Promise.reject(r);
            }
            var unit=r.data.entity;
            if(unit){
                self.unit=unit;
            }
            return Promise.resolve(r);
        }).then(function(r){
            var track=new hi.UserClassTrack({userProductId:userProductId,productId:self.unit.productId,unitId:self.unit.id,studySection:'preview'});
            track.setStudyLog().then(function(){
                self.track=track;
                //self.track.setMediaTime(self.unit.mediaTime);
            });
            self.$nextTick(function(){
                $(".scroll").niceScroll({
                    cursorcolor: "#ccc",
                    cursoropacitymax: 1,
                    touchbehavior: false,
                    cursorwidth: "6px",
                    cursorborder: "0",
                    cursorborderradius: "50px",
                    autohidemode: false
                });
            });
        });

    },
    computed:{
        previewAudioSrc: function () {
            if(this.unit){
                return hi.root + "sys/file/audio?type=audio_unit_preview&id=" + this.unit.id;
            }
            return '';
        },
        previewImageSrc: function () {
            if(this.unit){
                return hi.root + "sys/file/img?type=img_unit_preview&id=" + this.unit.id;
            }
            return '';
        },
        previewVideoSrc: function () {
            if(this.unit){
                return hi.root + "sys/file/video?type=video_unit_preview&id=" + this.unit.id;
            }
            return '';
        }
    },
    methods: {

        backToUserProduct:function(){
            window.location.href=hi.root+"product/classCenter/#/user-product?id="+this.userProductId;
        },
        onAudioPlay:function(){
            var self=this;
            self.track.learningProgressStart();
            self.track.studyLogStart();
            //每个一段媒体时间，更新study_log
            clearInterval(self.studyLogUpdateJob);
            var studyLogUpdateJob=setInterval(function(){
                self.track.studyLogUpdate();
            },self.track.MEDIA_UPDATE_INTERVAL*1000);
            self.studyLogUpdateJob=studyLogUpdateJob;
        },
        onAudioPaused:function(){
            this.track.studyLogUpdate();
            clearInterval(this.studyLogUpdateJob);
        },
        onAudioEnded:function(){
            var self=this;
            self.track.learningProgressEnd().then(function(){
                self.track.studyLogEnd();
            });
            self.track.userProductStatusUpdate().then(function(){
                layer.confirm('恭喜您已完成课前听读!', {
                    title: false,
                    closeBtn: 0,
                    btn: ['返回课程', '取消']
                }, function () {
                    clearInterval(self.studyLogUpdateJob);
                    self.backToUserProduct();
                });
            });

        }



    },
    mounted:function(){
    },
    watch:{
    }

})