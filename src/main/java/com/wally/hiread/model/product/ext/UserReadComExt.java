package com.wally.hiread.model.product.ext;

import com.wally.hiread.model.product.UserReadCom;
import com.wally.hiread.model.user.User;

/**
 * Created by eric on 14/07/2017.
 */
public class UserReadComExt extends UserReadCom{
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
