Vue.component('unit-read', {
    props: ['userProduct'],
    mixins: [hi.mixin],
    data: function () {
        return {
            readPractises: null,
            readPractise: null,
            readPractiseIndex: 0,
            mediaConstraints: {audio: true},
            mediaRecorder: null,
            mp3Recorder:null,
            recording: false,
            audio: null,
            tryPlaying: false,
            readPractising: false,
            uploading:false,
            userReads: null,
            recorderInterval: null,
            recorderTime: 0,
            tryPlayed:false,
            currentDay:moment().format('YYYY-MM-DD'),
        }
    },
    template: '#unit-read-t',
    computed: {
        calendarEvents:function(){
            var self=this;
            var events={};
            _.each(this.userReads,function(ur){
                var d=moment(ur.dateCreatedTime,'YYYY-MM-DD').format('YYYY-M-D');
                if(events[d]==undefined){
                    events[d]='已读';
                }
            });
            return events;
        },
        currentDayUserReads:function(){
            var self=this;
            return _.filter(this.userReads,function(ur){
                var d=moment(ur.dateCreatedTime,'YYYY-MM-DD').format('YYYY-MM-DD');
                return d==self.currentDay;
            });
        }
    },
    created: function () {
        this.fetchUserPractises();
    },
    mounted: function () {
    },
    methods: {
        fetchUserPractises: function () {
            var self = this;
            hi.ax.get('/product/readPractise/readPractises?userProductId=' + self.userProduct.id).then(function (r) {
                if (r.data.status == 'fail') {
                    hi.alert(r.data.msg);
                    return;
                }
                var readPractises = r.data.entity;
                if (!self.empty(readPractises)) {
                    var sorted = _.sortBy(readPractises, [function (rp) {
                        if (self.empty(rp.userReads)) {
                            return 0;
                        }
                        return rp.userReads.length;
                    }, function (rp) {
                        var charpter = 0;
                        try {
                            chapter = parseInt(rp.chapter);
                        } catch (e) {
                            chapter = 0;
                        }
                        return charpter;
                    }, , function (rp) {
                        var page = 0;
                        try {
                            page = parseInt(rp.page);
                        } catch (e) {
                            page = 0;
                        }
                        return page;
                    }, , function (rp) {
                        var paragraph = 0;
                        try {
                            paragraph = parseInt(rp.paragraph);
                        } catch (e) {
                            paragraph = 0;
                        }
                        return paragraph;
                    }]);
                    self.readPractises = sorted;
                    self.readPractise = sorted[self.readPractiseIndex];
                    self.fetchUserReads();
                }
            });

        },
        changeReadPractise: function () {
            var i = 0;
            if (this.readPractiseIndex < this.readPractises.length - 1) {
                i = this.readPractiseIndex + 1;
            }
            this.readPractiseIndex = i;
            this.readPractise = this.readPractises[i];

        },
        //日历选择
        selectCalendar:function(value){
            this.currentDay=moment(value.join('-'),'YYYY-MM-DD').format('YYYY-MM-DD');
            this.audiosInit();
        },
        //提交录音
        upload: function () {
            var self = this;
            if(this.recording||this.tryPlaying||this.audio==null||!this.tryPlayed||this.uploading){
                return;
            }
            this.uploading=true;
            var loading=this.$loading({target: '.reading-top-audio',text:'提交中'});
            var audio = this.audio;
            var file = new File([audio.blob], audio.name, {
                type: 'audio/mp3'
            });

            var fd = new FormData();
            fd.append('readPractiseId', self.readPractise.id);
            hi.ax.post('/product/readPractise/userRead', fd).then(function (r) {
                if (r.data.status == 'fail') {
                    hi.alert(r.data.msg);
                    self.uploading=false;
                    return Promise.reject(r);t
                }
                hi.bus.$emit('reward','recordingProcess',self.userProduct.productId,self.readPractise.unitId);
                return Promise.resolve(r);
            }).then(function (r) {
                var userRead = r.data.entity;
                fd = new FormData();
                fd.append("file", file);
                fd.append("type", "audio_user_read");
                fd.append("id", userRead.id);
                hi.ax.post('sys/file/audio', fd).then(function (r) {
                    if (r.data.status == 'fail') {
                        hi.alert(r.data.msg);
                        self.uploading=false;
                        loading.close();
                        return Promise.reject(r);
                    }
                    self.audio = null;
                    if (self.empty(self.readPractise.userReads)) {
                        self.readPractise.userReads = [];
                    }
                    self.readPractise.userReads.push(userRead);
                    self.fetchUserReads();
                    self.uploading=false;
                    loading.close();
                });
            });

        },
        //录音，结束录音
        toggleRecording: function () {
            var self=this;
            if(self.recording){
                self.mp3Recorder.stop();
                self.recording=false;
                clearInterval(self.recorderInterval);
                self.recorderTime = 0;
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
                            audio.name = self.readPractise.chapter +self.readPractise.page+ self.readPractise.paragraph   + ".mp3";
                            self.audio = audio;
                        }
                    });
                    if(!mp3Recorder.support){
                        hi.alert("请您使用谷歌、火狐等现代浏览器进行录音。<br><a href='http://www.firefox.com.cn/' style='color:blue;' target='_blank'>点击下载最新浏览器</a>");
                        return;
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
                }, function (e) {
                    console.log(e);
                });
            }

        },
        //试听，结束试听
        toggleTryPlaying: function () {
            if (!this.audio) {
                return;
            }
            var tryAudio = document.querySelector("#tryAudio");
            if (this.tryPlaying) {
                tryAudio.pause();
                this.tryPlaying = false;
            } else {
                tryAudio.play();
                this.tryPlaying = true;
                this.tryPlayed=true;
            }
        },
        tryEnded: function () {
            this.tryPlaying = false;
        },
        //示范朗读
        toggleReadPractising: function () {
            if (!this.readPractise.audio) {
                return;
            }
            var readPractiseAudio = document.querySelector("#readPractiseAudio");
            if (this.readPractising) {
                readPractiseAudio.pause();
                this.readPractising = false;
            } else {
                readPractiseAudio.play();
                this.readPractising = true;
            }
        },
        getReadPractiseAudioSrc: function () {
            return hi.root + 'sys/file/audio?type=audio_read_practise&id=' + this.readPractise.id;
        },
        //用户朗读列表
        fetchUserReads: function () {
            var self = this;
            hi.ax.get('product/readPractise/userReads?productId=' + self.userProduct.productId).then(function (r) {
                if (r.data.status == 'fail') {
                    hi.alert(r.data.msg);
                    return;
                }
                var reads = r.data.entity;
                _.each(reads, function (r) {
                    r.playing = false;
                    r.src = hi.root + 'sys/file/audio?type=audio_user_read&id=' + r.id;
                });
                self.userReads = reads;
                self.audiosInit();
            });
        },
        audiosInit:function(){
            var self=this;
            self.$nextTick(function(){
                $('.userReadAudio').each(function(){
                    var audio=$(this)[0];
                    audio.addEventListener('canplaythrough', function() {
                        var duration=audio.duration;
                        var time="";
                        var dur=Math.ceil(duration);
                        if(dur>60){
                            time="01:00";
                        }else if(dur<10){
                            time="00:0"+dur;
                        }else{
                            time="00:"+dur;
                        }
                        $(audio).parent().parent().find('.audio-main-end').html(time);
                        $(audio).parent().parent().find('.play').css('width','0');
                        $(audio).parent().parent().find('.slider').css('left','0');
                    }, false);
                });
                $('.userReadAudio').on('timeupdate',function(){
                    var audio=$(this)[0];
                    var duration=audio.duration;
                    var percent=(audio.currentTime/duration*100).toFixed(2)+'%';
                    $(audio).parent().parent().find('.play').css('width',percent);
                    $(audio).parent().parent().find('.slider').css('left',percent);
                });
            })
        },
        //用户朗读播放
        toggleUserReadAudio: function (ur, index) {
            var audio = document.querySelector("#ur" + index);
            if (ur.playing) {
                audio.pause();
                ur.playing = false;
            } else {
                audio.play();
                ur.playing = true;
            }
        },
        userReadEnded: function (ur) {
            ur.playing = false;
        },
        //用户朗读分享二维码
        showUserReadQrcode: function (ur) {
            var fd = new FormData();
            fd.append("userReadId", ur.id);
            hi.ax.post('/product/readPractise/userReadShare', fd).then(function (r) {
                if (r.data.status == 'fail') {
                    hi.alert(r.data.msg);
                    return Promise.reject(r);
                }
                return Promise.resolve(r);
            }).then(function (r) {
                var userRead = r.data.entity;
                hi.ax.get('/sys/config/url/base').then(function (r) {
                    var url_base = r.data.entity;
                    var codeUrl = url_base + 'product/readShare/auth?userReadId=' + userRead.userReadId + "&sign=" + userRead.sign;
                    console.log(codeUrl);
                    $("#qrcode").html("");
                    $("#qrcode").css('display', 'block');
                    var qrcode = new QRCode(document.getElementById("qrcode"), {
                        width: 330,
                        height: 330
                    });
                    qrcode.makeCode(codeUrl);
                    layer.open({
                        title: '请用手机微信扫描二维码',
                        type: 1,
                        content: $('#qrcode'),
                        cancel: function (index, layero) {
                            $("#qrcode").html("");
                            $("#qrcode").css('display', 'none');
                            layer.close(index);
                        }
                    });
                })

            });


        }
    }
})