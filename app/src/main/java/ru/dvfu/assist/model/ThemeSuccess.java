package ru.dvfu.assist.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 23.01.2017.
 */

public class ThemeSuccess extends Response {
    @SerializedName("data")
    Themes themes;
    public Themes getThemes() {
        return themes;
    }
}
