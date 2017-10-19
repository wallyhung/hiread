package com.wally.hiread.model.product;

import com.wally.hiread.model.user.User;

import java.util.List;

/**
 * Created by liwen on 7/4/17.
 */
public class UserReadShare {
    private String userId;
    private String userReadId;
    private String created;
    private String sign;

    private User user;
    private ReadPractise readPractise;
    private Product product;
    private int likeCount;
    private List<UserReadCom> userReadComs;
    private int commentCount;


    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ReadPractise getReadPractise() {
        return readPractise;
    }

    public void setReadPractise(ReadPractise readPractise) {
        this.readPractise = readPractise;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public List<UserReadCom> getUserReadComs() {
        return userReadComs;
    }

    public void setUserReadComs(List<UserReadCom> userReadComs) {
        this.userReadComs = userReadComs;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserReadId() {
        return userReadId;
    }

    public void setUserReadId(String userReadId) {
        this.userReadId = userReadId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}
