/**
 * Created by eric on 31/05/2017.
 */
var header = new Vue({
    el: '#header',
    data: {
        login: false,
        user: null
    },
    http: {
        root: '/hiread'
    },
    created:function(){
        this.loginStatus();
    },
    mounted:function(){
        this.$nextTick(function(){
            $("#header").css('display','block');
            hi.bus.$emit('header-show');
         });
    },
    methods: {
        /**
         * 登录状态
         */
        loginStatus: function () {
            this.$http.get('user/login/refreshLoginUser').then(function (response) {
                var body = response.body;

                if(body.status == 'success'){
                    var entity = body.entity;
                    var wx_avatar = entity.avatarLink;
                    var wx_nickname = entity.wxNickname;

                    var user = {
                        wx_avatar: wx_avatar,
                        wx_nickname: wx_nickname
                    }

                    this.user = user;
                    this.login = true;

                    setTimeout(function () {
                        $('[data-hover="dropdown"]').dropdownHover();
                    },500)
                }
            }, function (response) {
                
            })
        },
        /**
         * 退出
         */
        logout: function () {
            
        }
    }
});

var footer = new Vue({
    el: '#footer',
    data: {
    },
    created:function(){
        hi.bus.$on('header-show',function(){
            this.$nextTick(function(){
                $("#footer").css('display','block');
            })
        });
    },
    methods: {

    }
})



