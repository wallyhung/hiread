hi.studyLogService={
    currentStudyLog:function(studyLogs,productId,unitId,studySection){
        var m=moment();
        if(studyLogs&&studyLogs.length>0){
            var unCompletedLog=hi.studyLogService.unCompletedLog(studyLogs);

            //有未完成的当日log则返回
            if(unCompletedLog){
                unCompletedLog.latestUpdate=m.format('YYYY-MM-DD HH:mm:ss');
                return unCompletedLog;
            }
            //没有则new一个，并且sn轮次号加1
            else{
                var log=hi.studyLogService.initStudyLog(productId,unitId,studySection);
                var latestSn=hi.studyLogService.latestSn(studyLogs);
                var sn=parseInt(latestSn)+1;
                log.sn=sn+'';
                return log;
            }

        }else{
            return hi.studyLogService.initStudyLog(productId,unitId,studySection);
        }
    },
    initStudyLog:function(productId,unitId,studySection){
        var studyLog={};
        var m=moment();
        studyLog.studyDate=m.format('YYYY-MM-DD');
        studyLog.productId=productId;
        studyLog.unitId=unitId;
        studyLog.studySection=studySection;
        studyLog.sn="1";
        studyLog.startTime=m.format('YYYY-MM-DD HH:mm:ss');
        studyLog.completed="0";
        studyLog.times="0";
        studyLog.latestUpdate=m.format('YYYY-MM-DD HH:mm:ss');
        return studyLog;
    },
    unCompletedLog:function(studyLogs){
        return _.find(studyLogs,{'completed':'0'});
    },
    latestSn:function(studyLogs){
        var sort=_.sortBy(studyLogs,function(l){
            return -parseInt(l.sn);
        });
        return sort[0].sn;
    }
}
