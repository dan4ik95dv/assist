package ru.dvfu.assist.model;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by user on 23.01.2017.
 */
@Table(database = MyDatabase.class)
public class Profile extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    @SerializedName("token")
    String token;
    @Column
    @SerializedName("fullname")
    String fullname;
    @Column
    @SerializedName("course")
    int course;
    @Column
    @SerializedName("department")
    String department;
    @Column
    @SerializedName("section")
    String section;
    @Column
    @SerializedName("type")
    String type;

    public String getToken() {
        return token;
    }

    public String getFullname() {
        return fullname;
    }

    public int getCourse() {
        return course;
    }

    public String getDepartment() {
        return department;
    }

    public String getSection() {
        return section;
    }

    public String getType() {
        return type;
    }
}
