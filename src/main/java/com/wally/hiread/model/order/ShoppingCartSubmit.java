package com.wally.hiread.model.order;

import java.util.List;

/**
 * Created by liwen on 6/14/17.
 */
public class ShoppingCartSubmit {
    private List<ShoppingCart> cartSelectedList;
    private Coupon couponSelected;
    private Integer usePoint;
    private Double total;
    private Double diffPriceSum;
    private Double compoundDiffPriceSum;
    private Double couponPrice;
    private Double usePointPrice;
    private Double totalSave;
    private Double coursePriceSum;
    private boolean noPayment;

    public Double getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(Double couponPrice) {
        this.couponPrice = couponPrice;
    }

    public boolean isNoPayment() {
        return noPayment;
    }

    public void setNoPayment(boolean noPayment) {
        this.noPayment = noPayment;
    }


    public Double getCoursePriceSum() {
        return coursePriceSum;
    }

    public void setCoursePriceSum(Double coursePriceSum) {
        this.coursePriceSum = coursePriceSum;
    }

    public Double getDiffPriceSum() {
        return diffPriceSum;
    }

    public void setDiffPriceSum(Double diffPriceSum) {
        this.diffPriceSum = diffPriceSum;
    }

    public Double getCompoundDiffPriceSum() {
        return compoundDiffPriceSum;
    }

    public void setCompoundDiffPriceSum(Double compoundDiffPriceSum) {
        this.compoundDiffPriceSum = compoundDiffPriceSum;
    }


    public Double getUsePointPrice() {
        return usePointPrice;
    }

    public void setUsePointPrice(Double usePointPrice) {
        this.usePointPrice = usePointPrice;
    }

    public Double getTotalSave() {
        return totalSave;
    }

    public void setTotalSave(Double totalSave) {
        this.totalSave = totalSave;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ShoppingCart> getCartSelectedList() {
        return cartSelectedList;
    }

    public void setCartSelectedList(List<ShoppingCart> cartSelectedList) {
        this.cartSelectedList = cartSelectedList;
    }

    public Coupon getCouponSelected() {
        return couponSelected;
    }

    public void setCouponSelected(Coupon couponSelected) {
        this.couponSelected = couponSelected;
    }

    public Integer getUsePoint() {
        return usePoint;
    }

    public void setUsePoint(Integer usePoint) {
        this.usePoint = usePoint;
    }
}
