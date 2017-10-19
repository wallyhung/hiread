var appClassFreeTalk = new Vue({
    el: '#class-free-talk',
    mixins:[hi.mixin],
    data: {
        userProductId:'',
        type:'',
        productId:'',
        product:null,
        mediaConstraints: {audio: true},
        mediaRecorder: null,
        mp3Recorder:null,
        recording: false,
        audio:null,
        tryPlaying:false,
        tryPlayed:false,
        uploading:false,
        recorderInterval: null,
        recorderTime: 0,
        track:null,
        studyLogUpdateJob:null
    },
    created:function(){
        var self=this;
        var userProductId=hi.queryString.userProductId;
        self.userProductId=userProductId;
        var productId=hi.queryString.productId;
        self.productId=productId;
        var type=hi.queryString.type;
        self.type=type;
        hi.ax.get('/product/product/load?id='+productId).then(function(r){
            if(r.data.status=='fail'){
                console.log(r.data.msg);
                return;
            }
            self.product=r.data.entity;

            var track=new hi.UserClassTrack({userProductId:self.userProductId,
                productId:self.productId,
                unitId:'',
                studySection:self.type});
            track.setStudyLog().then(function(r){
                self.track=track;
            });
        });

    },
    computed:{
    },
    methods: {
        backToUserProduct:function(){
            window.location.href=hi.root+"product/classCenter/#/user-product?id="+this.userProductId;
        },
        //提交录音
        upload: function () {
            var self = this;
            if(this.recording||this.tryPlaying||this.audio==null||!this.tryPlayed||this.uploading){
                hi.alert("请确认已录音并且已试听过");
                return;
            }
            this.uploading=true;
            var loading=this.$loading({target: '.submit-opt'});
            var audio = this.audio;
            var file = new File([audio.blob], audio.name, {
                type: 'audio/mp3'
            });

            var fd = new FormData();
            fd.append('productId', self.productId);
            fd.append('type', self.type);

            hi.ax.post('/product/class/freeTalk', fd).then(function (r) {
                if (r.data.status == 'fail') {
                    hi.alert(r.data.msg);
                    this.uploading=false;
                    return Promise.reject(r);
                }
                return Promise.resolve(r);
            }).then(function (r) {
                var freeTalk = r.data.entity;
                fd = new FormData();
                fd.append("file", file);
                fd.append("type", "audio_free_talk");
                fd.append("id", freeTalk.id);
                hi.ax.post('sys/file/audio', fd).then(function (r) {
                    if (r.data.status == 'fail') {
                        hi.alert(r.data.msg);
                        this.uploading=false;
                        loading.close();
                        return Promise.reject(r);
                    }
                    loading.close();

                    self.track.studyLog.latestUpdate=moment().format('YYYY-MM-DD HH:mm:ss');
                    self.track.learningProgressEnd().then(function(){
                        self.track.studyLogEnd();
                    });
                    self.track.userProductStatusUpdate().then(function(){
                        if(r.data.status=='fail'){
                            console.log(r.data.msg);
                        }
                        self.audio=null;
                        self.uploading=false;
                        layer.confirm('恭喜您已完成Free Talk!', {
                            title: false,
                            closeBtn: 0,
                            btn: ['返回课程', '取消']
                        }, function () {
                            clearInterval(self.studyLogUpdateJob);
                            self.backToUserProduct();
                        });
                    });

                });
            });

        },
        //录音，结束录音
        toggleRecording: function () {
            var self=this;
            if(self.recording){
                self.mp3Recorder.stop();
                self.recording=false;
                self.recorderTime = 0;
                clearInterval(self.recorderInterval);

                self.track.studyLogUpdate();
                clearInterval(self.studyLogUpdateJob);
            }else{
                if(!self.mp3Recorder){
                    var mp3Recorder = new MP3Recorder({
                        //numChannels: 1,     //声道数,默认为1
                        //sampleRate: 8000,   //采样率,一般由设备提供,比如 48000
                        bitRate: 64,        //比特率,不要低于64,否则可能录制无声音（人声）
                        WORKER_PATH:hi.root+'js/mp3-recorder/recorder-worker.js',
                        //录音结束事件
                        complete: function (data, type) {
                            var blob = new Blob(data, { type: type });
                            var audio = {};
                            audio.blob = blob;
                            audio.objUrl = URL.createObjectURL(blob);
                            audio.name = moment().format('YYYYMMDDHHmmss') + ".mp3";
                            self.audio = audio;
                        }
                    });
                    if(!mp3Recorder.support){
                        layer.confirm("请您使用谷歌、火狐等现代浏览器进行录音。<br><a href='http://www.firefox.com.cn/' style='color:blue;' target='_blank'>点击下载最新浏览器</a>", {
                            title: false,
                            closeBtn: 0,
                            btn: ['暂时无法录音,继续后续课程', '取消']
                        }, function () {
                            var fd=new FormData();
                            fd.append('userProductId',self.userProductId);
                            fd.append('unitId','');
                            fd.append('status',self.type);
                            hi.ax.post('/product/class/userProduct/status',fd).then(function(r){
                                if(r.data.status=='fail'){
                                    console.log(r.data.msg);
                                }
                                self.backToUserProduct();
                                layer.close(layer.index);
                                return;
                            });
                        });
                    }
                    self.mp3Recorder=mp3Recorder;
                }
                self.mp3Recorder.start(function () {
                    self.recording=true;
                    self.tryPlayed=false;
                    self.tryPlaying=false;
                    self.audio=null;
                    clearInterval(self.recorderInterval);
                    self.recorderTime = 0;
                    self.recorderInterval = setInterval(function () {
                        if (self.recorderTime >= 60) {
                            self.mp3Recorder.stop();
                            self.recording = false;
                            clearInterval(self.recorderInterval);
                            self.recorderTime = 0;
                        } else {
                            self.recorderTime += 1;
                        }
                    }, 1000);

                    self.track.learningProgressStart();
                    self.track.studyLogStart();
                    //每个一段媒体时间，更新study_log
                    clearInterval(self.studyLogUpdateJob);
                    var studyLogUpdateJob=setInterval(function(){
                        self.track.studyLogUpdate();
                    },self.track.MEDIA_UPDATE_INTERVAL*1000);
                    self.studyLogUpdateJob=studyLogUpdateJob;
                }, function (e) {
                    console.log(e);
                });
            }

        },
        //试听，结束试听
        toggleTryPlaying:function(){
            if(!this.audio||this.uploading){
                return;
            }
            var tryAudio=document.querySelector("#tryAudio");
            if(this.tryPlaying){
                tryAudio.pause();
                this.tryPlaying=false;
            }else{
                tryAudio.play();
                this.tryPlaying=true;
                this.tryPlayed=true;
            }
        },
        tryEnded:function(){
            this.tryPlaying=false;
        }



    },
    mounted:function(){

    },
    watch:{
    }

})