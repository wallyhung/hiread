/**
 * Created by eric on 26/05/2017.
 */

var layuiLayer = layui.layer;
var smsResendMinTime = 90; // 单位秒
var smsResendRemainTimeCount;

var registerStep2 = new Vue({
    el: '#register_step2',
    data: {
        wxuser: null,
        formData: {
            mobile: '',
            captcha: '',
            password: ''
        },
        baseUrl: '',
        smsBtn: {
            content: '获取短信验证码'
        }
    },
    created: function () {
        var self = this;
        hi.ax.get('/sys/config/url/base').then(function (r) {
            var baseUrl = r.data.entity;
            self.baseUrl = baseUrl;
        })
    },
    methods: {
        /**
         * 获取微信用户信息
         */
        getWxuser: function () {
            var self = this;
            hi.ax.get('user/register/wx-userinfo').then(function (r) {
                if (r.data.status == 'success') {
                    var wxuser = r.data.entity;
                    self.wxuser = wxuser;
                }
            })
        },
        /**
         * 提交注册
         */
        submit: function () {
            var self = this;
            var formData = new FormData();
            formData.append('mobile', this.formData.mobile);
            formData.append('mobileVerifyCode', this.formData.captcha);
            formData.append('password', this.formData.password);
            hi.ax.post('user/register/mobile/bind', formData).then(function (r) {
                var title = '';
                if (r.data.status == 'fail') {
                    title = r.data.msg;
                    layuiLayer.msg(title, {
                        offset: '0',
                        icon: 2,
                        time: 3000
                    })
                    return Promise.reject();
                }
                var extraInfo = r.data.extraInfo;
                var couponQuantity = extraInfo.couponQuantity;

                layui.layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    shadeClose: true,
                    content: '<div class="popup"><div class="popup-bj"></div><div class="class-popup class-translate"><h3>恭喜您注册成功！您已获得<span>￥' + couponQuantity + ' 现金券</span></h3><p>请您完善学员信息，以便我们为您的孩子推荐适合的书目</p><a href="javascript:;" class="btn btn-danger" onclick="redirectToQuestionnaire()">立即开始</a><a href="javascript:;" class="btn btn-wait" onclick="redirectToIndex()">稍后再说</a></div></div>',
                    area: '600px'
                });


                return Promise.resolve();
            }).then(function () {
                hi.ax.get('sys/session/operation').then(function (r) {
                    var opt = r.data.entity;
                    if (opt && self.baseUrl) {
                        if (opt.page == 'read_share_register') {
                            window.location.href = self.baseUrl + 'product/readShare/page/shared?userReadId=' + opt.refId;
                        }
                    }
                })
            });
        },
        /**
         * 发送短信验证码
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
            hi.ax.get('user/register/sms-captcha/send?mobile=' + mobile).then(function (r) {
                var title = '';
                if (r.data.status == 'success') {
                    title = '短信验证码已发送';
                    layuiLayer.msg(title, {
                        offset: '0',
                        icon: 1,
                        time: 3000
                    })
                } else {
                    title = '短信验证码发送失败：' + r.data.msg;
                    layuiLayer.msg(title, {
                        offset: '0',
                        icon: 2,
                        time: 3000
                    })
                }
            })
        }
    }
})

registerStep2.getWxuser();

function redirectToQuestionnaire() {
    location.href = hi.root + 'user/questionnaire';
}
function redirectToIndex() {
    location.href = hi.root;
}
