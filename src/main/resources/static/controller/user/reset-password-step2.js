/**
 * Created by eric on 02/06/2017.
 */
var resetPasswordStep2 = new Vue({
    el: '#reset_password_step2',
    data: {
        formData: {
            new_password: '',
            new_password_repeat: ''
        }
    },
    http: {
        root: '/hiread/user/reset/password'
    },
    methods: {
        /**
        /**
         * 处理提交表单事件
         */
        submit: function () {
            var formData = new FormData();

            formData.append('newPassword', this.formData.new_password);
            formData.append('newPasswordRepeat', this.formData.new_password_repeat);

            this.$http.post('', formData).then(function (response) {
                var body = response.body;

                if(body.status == 'success'){
                    window.location.href = '/hiread/index';
                } else {
                    alert(body.msg);
                }
            },function (response) {
            })

        }
    }
})
