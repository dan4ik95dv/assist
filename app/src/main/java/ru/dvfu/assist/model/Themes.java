package ru.dvfu.assist.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by user on 23.01.2017.
 */

public class Themes {
    @SerializedName("themes")
    ArrayList<Theme> themes;

    public ArrayList<Theme> getThemesList() {
        return themes;
    }
}
