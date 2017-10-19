var login = new Vue({
    el: '#login',
    data: {
        type: {
            normal: {
                active: true
            },
            wx: {
                active: false
            }
        },
        formData: {
            mobile: '',
            password: ''
        },
        qrcode: {
            id: "lc",
            appid: "",
            scope: "snsapi_login",
            redirect_uri: "",
            state: "",
            style: "",
            href: ""
        }
    },
    http: {
        root: '/hiread/user/login'
    },
    methods: {
        /**
         * 处理普通登录方式点击
         */
        onNormalClick: function () {
            this.type.normal.active = true;
            this.type.wx.active = false;
        },
        /**
         * 处理微信登录方式点击
         */
    	onWxClick: function () {
            this.type.normal.active = false;
            this.type.wx.active = true;

            this.wxQrcode();
        },
        /**
         * 处理表单提交
         */
        submit: function () {
            var formData = new FormData();
            formData.append("mobile", this.formData.mobile);
            formData.append("password", this.formData.password);
            
            this.$http.post('normal', formData).then(function (response) {
                var body = response.body;

                if(body.status == 'success'){
                    window.location.href = '/hiread/index';
                } else {
                  alert("error:" + body.msg);
                }
            }, function (response) {

            });
        },
        /**
         * 生成微信二维码
         */
        wxQrcode: function () {
            this.$http.get('wx/requestLoginUrl').then(function (response) {
                var body = response.body;

                if(body.status == 'success'){
                    var entity = body.entity;
                    var appid = entity.appid;
                    var redirect_uri = entity.redirectUri;
                    var state = entity.state;

                    this.qrcode.appid = appid;
                    this.qrcode.redirect_uri = encodeURIComponent(redirect_uri);
                    this.qrcode.state = state;

                    var wxLogin = new WxLogin({
                        id: this.qrcode.id,
                        appid: this.qrcode.appid,
                        scope: this.qrcode.scope,
                        redirect_uri: this.qrcode.redirect_uri,
                        state: this.qrcode.state,
                        style: this.qrcode.style,
                        href: this.qrcode.href
                    });
                }
            }, function (response) {

            })
        }
    }
})