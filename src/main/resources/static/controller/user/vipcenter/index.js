/**
 * Created by eric on 21/06/2017.
 */

const items = [{
    name: '个人中心',
    routes: [{
        path: '/profile-setting',
        label: '个人信息',
        icon: 'fa-drivers-license-o'
    }, {
        path: '/reset-password',
        label: '修改密码',
        icon: 'fa-key'
    }, {
        path: '/my-notification',
        label: '我的消息',
        icon: 'fa-commenting-o'
    }, {
        path: '/my-integral',
        label: '我的积分',
        icon: 'fa-database'
    }, {
        path: '/my-coupon',
        label: '我的代金券',
        icon: 'fa-ticket'
    }, {
        path: '/my-invitation',
        label: '邀请好友',
        icon: 'fa-user-plus'
    }],
}, {
    name: '阅读大使',
    routes: [{
        path: '/ambassador-auth',
        label: '大使认证',
        icon: 'fa-certificate'
    }, {
        path: '/my-performance',
        label: '我的业绩',
        icon: 'fa-line-chart'
    }]
}]

const routes = [{
    path: '/',
    component: ProfileSetting
}, {
    path: '/profile-setting',
    component: ProfileSetting
}, {
    path: '/reset-password',
    component: ResetPassword
}, {
    path: '/my-notification',
    component: MyNotification
}, {
    path: '/my-integral',
    component: MyIntegral
}, {
    path: '/my-coupon',
    component: MyCoupon
}, {
    path: '/my-invitation',
    component: MyInvitation
}, {
    path: '/ambassador-auth',
    component: AmbassadorAuth
}, {
    path: '/my-performance',
    component: MyPerformance
}];

// 创建 router 实例
const router = new VueRouter({
    routes: routes
})

var vipcenter = new Vue({
    el: '#vipcenter',
    router: router,
    data: {
        items: items
    }
})