/**
 * Created by eric on 30/08/2017.
 */

var layer = layui.layer;

var questionnaire = new Vue({
    el: '#questionnaire',
    data: {
        answer: {
            q1: {
                value: '',
                active: {
                    A: false,
                    B: false,
                    C: false,
                    D: false
                }
            },
            q2: {
                value: '',
                active: {
                    A: false,
                    B: false,
                    C: false,
                    D: false,
                    E: false,
                    F: false
                }
            },
            q3: {
                value: '',
                active: {
                    A: false,
                    B: false,
                    C: false,
                    D: false,
                    E: false,
                    F: false
                }
            },
            q4: {
                value: '',
                active: {
                    A: false,
                    B: false,
                    C: false,
                    D: false,
                    E: false,
                    F: false
                }
            },
            q5: {
                value: [],
                active: {
                    A: false,
                    B: false,
                    C: false,
                    D: false
                }
            },
            q6: {
                value: [],
                active: {
                    A: false,
                    B: false,
                    C: false,
                    D: false
                }
            },
            q7: {
                value: [],
                value_addition: '',
                active: {
                    A: false,
                    B: false,
                    C: false,
                    D: false,
                    E: false,
                    F: false,
                    G: false,
                    H: false
                }
            },
            q8: {
                value: [],
                value_addition: '',
                active: {
                    A: false,
                    B: false,
                    C: false,
                    D: false,
                    E: false,
                    F: false,
                    G: false,
                    H: false
                }
            },
            q9: '',
            q10: {
                province: '',
                city: '',
                district: '',
                english_name: '',
                birthday: '',
                gender: ''
            }
        },
        currentQuestionNo: 1,
        processPercentage: '10%',
        sexOptions: [{
            label: '男',
            value: 'male'
        }, {
            label: '女',
            value: 'female'
        }]
    },
    created: function () {

    },
    computed: {},
    methods: {
        radio: function (questionNo, value) {
            var vm = this;

            if (questionNo == '1') {
                vm.answer.q1.value = value;
                switch (value) {
                    case 'A':
                        vm.answer.q1.active.A = true;
                        vm.answer.q1.active.B = false;
                        vm.answer.q1.active.C = false;
                        vm.answer.q1.active.D = false;
                        break;
                    case 'B':
                        vm.answer.q1.active.A = false;
                        vm.answer.q1.active.B = true;
                        vm.answer.q1.active.C = false;
                        vm.answer.q1.active.D = false;
                        break;
                    case 'C':
                        vm.answer.q1.active.A = false;
                        vm.answer.q1.active.B = false;
                        vm.answer.q1.active.C = true;
                        vm.answer.q1.active.D = false;
                        break;
                    case 'D':
                        vm.answer.q1.active.A = false;
                        vm.answer.q1.active.B = false;
                        vm.answer.q1.active.C = false;
                        vm.answer.q1.active.D = true;
                        break;
                    default:
                        break;
                }
            } else if (questionNo == '2') {
                vm.answer.q2.value = value;
                switch (value) {
                    case 'A':
                        vm.answer.q2.active.A = true;
                        vm.answer.q2.active.B = false;
                        vm.answer.q2.active.C = false;
                        vm.answer.q2.active.D = false;
                        vm.answer.q2.active.E = false;
                        vm.answer.q2.active.F = false;
                        break;
                    case 'B':
                        vm.answer.q2.active.A = false;
                        vm.answer.q2.active.B = true;
                        vm.answer.q2.active.C = false;
                        vm.answer.q2.active.D = false;
                        vm.answer.q2.active.E = false;
                        vm.answer.q2.active.F = false;
                        break;
                    case 'C':
                        vm.answer.q2.active.A = false;
                        vm.answer.q2.active.B = false;
                        vm.answer.q2.active.C = true;
                        vm.answer.q2.active.D = false;
                        vm.answer.q2.active.E = false;
                        vm.answer.q2.active.F = false;
                        break;
                    case 'D':
                        vm.answer.q2.active.A = false;
                        vm.answer.q2.active.B = false;
                        vm.answer.q2.active.C = false;
                        vm.answer.q2.active.D = true;
                        vm.answer.q2.active.E = false;
                        vm.answer.q2.active.F = false;
                        break;
                    case 'E':
                        vm.answer.q2.active.A = false;
                        vm.answer.q2.active.B = false;
                        vm.answer.q2.active.C = false;
                        vm.answer.q2.active.D = false;
                        vm.answer.q2.active.E = true;
                        vm.answer.q2.active.F = false;
                        break;
                    case 'F':
                        vm.answer.q2.active.A = false;
                        vm.answer.q2.active.B = false;
                        vm.answer.q2.active.C = false;
                        vm.answer.q2.active.D = false;
                        vm.answer.q2.active.E = false;
                        vm.answer.q2.active.F = true;
                        break;
                    default:
                        break;
                }
            } else if (questionNo == '3') {
                vm.answer.q3.value = value;
                switch (value) {
                    case 'A':
                        vm.answer.q3.active.A = true;
                        vm.answer.q3.active.B = false;
                        vm.answer.q3.active.C = false;
                        vm.answer.q3.active.D = false;
                        vm.answer.q3.active.E = false;
                        vm.answer.q3.active.F = false;
                        break;
                    case 'B':
                        vm.answer.q3.active.A = false;
                        vm.answer.q3.active.B = true;
                        vm.answer.q3.active.C = false;
                        vm.answer.q3.active.D = false;
                        vm.answer.q3.active.E = false;
                        vm.answer.q3.active.F = false;
                        break;
                    case 'C':
                        vm.answer.q3.active.A = false;
                        vm.answer.q3.active.B = false;
                        vm.answer.q3.active.C = true;
                        vm.answer.q3.active.D = false;
                        vm.answer.q3.active.E = false;
                        vm.answer.q3.active.F = false;
                        break;
                    case 'D':
                        vm.answer.q3.active.A = false;
                        vm.answer.q3.active.B = false;
                        vm.answer.q3.active.C = false;
                        vm.answer.q3.active.D = true;
                        vm.answer.q3.active.E = false;
                        vm.answer.q3.active.F = false;
                        break;
                    case 'E':
                        vm.answer.q3.active.A = false;
                        vm.answer.q3.active.B = false;
                        vm.answer.q3.active.C = false;
                        vm.answer.q3.active.D = false;
                        vm.answer.q3.active.E = true;
                        vm.answer.q3.active.F = false;
                        break;
                    case 'F':
                        vm.answer.q3.active.A = false;
                        vm.answer.q3.active.B = false;
                        vm.answer.q3.active.C = false;
                        vm.answer.q3.active.D = false;
                        vm.answer.q3.active.E = false;
                        vm.answer.q3.active.F = true;
                        break;
                    default:
                        break;
                }
            } else if (questionNo == '4') {
                vm.answer.q4.value = value;
                switch (value) {
                    case 'A':
                        vm.answer.q4.active.A = true;
                        vm.answer.q4.active.B = false;
                        vm.answer.q4.active.C = false;
                        vm.answer.q4.active.D = false;
                        vm.answer.q4.active.E = false;
                        vm.answer.q4.active.F = false;
                        break;
                    case 'B':
                        vm.answer.q4.active.A = false;
                        vm.answer.q4.active.B = true;
                        vm.answer.q4.active.C = false;
                        vm.answer.q4.active.D = false;
                        vm.answer.q4.active.E = false;
                        vm.answer.q4.active.F = false;
                        break;
                    case 'C':
                        vm.answer.q4.active.A = false;
                        vm.answer.q4.active.B = false;
                        vm.answer.q4.active.C = true;
                        vm.answer.q4.active.D = false;
                        vm.answer.q4.active.E = false;
                        vm.answer.q4.active.F = false;
                        break;
                    case 'D':
                        vm.answer.q4.active.A = false;
                        vm.answer.q4.active.B = false;
                        vm.answer.q4.active.C = false;
                        vm.answer.q4.active.D = true;
                        vm.answer.q4.active.E = false;
                        vm.answer.q4.active.F = false;
                        break;
                    case 'E':
                        vm.answer.q4.active.A = false;
                        vm.answer.q4.active.B = false;
                        vm.answer.q4.active.C = false;
                        vm.answer.q4.active.D = false;
                        vm.answer.q4.active.E = true;
                        vm.answer.q4.active.F = false;
                        break;
                    case 'F':
                        vm.answer.q4.active.A = false;
                        vm.answer.q4.active.B = false;
                        vm.answer.q4.active.C = false;
                        vm.answer.q4.active.D = false;
                        vm.answer.q4.active.E = false;
                        vm.answer.q4.active.F = true;
                        break;
                    default:
                        break;
                }
            } else {
            }
        },
        multiSelect: function (questionNo, value) {
            var vm = this;
            var isExist = false;

            if (questionNo == '5') {
                var q5Answer = vm.answer.q5.value;
                switch (value) {
                    case 'A':
                        for (var i = 0; i < q5Answer.length; i++) {
                            if (q5Answer[i] == 'A') {
                                q5Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q5Answer.push('A');
                        }
                        vm.answer.q5.active.A = !vm.answer.q5.active.A;
                        break;
                    case 'B':
                        for (var i = 0; i < q5Answer.length; i++) {
                            if (q5Answer[i] == 'B') {
                                q5Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q5Answer.push('B');
                        }
                        vm.answer.q5.active.B = !vm.answer.q5.active.B;
                        break;
                    case 'C':
                        for (var i = 0; i < q5Answer.length; i++) {
                            if (q5Answer[i] == 'C') {
                                q5Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q5Answer.push('C');
                        }
                        vm.answer.q5.active.C = !vm.answer.q5.active.C;
                        break;
                    case 'D':
                        for (var i = 0; i < q5Answer.length; i++) {
                            if (q5Answer[i] == 'D') {
                                q5Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q5Answer.push('D');
                        }
                        vm.answer.q5.active.D = !vm.answer.q5.active.D;
                        break;
                    default:
                        break;
                }

            } else if (questionNo == '6') {
                var q6Answer = vm.answer.q6.value;
                switch (value) {
                    case 'A':
                        for (var i = 0; i < q6Answer.length; i++) {
                            if (q6Answer[i] == 'A') {
                                q6Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q6Answer.push('A');
                        }
                        vm.answer.q6.active.A = !vm.answer.q6.active.A;
                        break;
                    case 'B':
                        for (var i = 0; i < q6Answer.length; i++) {
                            if (q6Answer[i] == 'B') {
                                q6Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q6Answer.push('B');
                        }
                        vm.answer.q6.active.B = !vm.answer.q6.active.B;
                        break;
                    case 'C':
                        for (var i = 0; i < q6Answer.length; i++) {
                            if (q6Answer[i] == 'C') {
                                q6Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q6Answer.push('C');
                        }
                        vm.answer.q6.active.C = !vm.answer.q6.active.C;
                        break;
                    case 'D':
                        for (var i = 0; i < q6Answer.length; i++) {
                            if (q6Answer[i] == 'D') {
                                q6Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q6Answer.push('D');
                        }
                        vm.answer.q6.active.D = !vm.answer.q6.active.D;
                        break;
                    default:
                        break;
                }
            } else if (questionNo == '7') {
                var q7Answer = vm.answer.q7.value;
                switch (value) {
                    case 'A':
                        for (var i = 0; i < q7Answer.length; i++) {
                            if (q7Answer[i] == 'A') {
                                q7Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q7Answer.push('A');
                        }
                        vm.answer.q7.active.A = !vm.answer.q7.active.A;
                        break;
                    case 'B':
                        for (var i = 0; i < q7Answer.length; i++) {
                            if (q7Answer[i] == 'B') {
                                q7Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q7Answer.push('B');
                        }
                        vm.answer.q7.active.B = !vm.answer.q7.active.B;
                        break;
                    case 'C':
                        for (var i = 0; i < q7Answer.length; i++) {
                            if (q7Answer[i] == 'C') {
                                q7Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q7Answer.push('C');
                        }
                        vm.answer.q7.active.C = !vm.answer.q7.active.C;
                        break;
                    case 'D':
                        for (var i = 0; i < q7Answer.length; i++) {
                            if (q7Answer[i] == 'D') {
                                q7Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q7Answer.push('D');
                        }
                        vm.answer.q7.active.D = !vm.answer.q7.active.D;
                        break;
                    case 'E':
                        for (var i = 0; i < q7Answer.length; i++) {
                            if (q7Answer[i] == 'E') {
                                q7Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q7Answer.push('E');
                        }
                        vm.answer.q7.active.E = !vm.answer.q7.active.E;
                        break;
                    case 'F':
                        for (var i = 0; i < q7Answer.length; i++) {
                            if (q7Answer[i] == 'F') {
                                q7Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q7Answer.push('F');
                        }
                        vm.answer.q7.active.F = !vm.answer.q7.active.F;
                        break;
                    case 'G':
                        for (var i = 0; i < q7Answer.length; i++) {
                            if (q7Answer[i] == 'G') {
                                q7Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q7Answer.push('G');
                        }
                        vm.answer.q7.active.G = !vm.answer.q7.active.G;
                        break;
                    case 'H':
                        for (var i = 0; i < q7Answer.length; i++) {
                            if (q7Answer[i] == 'H') {
                                q7Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q7Answer.push('H');
                        }
                        vm.answer.q7.active.H = !vm.answer.q7.active.H;
                        break;
                    default:
                        break;
                }
            } else if (questionNo == '8') {
                var q8Answer = vm.answer.q8.value;
                switch (value) {
                    case 'A':
                        for (var i = 0; i < q8Answer.length; i++) {
                            if (q8Answer[i] == 'A') {
                                q8Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q8Answer.push('A');
                        }
                        vm.answer.q8.active.A = !vm.answer.q8.active.A;
                        break;
                    case 'B':
                        for (var i = 0; i < q8Answer.length; i++) {
                            if (q8Answer[i] == 'B') {
                                q8Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q8Answer.push('B');
                        }
                        vm.answer.q8.active.B = !vm.answer.q8.active.B;
                        break;
                    case 'C':
                        for (var i = 0; i < q8Answer.length; i++) {
                            if (q8Answer[i] == 'C') {
                                q8Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q8Answer.push('C');
                        }
                        vm.answer.q8.active.C = !vm.answer.q8.active.C;
                        break;
                    case 'D':
                        for (var i = 0; i < q8Answer.length; i++) {
                            if (q8Answer[i] == 'D') {
                                q8Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q8Answer.push('D');
                        }
                        vm.answer.q8.active.D = !vm.answer.q8.active.D;
                        break;
                    case 'E':
                        for (var i = 0; i < q8Answer.length; i++) {
                            if (q8Answer[i] == 'E') {
                                q8Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q8Answer.push('E');
                        }
                        vm.answer.q8.active.E = !vm.answer.q8.active.E;
                        break;
                    case 'F':
                        for (var i = 0; i < q8Answer.length; i++) {
                            if (q8Answer[i] == 'F') {
                                q8Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q8Answer.push('F');
                        }
                        vm.answer.q8.active.F = !vm.answer.q8.active.F;
                        break;
                    case 'G':
                        for (var i = 0; i < q8Answer.length; i++) {
                            if (q8Answer[i] == 'G') {
                                q8Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q8Answer.push('G');
                        }
                        vm.answer.q8.active.G = !vm.answer.q8.active.G;
                        break;
                    case 'H':
                        for (var i = 0; i < q8Answer.length; i++) {
                            if (q8Answer[i] == 'H') {
                                q8Answer.splice(i, 1);
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            q8Answer.push('H');
                        }
                        vm.answer.q8.active.H = !vm.answer.q8.active.H;
                        break;
                    default:
                        break;
                }
            }

        },
        nextQuestion: function () {
            var vm = this;
            var hasInput = false;

            if (vm.currentQuestionNo == 1) {
                if(vm.answer.q1.value != ''){
                    hasInput = true;
                }
            }

            if (vm.currentQuestionNo == 2) {
                if(vm.answer.q2.value != ''){
                    hasInput = true;
                }
            }

            if (vm.currentQuestionNo == 3) {
                if(vm.answer.q3.value != ''){
                    hasInput = true;
                }
            }

            if (vm.currentQuestionNo == 4) {
                if(vm.answer.q4.value != ''){
                    hasInput = true;
                }
            }

            if (vm.currentQuestionNo == 5) {
                if(vm.answer.q5.value != ''){
                    hasInput = true;
                }
            }

            if (vm.currentQuestionNo == 6) {
                if(vm.answer.q6.value != ''){
                    hasInput = true;
                }
            }

            if(vm.currentQuestionNo == 7 || vm.currentQuestionNo == 8
                || vm.currentQuestionNo == 9 || vm.currentQuestionNo == 10){
                hasInput = true;
            }

            if(!hasInput){
                layer.msg('选项不能为空', {
                    offset: '0',
                    icon: 2,
                    time: 3000
                })
                return;
            }

            vm.currentQuestionNo++;
            vm.processPercentage = (10 * vm.currentQuestionNo) + '%';
        },
        prevQuestion: function () {
            var vm = this;
            vm.currentQuestionNo--;
            vm.processPercentage = (10 * vm.currentQuestionNo) + '%';
        },
        submit: function () {
            var vm = this;
            var formData = new FormData();
            formData.append('question1', vm.answer.q1.value);
            formData.append('question2', vm.answer.q2.value);
            formData.append('question3', vm.answer.q3.value);
            formData.append('question4', vm.answer.q4.value);

            var q5Answer = vm.answer.q5.value;
            var q5AnswerStr = '';
            for (var i = 0; i < q5Answer.length; i++) {
                q5AnswerStr += q5Answer[i] + ';';
            }
            q5AnswerStr = q5AnswerStr.substring(0, q5AnswerStr.length - 1);
            formData.append('question5', q5AnswerStr);

            var q6Answer = vm.answer.q6.value;
            var q6AnswerStr = '';
            for (var i = 0; i < q6Answer.length; i++) {
                q6AnswerStr += q6Answer[i] + ';';
            }
            q6AnswerStr = q6AnswerStr.substring(0, q6AnswerStr.length - 1);
            formData.append('question6', q6AnswerStr);

            var q7Answer = vm.answer.q7.value;
            var q7AnswerStr = '';
            for (var i = 0; i < q7Answer.length; i++) {
                q7AnswerStr += q7Answer[i] + ';';
            }
            try {
                q7AnswerStr = q7AnswerStr.substring(0, q7AnswerStr.length - 1);
                formData.append('question7', q7AnswerStr);
            } catch (e) {
            }
            formData.append('question7Addition', vm.answer.q7.value_addition);

            var q8Answer = vm.answer.q8.value;
            var q8AnswerStr = '';
            for (var i = 0; i < q8Answer.length; i++) {
                q8AnswerStr += q8Answer[i] + ';';
            }
            try {
                q8AnswerStr = q8AnswerStr.substring(0, q8AnswerStr.length - 1);
                formData.append('question8', q8AnswerStr);
            } catch (e) {
            }
            formData.append('question8Addition', vm.answer.q8.value_addition);

            formData.append('question9', vm.answer.q9);

            formData.append('question10Province', vm.answer.q10.province);
            formData.append('question10City', vm.answer.q10.city);
            formData.append('question10District', vm.answer.q10.district);
            formData.append('question10EnglishName', vm.answer.q10.english_name);
            formData.append('question10Birthday', vm.answer.q10.birthday);
            formData.append('question10Gender', vm.answer.q10.gender);

            // 提交数据
            hi.ax.post('user/questionnaire/submit', formData).then(function (response) {
                var data = response.data;
                if (data.status == 'fail') {
                    title = data.msg;
                    layer.msg(title, {
                        offset: '0',
                        icon: 2,
                        time: 3000
                    })
                    return Promise.reject();
                }
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    shadeClose: true,
                    content: '<div class="popup"><div class="popup-bj"></div><div class="class-popup class-translate"><h3>学员信息已完善！</h3><p style="text-align: left;">想了解孩子当前的英文阅读水平，以及适合读什么原版书吗？请让学员完成HiRead阅读水平测试。</p><a href="javascript:;" class="btn btn-wait" onclick="redirectToIndex()">稍后再说</a><a href="javascript:;" class="btn btn-danger" onclick="redirectToReadingLevelTest()">立即开始</a></div></div>',
                    area: '600px'
                });
                return Promise.resolve();
            }).then(function () {
            })
        },
        changeBirthday: function (value) {
            var vm = this;
            vm.answer.q10.birthday = value;
        },
        changeSex: function (value) {
            var vm = this;
            vm.answer.q10.gender = value;
        }

    }
})

function redirectToIndex() {
    location.href = hi.root;
}

function redirectToReadingLevelTest() {
    location.href = hi.root + 'user/testing/readingLevel';
}