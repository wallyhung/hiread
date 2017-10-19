Vue.component('hi-header', {
    template: '#hi-header-t',
    data: function () {
        return {
            login: false,
            user: null
        }
    },
    http: {
        root: '/hiread'
    },
    created:function(){
        this.loginStatus();
    },
    mounted:function(){
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
})