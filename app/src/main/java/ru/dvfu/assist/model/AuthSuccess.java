package ru.dvfu.assist.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 23.01.2017.
 */

public class AuthSuccess extends Response {
    @SerializedName("data")
    Profile profile;

    public Profile getProfile() {
        return profile;
    }
}
