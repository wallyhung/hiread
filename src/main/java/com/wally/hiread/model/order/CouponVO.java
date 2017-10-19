package com.wally.hiread.model.order;

/**
 * Created by eric on 30/06/2017.
 */
public class CouponVO extends Coupon{
    /**
     * 失效间隔天数
     */
    private String intervalDays;

    public String getIntervalDays() {
        return intervalDays;
    }

    public void setIntervalDays(String intervalDays) {
        this.intervalDays = intervalDays;
    }
}
