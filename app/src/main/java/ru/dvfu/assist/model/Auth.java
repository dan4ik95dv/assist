package ru.dvfu.assist.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 23.01.2017.
 */

public class Auth {
    @SerializedName("login")
    String loginUser;
    @SerializedName("password")
    String passwordUser;

    public Auth(String loginUser, String passwordUser) {
        this.loginUser = loginUser;
        this.passwordUser = passwordUser;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }
}
