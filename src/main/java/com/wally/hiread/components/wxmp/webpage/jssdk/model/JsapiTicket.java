package com.wally.hiread.components.wxmp.webpage.jssdk.model;

import java.util.Date;

/**
 * Created by eric on 07/06/2017.
 */
public class JsapiTicket {
    private String value;
    private Date dateCreated;
    private Date dateModified;
    private int validTime;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public int getValidTime() {
        return validTime;
    }

    public void setValidTime(int validTime) {
        this.validTime = validTime;
    }
}
