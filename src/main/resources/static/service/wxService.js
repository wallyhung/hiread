hi.wxService={
    debug:false,
    /**
     * 配置微信 JSSDK
     */
    configJssdk: function (url) {
        return hi.ax.get('wx/mp/jssdk-config?url=' + url)
            .then(function (r) {
                if(r.data.status=="fail"){
                    hi.alert(r.data.msg);
                    return Promise.reject(r);
                }
                var entity = r.data.entity;
                wx.config({
                    debug: hi.wxService.debug,
                    appId: entity.appid,
                    timestamp: entity.timestamp,
                    nonceStr: entity.noncestr,
                    signature: entity.signature,
                    jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage']
                });
                return Promise.resolve(r);
            });
    }
}

