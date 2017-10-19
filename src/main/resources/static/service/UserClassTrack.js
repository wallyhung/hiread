hi.UserClassTrack=function(classInfo){
    this.PRACTISE_INACTIVE_MAX=30;
    this.PRACTISE_INACTIVE_BACK=30;
    this.MEDIA_UPDATE_INTERVAL=10;

    this.userProductId='';
    this.productId='';
    this.unitId='';
    this.studySection='';

    this.studyLog=null;
    this.studyLogs=null;
    this.studyLogStarted=false;

    this.userProductStatusUpdated=false;
    this.learningProgressStarted=false;
    this.learningProgressEnded=false;

    this.practiseUser=null;

    if(classInfo.userProductId){
        this.userProductId=classInfo.userProductId;
    }
    if(classInfo.productId){
        this.productId=classInfo.productId;
    }
    if(classInfo.unitId){
        this.unitId=classInfo.unitId;
    }
    if(classInfo.studySection){
        this.studySection=classInfo.studySection;
    }
    /*
    class-preview.js,class.js调用
    设置媒体时间
     */
    this.setMediaTime=function(time){
        if(this.studyLog){
            this.studyLog.mediaTime=time;
        }
    }
    /*
    user study log
     */
    this.getUnCompletedLog=function(){
        return _.find(this.studyLogs,{'completed':'0'});
    };
    this.getLatestSn=function(){
        var sort=_.sortBy(this.studyLogs,function(l){
            return -parseInt(l.sn);
        });
        return sort[0].sn;
    };
    this.getInitStudyLog=function(){
        var studyLog={};
        var m=moment();
        studyLog.studyDate=m.format('YYYY-MM-DD');
        studyLog.productId=this.productId;
        studyLog.unitId=this.unitId;
        studyLog.studySection=this.studySection;
        studyLog.sn="1";
        studyLog.startTime=m.format('YYYY-MM-DD HH:mm:ss');
        studyLog.completed="0";
        studyLog.times="0";
        if(this.studySection=='previewHW'
            ||this.studySection=='reviewHW'
            ||this.studySection=='videoGami'
            ||this.studySection=='preTestPractise'
            ||this.studySection=='postTestPractise'){
            studyLog.answerTime="0";
            studyLog.analysisTime="0";
        }
        studyLog.latestUpdate=m.format('YYYY-MM-DD HH:mm:ss');
        return studyLog;
    };
    this.setStudyLog=function(){
        var self=this;
        var fd=new FormData();
        fd.append('productId',self.productId);
        fd.append('unitId',self.unitId);
        fd.append('studySection',self.studySection);
        return hi.ax.post('/user/study/log/list',fd).then(function(r){
            if(r.data.status=='fail'){
                return Promise.reject(r);
            }
            self.studyLogs=r.data.entity;
            var m=moment();
            if(self.studyLogs&&self.studyLogs.length>0){
                var unCompletedLog=self.getUnCompletedLog();
                //如果有未完成的当日log
                if(unCompletedLog){
                    unCompletedLog.latestUpdate=m.format('YYYY-MM-DD HH:mm:ss');
                    self.studyLog= unCompletedLog;
                }
                //没有则new一个，并且sn轮次号加1
                else{
                    var log=self.getInitStudyLog();
                    var latestSn=self.getLatestSn();
                    var sn=parseInt(latestSn)+1;
                    log.sn=sn+'';
                    self.studyLog=log;
                }

            }else{
                self.studyLog=self.getInitStudyLog();
            }
            return Promise.resolve(r);
        });
    };
    /*
    学习开始后调用
     */
    this.studyLogStart=function(practises){
        var self=this;
        self.studyLog.latestUpdate=moment().format('YYYY-MM-DD HH:mm:ss');
        if(!self.studyLogStarted){
            if(!self.studyLog.startTime){
                self.studyLog.startTime=moment().format('YYYY-MM-DD HH:mm:ss');
            }
            if(practises&&practises.length>0){
                self.studyLog.practiseNum=practises.length;
                self.studyLog.completeNum=self.studyLog.completeNum?self.studyLog.completeNum:'0';
                self.studyLog.correctNum=self.studyLog.correctNum?self.studyLog.correctNum:'0';
            }
            hi.ax.post('/user/study/log/update',self.studyLog).then(function(r){
                if(r.data.status=='fail'){
                    console.log(r.data.msg);
                }
                self.setStudyLog();

                self.learningProgressTotalTimeUpdate();
            });
            self.studyLogStarted=true;
        }
    },
    /*
     学习结束后调用
     */
    this.studyLogEnd=function(){
        this.studyLog.completed="1";
        this.studyLog.endTime=moment().format('YYYY-MM-DD HH:mm:ss');
        if(this.studyLog.id){
            if(this.studyLog.studySection=='previewHW'
                ||this.studyLog.studySection=='reviewHW'
                ||this.studyLog.studySection=='videoGami'
                ||this.studyLog.studySection=='preTestPractise'
                ||this.studyLog.studySection=='postTestPractise'){
                this.studyLogUpdateForPractise('analysis',0);
            }else{
                this.studyLogUpdate();
            }
        }else{
            this.studyLogStart();
        }
        this.studyLogStarted=false;
        this.learningProgressTotalTimeUpdate();

    };
    /*
    class-preview.js,class.js调用
    媒体学习过程中，更新学习时间到study_log
     */
    this.studyLogUpdate=function(){
        var self=this;
        console.log('study log update');
        var latestUpdate=self.studyLog.latestUpdate;
        var now=moment().format('YYYY-MM-DD HH:mm:ss');
        var diff = moment(now).diff(latestUpdate);
        var diffSec = Math.ceil(diff / 1000);
        var times=self.studyLog.times;
        times=(parseInt(times)+diffSec)+"";
        self.studyLog.times=times;
        hi.ax.post('/user/study/log/update',self.studyLog).then(function(r){
            if(r.data.status=='fail'){
                console.log(r.data.msg);
            }
            self.studyLog.latestUpdate=now;
            if(self.studyLog.completed=='1'){
                self.setStudyLog();
            }

            self.learningProgressTotalTimeUpdate();
        });
    };
    /*
    private
    在题目提交或看完解析后的点击，更新答题或解析时间到study_log
     */
    this.studyLogUpdateForPractise=function(answerStatus,diffSec,practises){
        var self=this;
        if(answerStatus=='answer'){
            var answerTime=self.studyLog.answerTime;
            answerTime=(parseInt(answerTime)+diffSec)+"";
            self.studyLog.answerTime=answerTime;
        }else if(answerStatus=='analysis'){
            var analysisTime=self.studyLog.analysisTime;
            analysisTime=(parseInt(analysisTime)+diffSec)+"";
            self.studyLog.analysisTime=analysisTime;
        }
        if(practises&&practises.length>0){
            self.studyLog.practiseNum=practises.length;
            self.studyLog.completeNum=_.filter(practises,{completedForStudyLog:true}).length;
            self.studyLog.correctNum=_.filter(practises,{correctForStudyLog:true}).length;
        }
        var times=self.studyLog.times;
        times=(parseInt(times)+diffSec)+"";
        self.studyLog.times=times;

        hi.ax.post('/user/study/log/update',self.studyLog).then(function(r){
            if(r.data.status=='fail'){
                console.log(r.data.msg);
            }
            self.studyLog.latestUpdate=moment().format('YYYY-MM-DD HH:mm:ss');
            if(self.studyLog.completed=='1'){
                self.setStudyLog();
            }

            self.learningProgressTotalTimeUpdate();
        });
    };

    /*
    user product
     */
    /*
    在一个section finish后调用,class-test-practise.js调用
    更新user_product的status
     */
    this.userProductStatusUpdate=function(){
        var self=this;
        console.log('status update..');
        if(!self.userProductStatusUpdated){
            var fd=new FormData();
            fd.append('userProductId',self.userProductId);
            fd.append('unitId',self.unitId);
            fd.append('status',self.studySection);
            return hi.ax.post('/product/class/userProduct/status',fd).then(function(r){
                if(r.data.status=='fail'){
                    console.log(r.data.msg);
                }else{
                    hi.bus.$emit('reward','unitCompleteProcess',self.productId,self.unitId,self.studySection);
                }
                self.userProductStatusUpdated=true;
                return Promise.resolve();
            });

        }
        return Promise.resolve();
    }
    /*
    learning process
     */
    /*
     在一个section start后调用
     插入learning process
     */
    this.learningProgressStart=function(){
        var self=this;
        if(!self.learningProgressStarted){
            var fd=new FormData();
            fd.append('productId',self.productId);
            fd.append('unitId',self.unitId);
            fd.append('type',self.studySection);
            hi.ax.post('/product/class/learningProgress/start',fd).then(function(r){
                if(r.data.status=='fail'){
                    console.log(r.data.msg);
                }
            });
            self.learningProgressStarted=true;
        }
    };
    /*
     在一个section end后调用
     更新learning process
     */
    this.learningProgressEnd=function(){
        var self=this;
        if(!self.learningProgressEnded){
            var fd=new FormData();
            fd.append('productId',self.productId);
            fd.append('unitId',self.unitId);
            fd.append('type',self.studySection);
            return hi.ax.post('/product/class/learningProgress/end',fd).then(function(r){
                self.learningProgressEnded=true;
                return Promise.resolve();
            });
        }else{
            return Promise.resolve();
        }

    };
    /*
    更新learning_progress的totalTime,由相关study_log计算得出
     */
    this.learningProgressTotalTimeUpdate=function(){
        var self=this;
        var fd=new FormData();
        fd.append('productId',self.productId);
        fd.append('unitId',self.unitId);
        fd.append('type',self.studySection);
        hi.ax.post('/product/class/learningProgress/updateTotalTime',fd).then(function(r){
            if(r.data.status=='fail'){
                console.log(r.data.msg);
            }
            hi.bus.$emit('reward','studyTimeProcess',self.productId);
        });
    }
    /*
    practise user
     */
    /*
    private
    在答题或查看解析时处理长时间不活动事件
     */
    this.refreshLatestTime=function(){
        var now=moment().format('YYYY-MM-DD HH:mm:ss');
        var latestActiveTime=this.practiseUser.latestActiveTime;
        var diff=moment(now).diff(latestActiveTime);
        if(diff>this.PRACTISE_INACTIVE_MAX*1000){
            if(this.practiseUser.answerStatus=='answer'){
                var latestAnswerTime=this.practiseUser.latestAnswerTime;
                this.practiseUser.latestAnswerTime=moment(now)
                    .subtract(this.PRACTISE_INACTIVE_BACK,'seconds')
                    .format('YYYY-MM-DD HH:mm:ss');
            }else if(this.practiseUser.answerStatus=='analysis'){
                var latestAnalysisTime=this.practiseUser.latestAnalysisTime;
                this.practiseUser.latestAnalysisTime=moment(now)
                    .subtract(this.PRACTISE_INACTIVE_BACK,'seconds')
                    .format('YYYY-MM-DD HH:mm:ss');
            }
        }
        this.practiseUser.latestActiveTime=now;
    }
    /*
     private
     最新做题时间重置
     */
    this.refreshPractiseUserTime=function(pu,now){
        pu.latestActiveTime=now;
        pu.latestAnswerTime=now;
        pu.latestAnalysisTime=now;
        pu.activeTime=now;
        $('body').unbind('mousemove.track');
        $('body').unbind('click.track');
        $('body').bind('mousemove.track',this.refreshLatestTime.bind(this));
        $('body').bind('click.track',this.refreshLatestTime.bind(this));
    }
    /*
    class-test-practise.js,class-hw.js,class.js调用
    每次进入题目，如果有未回答正确的记录，则载入，否则初始化practise_user
    最新做题时间重置，监听处理长时间不活动事件
     */
    this.practiseUserInit=function(practiseId){
        var self=this;
        var now=moment().format('YYYY-MM-DD HH:mm:ss');
        hi.ax.get('/product/class/practiseUser/notCorrect?practiseId='+practiseId+"&type="+self.studySection).then(function(r){
            var pu=r.data.entity
            if(pu){
                pu.answerStatus='answer';
                self.practiseUser=pu;
                self.refreshPractiseUserTime(pu,now);
            }else{
                pu={
                    productId:self.productId,
                    unitId:self.unitId,
                    practiseId:practiseId,
                    type:self.studySection,
                    startTime:now,
                    completed:"0",
                    correct:"No",
                    useHelp:"0",
                    useTranslation:"0",
                    studyPoint:"0",
                    totalTime:"0",
                    tryTimes:"0",
                    answerTime:"0",
                    analysisTime:"0",
                    startEndDiff:"0",
                    userStudyLogId:''
                };
                hi.ax.post('/product/class/practiseUser/init', pu)
                    .then(function (r) {
                        if (r.data.status == 'fail') {
                            console.log(r.data.msg);
                            return;
                        }
                        var pu=r.data.entity;
                        self.refreshPractiseUserTime(pu,now);
                        pu.answerStatus='answer';
                        self.practiseUser=pu;
                    });
            }
        });


    };
    this.practiseUserUseHelp=function(){
        var self=this;
        var fd = new FormData();
        fd.append('practiseUserId', self.practiseUser.id);
        fd.append('help', true);
        return hi.ax.post('/product/class/practiseUser/use', fd)
            .then(function (r) {
                if (r.data.status == 'fail') {
                    console.log(r.data.msg);
                    return Promise.reject(r);
                }
                self.practiseUser.useHelp="1";
                return Promise.resolve(r);
            });
    };
    this.practiseUserUseTrans=function(){
        var self=this;
        var fd = new FormData();
        fd.append('practiseUserId', self.practiseUser.id);
        fd.append('trans', true);
        return hi.ax.post('/product/class/practiseUser/use', fd)
            .then(function (r) {
                if (r.data.status == 'fail') {
                    console.log(r.data.msg);
                    return Promise.reject(r);
                }
                self.practiseUser.useTranslation="1";
                return Promise.resolve(r);
            });
    };
    /*
     unit-practise.js调用
    题目提交,计算答题时间
    对于上课的题目记录答题时间到study_log
     */
    this.practiseUserSubmit=function(answer,correct){
        var self=this;
        self.refreshLatestTime();
        var pu=self.practiseUser;
        pu.answer=answer;
        pu.correct=correct;
        if(correct=="Yes"){
            pu.completed="1";
        }else{
            pu.completed="0";
        }
        var now=moment().format('YYYY-MM-DD HH:mm:ss');
        var diffLatestAnswer = moment(now).diff(pu.latestAnswerTime);
        var diffLatestAnswerSec = Math.ceil(diffLatestAnswer / 1000);
        var totalTime=pu.totalTime;
        var answerTime=pu.answerTime;
        try{
            totalTime=(parseInt(pu.totalTime)+diffLatestAnswerSec)+"";
            answerTime=(parseInt(pu.answerTime)+diffLatestAnswerSec)+"";
        }catch(e){}
        if(totalTime){
            pu.totalTime=totalTime;
        }
        if(answerTime){
            pu.answerTime=answerTime;
        }

        var diffStartTime=moment(now).diff(pu.startTime);
        var diffStartTimeSec=Math.ceil(diffStartTime / 1000);
        pu.endTime=now;
        pu.startEndDiff=diffStartTimeSec;

        var tryTimes=pu.tryTimes;
        try{
            tryTimes=(parseInt(pu.tryTimes)+1)+"";
        }catch(e){}
        pu.tryTimes=tryTimes;
        pu.userStudyLogId = self.studyLog.id;
        return hi.ax.post('/product/class/practiseUser/update', pu)
            .then(function (r) {
                if (r.data.status == 'fail') {
                    hi.alert(r.data.msg);
                    return Promise.reject(r);
                }
                var practises=r.data.entity;
                if(practises&&practises.length>0){
                    self.studyLogUpdateForPractise('answer',diffLatestAnswerSec,practises);
                }else{
                    self.studyLogUpdateForPractise('answer',diffLatestAnswerSec);
                }
                self.refreshPractiseUserTime(pu,now);
                pu.answerStatus='analysis';
                return Promise.resolve(r);
            })
    };
    /*
    unit-practise.js调用
    切换到下一题前更新，点击重置(再试一下)更新，点击完成更新
    对于上课的题目记录解析时间到study_log
     */
    this.practiseUserUpdate=function(){
        var self=this;
        self.refreshLatestTime();
        var pu=self.practiseUser;
        var now=moment().format('YYYY-MM-DD HH:mm:ss');
        if(pu.answerStatus=='answer'){
            var diffLatestAnswer = moment(now).diff(pu.latestAnswerTime);
            var diffLatestAnswerSec = Math.ceil(diffLatestAnswer / 1000);
            var totalTime=pu.totalTime;
            var answerTime=pu.analysisTime;
            try{
                totalTime=(parseInt(pu.totalTime)+diffLatestAnswerSec)+"";
                answerTime=(parseInt(pu.answerTime)+diffLatestAnswerSec)+"";
            }catch(e){
                totalTime=0;
                answerTime=0;
            }
            if(totalTime){
                pu.totalTime=totalTime;
            }
            if(answerTime){
                pu.answerTime=answerTime;
            }
        }else if(pu.answerStatus=='analysis'){
            var diffLatestAnalysis = moment(now).diff(pu.latestAnalysisTime);
            var diffLatestAnalysisSec = Math.ceil(diffLatestAnalysis / 1000);
            var totalTime=pu.totalTime;
            var analysisTime=pu.analysisTime;
            try{
                totalTime=(parseInt(pu.totalTime)+diffLatestAnalysisSec)+"";
                analysisTime=(parseInt(pu.analysisTime)+diffLatestAnalysisSec)+"";
            }catch(e){}
            if(totalTime){
                pu.totalTime=totalTime;
            }
            if(analysisTime){
                pu.analysisTime=analysisTime;
            }
        }

        return hi.ax.post('/product/class/practiseUser/update', pu)
            .then(function (r) {
                if (r.data.status == 'fail') {
                    hi.alert(r.data.msg);
                    return Promise.reject(r);
                }
                self.studyLogUpdateForPractise(pu.answerStatus,pu.answerStatus=='answer'?diffLatestAnswerSec:diffLatestAnalysisSec);
                self.refreshPractiseUserTime(pu,now);
                pu.answerStatus='answer';
                return Promise.resolve(r);
            })
    };

}
