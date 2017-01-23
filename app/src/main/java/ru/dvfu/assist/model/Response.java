package ru.dvfu.assist.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 23.01.2017.
 */

public class Response {
    @SerializedName("status")
    String statusServer;
    @SerializedName("msg")
    String msgServer;
}
