/**
 * Created by eric on 02/06/2017.
 */
var layuiLayer = layui.layer;
var smsResendMinTime = 90; // 单位秒
var smsResendRemainTimeCount;

var resetPasswordStep1 = new Vue({
    el: '#reset_password_step1',
    data: {
        formData: {
            mobile: '',
            sms_captcha: ''
        },
        smsBtn: {
            content: '获取短信验证码'
        }
    },
    http: {
        root: '/hiread/user/reset/password'
    },
    methods: {
        /**
         * 处理发送短信验证码事件
         */
        sendSMSCaptcha: function (e) {
            var self = this;
            var currentTarget = e.currentTarget;
            smsResendRemainTimeCount = smsResendMinTime;
            var interval = setInterval(function () {
                if (smsResendRemainTimeCount == 0) {
                    clearInterval(interval);
                    self.smsBtn.content = '重新发送验证码';
                    $(currentTarget).removeAttr('disabled');
                } else {
                    smsResendRemainTimeCount--;
                    self.smsBtn.content = smsResendRemainTimeCount + ' s';
                    $(currentTarget).attr("disabled", "disabled");

                }
            }, 1000)

            var mobile = this.formData.mobile;
            this.$http.get('send-sms-captcha?mobile=' + mobile).then(function(response){
                var sr = response.body;
                var title = '';
                if(sr.status == 'success'){
                    title = '短信验证码已发送';
                    layuiLayer.msg(title, {
                        offset: '0',
                        icon: 1,
                        time: 3000
                    })
                } else {
                    title = '短信验证码发送失败：' + sr.msg;
                    layuiLayer.msg(title, {
                        offset: '0',
                        icon: 2,
                        time: 3000
                    })
                }
            }, function (response) {
            })
        },
        /**
         * 处理提交表单事件
         */
        submit: function () {
            var formData = new FormData();

            formData.append('mobile', this.formData.mobile);
            formData.append('smsCaptcha', this.formData.sms_captcha);

            this.$http.post('validate-sms-captcha', formData).then(function (response) {
                var body = response.body;
                var title = '';

                if(body.status == 'success'){
                    window.location.href = '/hiread/user/reset/password/step2';
                } else {
                    title = '短信验证码发送失败：' + sr.msg;
                    layuiLayer.msg(title, {
                        offset: '0',
                        icon: 2,
                        time: 3000
                    })
                }
            },function (response) {

            })

        }
    }
})
