package ru.dvfu.assist.model;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by user on 16.01.2017.
 */

@Table(database = MyDatabase.class)
public class Theme extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    @SerializedName("id")
    int id;
    @SerializedName("name")
    @Column
    String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
