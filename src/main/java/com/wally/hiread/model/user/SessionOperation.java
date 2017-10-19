package com.wally.hiread.model.user;

import com.wally.hiread.components.wxmp.webpage.authorization.model.WxmpUserInfo;

public class SessionOperation {
    public static final String KEY="session_operation";

    public static final String PAGE_READ_SHARE_AUTH="read_share_auth";
    public static final String PAGE_READ_SHARE="read_share";
    public static final String PAGE_READ_SHARE_REGISTER="read_share_register";

    public static final String TYPE_PRAISE="praise";
    public static final String TYPE_COMMENT="comment";
    public static final String TYPE_GET_COUPON="get_coupon";
    public static final String TYPE_SHARE="share";

    private String refId;
    private String initiatorId;
    private String page;
    private String type;
    private String param;
    private String msg;
    private WxmpUserInfo wxUser;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public WxmpUserInfo getWxUser() {
        return wxUser;
    }

    public void setWxUser(WxmpUserInfo wxUser) {
        this.wxUser = wxUser;
    }
}
