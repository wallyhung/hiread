const store = new Vuex.Store({
    state: {
        testingPaperLevel:'',
        testings: null,
        testingStage:'info',//info:开始说明,begin:开始,end:结束
        testingIndex:0,
        testing:null,
        testingAnswer:'',
        testingReport:null
    },
    getters:{
        testingPercent:function(state){
            var percent=0;
            if(state.testings.length){
                var currentPage=state.testingIndex+1;
                var totalPages=state.testings.length;
                percent=Math.ceil((currentPage/totalPages)*100);
            }
            return percent;
        },
        testingCorrect:function(state){
            if(!state.testing||!state.testingAnswer){
                return 'No';
            }
            if(state.testing.correctAnswer==state.testingAnswer){
                return 'Yes';
            }else{
                return 'No';
            }
        },
        testingRecommendRanksText:function(state){
            if(state.testingReport){
                var ranks=state.testingReport.recommendHireadRanks;
                return ranks.join('-');
            }

        }
    },
    mutations: {
        setTestingPaperLevel (state,level) {
            state.testingPaperLevel=level;
        },
        setTestings (state,payload) {
            state.testings=payload;
        },
        setTestingStage(state,stage){
            state.testingStage=stage;
        },
        setTestingIndex(state,index){
            state.testingIndex=index;
        },
        setTesting(state,testing){
            state.testing=testing;
        },
        setTestingAnswer(state,answer){
            state.testingAnswer=answer;
        },
        setTestingReport(state,report){
            state.testingReport=report;
        }
    },
    actions:{
        fetchTestings(context){
            hi.ax.get('/user/testing/readingLevel/testings?paperLevel='+context.state.testingPaperLevel,{}).then(function (r) {
                if(r.data.status=='fail'){
                    hi.alert(r.data.msg);
                    return;
                }
                if(r.data.entity){
                    context.commit('setTestings',r.data.entity);
                    if(!context.state.testingPaperLevel){
                        if(r.data.entity[0]){
                            context.commit('setTestingPaperLevel',r.data.entity[0].paperLevel);
                        }
                    }
                }
            });
        },
        fetchTestingReport(context){
            hi.ax.get('/user/testing/readingLevel/testing/report?paperLevel='+context.state.testingPaperLevel,{}).then(function (r) {
                if(r.data.status=='fail'){
                    hi.alert(r.data.msg);
                    return;
                }
                if(r.data.entity){
                    context.commit('setTestingReport',r.data.entity);
                }
            });
        }
    }
});
Vue.directive('line-break-text', {
    bind: function (el, binding, vnode) {
        var value=binding.value;
        value.replace('\n','<br>');
        el.innerHTML=value;
    }
});

var appReadingLevel = new Vue({
    el: '#app-reading-level',
    store,
    mixins: [hi.mixin],
    data: {
        submitting:false,
        audioTimes:0
    },
    created:function(){
        var level=hi.queryString.level;
        if(level){
            store.commit('setTestingPaperLevel',level);
        }
        store.dispatch('fetchTestings');
    },
    computed:{
        testings: function testings() {
            return store.state.testings;
        },
        testingStage: function testingStage() {
            return store.state.testingStage;
        },
        testingIndex: function testingIndex() {
            return store.state.testingIndex;
        },
        testing: function testing() {
            return store.state.testing;
        },
        testingAnswer: function testingAnswer() {
            return store.state.testingAnswer;
        },
        testingReport: function testingReport() {
            return store.state.testingReport;
        },
        testingPercent: function testingPercent() {
            return store.getters.testingPercent;
        },
        testingCorrect: function testingCorrect() {
            return store.getters.testingCorrect;
        },
        testingRecommendRanksText: function testingRecommendRanksText() {
            return store.getters.testingRecommendRanksText;
        }

    },
    methods: {
        beginTesting:function(){
            store.commit('setTestingStage','begin');
            store.commit('setTestingIndex',0);
            store.commit('setTesting',this.testings[this.testingIndex]);
        },
        selectAnswer:function(answer){
            store.commit('setTestingAnswer',answer);
        },
        submit:function(){
            var self=this;
            if(!this.testingAnswer){
                hi.alert('请选择');
                return Promise.reject();
            }
            if(this.submitting){
                return Promise.reject();
            }
            this.submitting=true;
            var fd=new FormData();
            fd.append('testingId',this.testing.id);
            fd.append('answer',this.testingAnswer);
            fd.append('correct',this.testingCorrect);
            return hi.ax.post('/user/testing/readingLevel/testing/submit',fd).then(function (r) {
                if(r.data.status=='fail'){
                    hi.alert(r.data.msg);
                    self.submitting=false;
                    return Promise.reject();
                }
                self.submitting=false;
                return Promise.resolve();
            });

        },
        next:function(){
            var self=this;
            self.submit().then(function(){
                var index=self.testingIndex+1;
                self.audioTimes=0;
                store.commit('setTestingIndex',index);
                store.commit('setTesting',self.testings[self.testingIndex]);
                store.commit('setTestingAnswer','');
            },function(){});

        },
        finish:function(){
            var self=this;
            self.submit().then(function(){
                store.commit('setTestingStage','end');
                store.dispatch('fetchTestingReport');

            },function(){});
        },
        later:function(){
            // store.commit('setTestingStage','end');
            // store.dispatch('fetchTestingReport');
            location.href=hi.root+'product/classCenter#/testing';
        },
        pressEnter:function(){
            if(this.testingStage=='info'){
                this.beginTesting();
            }else if(this.testingStage=='begin'&&this.testingIndex<this.testings.length-1){
                this.next();
            }else if(this.testingStage=='begin'&&this.testingIndex==this.testings.length-1){
                this.finish();
            }
        },
        questionImgSrc:function(){
            return hi.root + "sys/file/img?type=img_testing_question_img&id=" + this.testing.id;
        },
        questionAudioSrc:function(){
            return hi.root + "sys/file/audio?type=audio_testing_question_audio&id=" + this.testing.id;
        },
        questionVideoSrc:function(){
            return hi.root + "sys/file/video?type=video_testing_question_video&id=" + this.testing.id;
        },
        getProductIconSrc:function(id){
            return hi.root + "sys/file/img?type=img_product&id=" + id;
        },
        getLevelSrc:function(rank){
            return hi.root+'/img/level'+rank+'jpg.png';
        },
        toIndex:function(){
            location.href=hi.root;
        },
        toProduct:function(id){
            location.href=hi.root+'#/product?productId='+id;
        },
        onAudioEnd:function(){
            this.audioTimes++;
        }

    },
    mounted:function(){

    },
    watch:{
    }

})