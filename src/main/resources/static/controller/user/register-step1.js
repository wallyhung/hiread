/**
 * Created by eric on 26/05/2017.
 */
var registerStep1 = new Vue({
    el: '#register_step1',
    data: {
        id: "wx_qrcode",
        appid: "",
        scope: "snsapi_login",
        redirect_uri: "",
        state: "",
        style: "",
        href: ""
    },
    http: {
        root: '/hiread/user/register'
    },
    methods: {
        /**
         * 生成微信二维码
         */
        wxQrcode: function () {
            this.$http.post('').then(function (response) {
                var body = response.body;

                if(body.status == 'success'){
                    var entity = body.entity;
                    var appid = entity.appid;
                    var redirect_uri = entity.redirectUri;
                    var state = entity.state;

                    this.appid = appid;
                    this.redirect_uri = encodeURIComponent(redirect_uri);
                    this.state = state;

                    var wxLogin = new WxLogin({
                        id: this.id,
                        appid: this.appid,
                        scope: this.scope,
                        redirect_uri: this.redirect_uri,
                        state: this.state,
                        style: this.style,
                        href: this.href
                    });
                }
            }, function (response) {

            })
        }
    }
})

registerStep1.wxQrcode();