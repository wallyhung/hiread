Vue.component('unit-practise', {
    props: ['practises', 'practiseId','type','track','productId'],
    data: function () {
        return {
            answer:null,
            startTime: moment().format('YYYY-MM-DD HH:mm:ss'),
            translationShow: false,
            useTranslation: '0',
            hintShow:true,
            useHelp:'0',
            submitted: false,
            correct: 'No',
            practiseUserId:'',
            finished:false,
            nice:null

        }
    },
    template: '#unit-practise-t',
    created: function () {
        this.init();
    },
    mounted: function () {
    },
    computed: {
        practise: function () {
            return _.cloneDeep(_.find(this.practises, ['id', this.practiseId]));
        },
        practiseGroup:function(){
            var group=_.filter(this.practises,{'computedSec':this.practise.computedSec});
            // if(group&&group.length>1){
            //     return _.sortBy(group,['sequence']);
            // }
            // if(group&&group.length==1&&
            //     (this.type=='previewHW'
            //     ||this.type=='reviewHW'
            //     ||this.type=='preTestPractise'
            //     ||this.type=='postTestPractise')){
            //     return group;
            // }
            return group;
        },
        practiseIndex:function(){
            if(this.practiseGroup){
                return _.findIndex(this.practiseGroup,{'id':this.practiseId});
            }
            return -1;

        },
        isFirstPractise:function(){
            if(this.practiseGroup==null){
                return true;
            }
            var first=_.first(this.practiseGroup);
            if(first.id==this.practiseId){
                return true;
            }
            return false;
        },
        isLastPractise:function(){
            if(this.practiseGroup==null){
                return true;
            }
            var last=_.last(this.practiseGroup);
            if(last.id==this.practiseId){
                return true;
            }
            return false;
        },
        practisePrev:function(){
            if(this.practiseGroup!=null){
                if(this.isFirstPractise){
                    return null;
                }else{
                    return this.practiseGroup[this.practiseIndex-1];
                }
            }
            return null;
        },
        practiseNext:function(){
            if(this.practiseGroup!=null){
                if(this.isLastPractise){
                    return null;
                }else{
                    return this.practiseGroup[this.practiseIndex+1];
                }
            }
            return null;
        },
        page:function(){
            var maxPagesPerRow=5;
            var currentPageIndex=this.practiseIndex;
            var pagesCount=this.practiseGroup.length;
            var currentRowIndex=Math.floor(currentPageIndex/maxPagesPerRow);
            var rowsCount=1;
            if(pagesCount>maxPagesPerRow){
                rowsCount=Math.ceil(pagesCount/maxPagesPerRow);
            }
            var pageInfo={};
            pageInfo.pageCount=pagesCount;
            pageInfo.entityCount=pagesCount;
            pageInfo.currentPage=currentPageIndex+1;
            pageInfo.maxPagePerRow=maxPagesPerRow;
            pageInfo.pageRowCount=rowsCount;
            pageInfo.currentPageRow=currentRowIndex+1;
            return pageInfo;

        },
        questionAudioSrc: function () {
            if(this.practise.questionAudio){
                return hi.root + "sys/file/audio?type=audio_practise_question&id=" + this.practiseId;
            }
            return '';
        },
        analysisVideoSrc: function () {
            if(this.practise.analysisVideo){
                return hi.root + "sys/file/video?type=video_practise_analysis&id=" + this.practiseId;
            }
            return '';
        },
        analysisAudioSrc: function () {
            if(this.practise.analysisAudio){
                return hi.root + "sys/file/audio?type=audio_practise_analysis&id=" + this.practiseId;
            }
            return '';
        },
        practiseOpts: function () {

            return this.practise.practiseOpts;
        },
        showTop:function(){
            return this.practise.questionType!='P3'&&this.practise.questionType!='P2';
        },
        questionImgClass:function(){
            var len=this.practise.questionImg.split(';').length;
            return "count"+len;
        }

    },
    methods: {
        init:function(){
            this.answer = null;
            this.translationShow = false;
            this.useTranslation = '0';
            this.useHelp = '0';
            this.submitted = false;
            this.correct = 'No';
            this.startTime=moment().format('YYYY-MM-DD HH:mm:ss');
        },
        practiseIdChange:function(v){
            var self=this;
            if(!self.practiseGroup){
                return;
            }
            if(!this.practiseGroup[self.practiseIndex].practiseDone){
                this.practiseGroup[self.practiseIndex].practiseDone=self.correct=='Yes'?true:false;
            }

            var index= _.findIndex(self.practiseGroup,{'id':v});
            if(index<0){
                return;
            }
            if(index>0){
                var p=self.practiseGroup[index-1];
                if(!p.practiseDone){
                    hi.alert("请按顺序答对题目");
                    return;
                }
            }
            //hi.bus.$emit('audio-pause');
            self.track.practiseUserUpdate().then(function(){
                self.$emit('practise-id-change',v);
            });

        },
        changeToPage:function(currentPage){
            var practise=this.practiseGroup[currentPage-1];
            this.practiseIdChange(practise.id);
        },
        translationToggle: function () {
            var self=this;
            self.translationShow = !self.translationShow;
            if(self.useTranslation=='0'){
                if(self.track){
                    self.track.practiseUserUseTrans();
                }
                self.useTranslation = '1';
            }
            if(self.translationShow){
                $('#hi-scroll').animate({
                    scrollTop: 0
                }, 100);
            }

        },
        help:function(){
            var self=this;
            if(self.practise.questionType=='P3'){//消消乐的提示特殊
                hi.bus.$emit('help');
            }else{
                hi.bus.$emit('hi-alert',self.practise.help,'提示');
            }
            if(self.useHelp=='0'){
                if(self.track){
                    self.track.practiseUserUseHelp();
                }
                self.useHelp = '1';
            }
        },
        onHelp:function(){
            var self=this;
            if(self.practise.questionType=='P3'){//消消乐的提示特殊
                if(self.useHelp=='0'){
                    if(self.track){
                        self.track.practiseUserUseHelp();
                    }
                    self.useHelp = '1';
                }
            }
        },
        onAnswer: function (answer) {
            if(this.submitted){
                return;
            }
            this.answer = answer;
        },
        validate:function(){
            var qtype= this.practise.questionType;
            if(qtype=='P2'||qtype=='P3'||qtype=='P4'){
                return true;
            }
            if(qtype=='P1'&&this.practise.practiseDone){
                hi.alert("该练习已完成,无法重复提交!");
                return false;
            }
            if(!this.answer){
                hi.alert("请完成练习!");
                return false;
            }
            if(qtype=='G1-1'||qtype=='G2-1'){
                var optIdChecked=this.answer[0];
                if(!optIdChecked){
                    hi.alert("请选择答案!");
                    return false;
                }
                return true;
            }
            if(qtype=='G6-1'){
                var emptyExist=false;
                _.each(this.answer,function(o){
                    if(o==""){
                        emptyExist=true;
                        return false;
                    }
                });
                if(emptyExist){
                    hi.alert("答案不完整!");
                    return false;
                }
                return true;
            }
            if(qtype=='G4-1'){
                if(this.answer.length!=this.practiseOpts.length){
                    hi.alert("答案不完整!");
                    return false;
                }
                return true;
            }
            if(qtype=='G3-1'||qtype=='G3-2'){
                var valid=true;
                _.each(this.answer,function(opt){
                    if(opt.isBlank=='Yes'){
                        _.each(opt.data,function(letter){
                            if(!letter.display){
                                if(letter.userInput==''){
                                    hi.alert("请填写完整!");
                                    valid=false;
                                    return false;
                                }
                            }
                        });
                        if(!valid){
                            return false;
                        }
                    }
                });
                return valid;
            }
            if(qtype=='P1'){//open question
                var a=this.answer[0];
                if(!a){
                    hi.alert("请回答!");
                    return false;
                }
                if(a.length>10000){
                    a=a.substr(0,10000);
                }
                return true;
            }

            return false;
        },
        submit: function () {
            var self=this;
            if(!this.validate()){
                return;
            }
            var answer = '';
            if (this.practise.questionType == 'G1-1') {
                answer=this.answer[0];
                if (this.practise.questionResult == answer) {
                    this.correct = 'Yes';
                } else {
                    this.correct = 'No';
                }
            }else if (this.practise.questionType == 'G2-1') {
                answer=this.answer[0];
                var optChecked = _.find(this.practiseOpts, ['id', answer]);
                if (optChecked && optChecked.correct == 'Yes') {
                    this.correct = 'Yes';
                } else {
                    this.correct = 'No';
                }
            }else if (this.practise.questionType == 'G6-1'||this.practise.questionType == 'G4-1') {
                answer=_.join(this.answer,';');
                var correctAnswers=_.sortBy(this.practiseOpts, [function(p) { return parseInt(p.sort); }]);
                var temp=[];
                _.each(correctAnswers,function(a){
                    temp.push(a.id);
                });
                var correctAnswer=_.join(temp,';');
                if(answer==correctAnswer){
                    this.correct = 'Yes';
                }else{
                    this.correct = 'No';
                }
            }else if(this.practise.questionType=='G3-1'||this.practise.questionType=='G3-2'){
                var correct='Yes';
                _.each(this.answer,function(opt){
                    if(opt.isBlank=='Yes'){
                        var word='';
                        _.each(opt.data,function(letter){
                            if(!letter.display){
                                if(self.practise.questionType=='G3-2'){//单词填空不区分大小写
                                    if(letter.userInput.toLowerCase()!=letter.value.toLowerCase()){
                                        correct='No';
                                    }
                                }else{
                                    if(letter.userInput!=letter.value){
                                        correct='No';
                                    }
                                }

                                word+=letter.userInput;

                            }

                        });
                        if(answer==""){
                            answer+=word;
                        }else{
                            answer+=";"+word;
                        }
                    }
                });
                this.correct=correct;
            }else if(this.practise.questionType == 'P2'||this.practise.questionType == 'P3'||this.practise.questionType == 'P4'){
                this.correct = 'Yes';
            }else if(this.practise.questionType == 'P1'){
                answer=this.answer[0];
                this.correct = 'Yes';
            }else {
                this.correct = 'No';
            }

            if(self.correct=='Yes'){
                self.playAudio('correct');
            }else{
                self.playAudio('wrong');
            }

            self.submitted = true;
            if((self.correct=='Yes'||self.practise.questionType=='G3-2')&&(self.practise.analysisType=='audio'||self.practise.analysisType=='video')){
                if(self.practise.analysisType=='audio'){
                    hi.bus.$emit('normal-audio-play');
                }else if(self.practise.analysisType=='video'){
                    hi.bus.$emit('normal-video-play');
                }
            }

            self.track.practiseUserSubmit(answer,self.correct).then(function(){
                self.$emit('practise-submit');
                if(self.correct=='Yes'){
                    hi.bus.$emit('reward','studyPoint',self.track.practiseUser.id);
                    hi.bus.$emit('reward','accurateProcess',self.track.practiseUser.id,self.track.practiseUser.productId);
                }

                if(self.correct=='No'&&(self.practise.questionType=='G1-1'
                    ||self.practise.questionType=='G2-1'
                    ||self.practise.questionType=='G3-1'
                    ||self.practise.questionType=='G4-1'
                    ||self.practise.questionType=='G6-1')){
                    var interval=setTimeout(function(){
                        self.redo();
                    },2000)
                };
                if(self.type!='video'&&self.correct=='Yes'&&self.isLastPractise){
                    self.finishPractise();
                    if(self.practise.analysisType=='text'){
                        setTimeout(function(){
                            self.finishPractiseConfirm();
                        },2000);
                    }
                }
            });
        },
        videoGamiHide:function(){
            this.$emit('video-show-change', true);
            this.$emit('practise-show-change', false);
            hi.bus.$emit('audio-pause');
            hi.bus.$emit('normal-video-pause');
            hi.bus.$emit('normal-audio-pause');
        },
        cancelVideoGami: function () {
            var self=this;
            self.track.practiseUserUpdate().then(function(){
                self.videoGamiHide();
                self.answer = null;
                self.submitted = false;
            });

        },
        continueVideo: function () {
            var self=this;
            self.track.practiseUserUpdate().then(function(){
                self.videoGamiHide();
                if(self.practise.questionType!='G3-1'&&self.practise.questionType!='G3-2'){
                    self.answer = null;
                }
                self.submitted = false;
                var pindex=_.findIndex(self.practises,{'id':self.practiseId});
                if(pindex==self.practises.length-1){
                    self.$emit('video-gami-finish');
                }
                hi.bus.$emit("practise-continue");
            });

        },
        redo:function(){
            var self=this;
            self.track.practiseUserUpdate().then(function(){
                if(self.practise.questionType=='G1-1'||self.practise.questionType=='G2-1'||self.practise.questionType=='G3-2'){
                    self.answer = null;
                }else if(self.practise.questionType=='G4-1'||self.practise.questionType=='G6-1'){
                    var answerKeep=[];
                    var correctAnswers=_.sortBy(self.practiseOpts, [function(p) { return parseInt(p.sort); }]);
                    for(var i=0;i<correctAnswers.length;i++){
                        var correctAnswerId=correctAnswers[i].id;
                        if(self.answer[i]==correctAnswerId){
                            answerKeep.push(correctAnswerId);
                        }else{
                            if(self.practise.questionType=='G4-1'){
                                break;
                            }else{
                                answerKeep.push("");
                            }
                        }
                    }
                    self.answer=answerKeep;
                }
                self.submitted = false;
                hi.bus.$emit('normal-video-pause');
                hi.bus.$emit('normal-audio-pause');
                hi.bus.$emit('practise-redo');
            });

        },
        reset:function(){
            this.answer = null;
            hi.bus.$emit('practise-reset');
        },
        prevPractise:function(){
            var self=this;
            if(self.practisePrev){
                self.practiseIdChange(self.practisePrev.id);
            }
        },
        nextPractise:function(){
            var self=this;
            if(self.practiseNext){
                self.practiseIdChange(self.practiseNext.id);
            }
        },
        finishPractiseConfirm:function(){
            var self=this;
            var type=self.type;
            var typeResolve='';
            if(type=='previewHW'){
                typeResolve= '课前单词健身房';
            }else if(type=='reviewHW'){
                typeResolve= '课后复习作业';
            }else if(type=='preTestPractise'){
                typeResolve= '课前测试习题';
            }else if(type=='postTestPractise'){
                typeResolve= '课后测试习题';
            }else if(type=='videoGami'){
                typeResolve= '英文原版导读课';
            }
            layer.confirm('恭喜您已完成'+typeResolve+'!', {
                title: false,
                closeBtn: 0,
                btn: ['返回课程', '取消']
            }, function () {
                self.$emit('back-to-user-product');
            });
        },
        analysisEnded:function(){
            if(this.isLastPractise){
                this.finishPractiseConfirm();
            }
        },
        finishPractise:function(){
            var self=this;
            if(self.finished){
                return;
            }
            self.track.practiseUserUpdate().then(function(){
                self.track.learningProgressEnd().then(function(){
                    self.track.studyLogEnd();
                    self.track.userProductStatusUpdate().then(function(){
                        self.finished=true;
                    });
                });
            });
        },
        questionImgSrc: function (name) {
            if(this.practise.questionImg){
                return hi.root + "sys/file/img?type=img_practise_question_img&id=" + this.practiseId+"&name="+name;
            }
            return '';
        },
        getAudioSrc: function (type) {
            return hi.root + "audio/"+type+".mp3";
        },
        playAudio:function(type){
            var self=this;
            self.$refs[type+'Audio'].$refs.audio.play();
        },
        enter:function(e){
            if(e.target.tagName=='TEXTAREA'){
                return;
            }
            if(this.$refs.submitBtn){
                this.$refs.submitBtn.click();
            }else if(this.$refs.redoBtn){
                this.$refs.redoBtn.click();
            }else if(this.$refs.nextPractiseBtn){
                this.$refs.nextPractiseBtn.click();
            }else if(this.$refs.continueVideoBtn){
                this.$refs.continueVideoBtn.click();
            }else if(this.$refs.finishPractiseBtn){
                this.$refs.finishPractiseBtn.click();
            }
        },
        // niceScroll:function(){
        //     var self=this;
        //     console.log('nice init');//TO DELETE
        //     self.$nextTick(function(){
        //         if(self.nice==null){
        //             self.nice=$("#hi-scroll").niceScroll({
        //                 cursorcolor: "rgba(0,0,0,.3)",
        //                 cursoropacitymax: 1,
        //                 touchbehavior: false,
        //                 cursorwidth: "1px",
        //                 cursorborder: "0",
        //                 cursorborderradius: "0",
        //                 scrollspeed: 0, // scrolling speed
        //                 hwacceleration: true,
        //                 smoothscroll: true, // scroll with ease movement
        //             });
        //         }else{
        //             console.log('resize');//TO DELETE
        //             setTimeout(function(){
        //                 self.nice.resize();
        //             },10);
        //
        //         }
        //     });
        // },
    },
    watch: {
        'practiseId': function (v) {
            if (v) {
                this.init();
                $('#hi-scroll').animate({
                    scrollTop: 0
                }, 100);
            }
        },
        'submitted':function(v){
            var self=this;
            if(v&&self.correct=='Yes'){
                $('#hi-scroll').animate({
                    scrollTop: 9999
                }, 100);

            }
        }
    }
})