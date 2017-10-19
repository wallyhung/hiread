package com.wally.hiread.model.sys;

import com.wally.hiread.model.product.Product;

import java.util.List;

public class TestingReport {
    String userId;
    int paperLevel;
    int correctNum;
    int totalNum;
    int correctPercent;
    List<Integer> recommendHireadRanks;
    List<Product> recommendProducts;

    public List<Integer> getRecommendHireadRanks() {
        return recommendHireadRanks;
    }

    public void setRecommendHireadRanks(List<Integer> recommendHireadRanks) {
        this.recommendHireadRanks = recommendHireadRanks;
    }

    public int getPaperLevel() {
        return paperLevel;
    }

    public void setPaperLevel(int paperLevel) {
        this.paperLevel = paperLevel;
    }

    public int getCorrectPercent() {
        return correctPercent;
    }

    public void setCorrectPercent(int correctPercent) {
        this.correctPercent = correctPercent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCorrectNum() {
        return correctNum;
    }

    public void setCorrectNum(int correctNum) {
        this.correctNum = correctNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }


    public List<Product> getRecommendProducts() {
        return recommendProducts;
    }

    public void setRecommendProducts(List<Product> recommendProducts) {
        this.recommendProducts = recommendProducts;
    }
}
