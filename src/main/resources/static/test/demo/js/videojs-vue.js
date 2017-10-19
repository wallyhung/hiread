var app1 = new Vue({
    el: '#app-1',
    data: {
        player:null,
        qss:[{id:"rdqs1",time:10,ava:true,show:false,name:'question one'},
            {id:"rdqs2",time:20,ava:true,show:false,name:'question two'},
            {id:"rdqs3",time:30,ava:true,show:false,name:'question three'}],
        qssAnswered:[],
        audio:{time:5,show:false},
        videoShow:true,
        congradulationShow:false,
        tab:1
    },
    methods: {
        congradulationBack:function(){
            this.videoShow=true;
            this.congradulationShow=false;
        },
        qback:function(index){
            this.qss[index].show=false;
            this.videoShow=true;
        },
        submit:function(index){
            this.qss[index].show=false;
            this.qss[index].ava=false;
            $("#"+this.qss[index].id).hide();
            this.qssAnswered.push(this.qss[index]);
            this.videoShow=true;
            this.player.play();
        },
        changeTab:function(tab){
            this.tab=tab;
        },
        view:function(index){
            this.player.pause();
            this.videoShow=false;
            this.congradulationShow=false;
            for(var i=0;i<this.qss.length;i++){
                if(i==index){
                    this.qss[i].show=true;
                }else{
                    this.qss[i].show=false;
                }
            }
        }
    },
    mounted: function () {
        var that=this;
        this.$nextTick(function () {
        	videojs('my-audio', {
                "aspectRatio": "1:0","fluid": true,"controlBar": {"fullscreenToggle": false}
            },function(){
            });
            this.player=videojs('my-video', {
                controlBar:true,
                nativeControlsForTouch: false,
                fullscreenToggle: false
            },function(){
            });
            this.player.exitFullscreen();
            var that=this;
            that.player.one('loadedmetadata', function() {
                var player=that.player;
                var width = document.getElementById("my-video").parentElement.offsetWidth;
                var height = document.getElementById("my-video").parentElement.offsetHeight;
                player.width(width).height(height);
                var dur=player.duration();
                var controlBar=player.getChild('ControlBar');
                var fullscreen=controlBar.getChild('fullscreenToggle');
                fullscreen.hide();
                var volumeMenuButton=controlBar.getChild('volumeMenuButton');
                volumeMenuButton.hide();
                var progressControl=controlBar.getChild('progressControl');
                var contentEl=progressControl.contentEl();
                
                for(var i=0;i<that.qss.length;i++){
                    var q=that.qss[i];
                    var per=parseInt(((q.time)/dur)*100);
                    q.percent=per;
                    var qe=$("<div class='rd-qs-point' style='left:"+per+"%' id='"+q.id+"'></div>");
                    $(contentEl).append(qe);
                }

                that.player.on('timeupdate',function(){
                    var cur=player.currentTime();
                    for(var i=0;i<that.qss.length;i++){
                        var q=that.qss[i];
                        if(q.ava){
                            if(cur>q.time){
                                player.currentTime(q.time);
                                player.pause();
                                that.videoShow=false;
                                q.show=true;
                                player.exitFullscreen();
                                break;
                            }
                        }
                    }
                    // if(that.audio.time==parseInt(cur)){
                    //     player.pause();
                    //     player.currentTime(parseInt(that.audio.time)+1);
                    //     that.audio.show=true;
                    //     player.exitFullscreen();
                    //
                    //     //$("#ui360").appendTo('#ui360-container');
                    //
                    // }else if(cur==(parseInt(that.audio.time)+1)){
                    // 	that.audio.show=true;
                    // }else{
                    //     that.audio.show=false;
                    // }

                });
                that.player.on('play',function(){
                    if(that.audio.show){
                        that.audio.show=false;
                        
                    }
                });
                that.player.on('ended',function(){
                    that.videoShow=false;
                    that.congradulationShow=true;
                    player.exitFullscreen();
                });
            });
        })
    }
})