package com.wally.hiread.model.product;

import java.util.Date;

/**
 * Created by liwen on 10/17/17.
 */
public class HiToken {
    private String id;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
