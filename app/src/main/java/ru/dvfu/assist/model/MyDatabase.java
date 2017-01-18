package ru.dvfu.assist.model;

import com.raizlabs.android.dbflow.annotation.Database;

import static ru.dvfu.assist.model.MyDatabase.NAME;
import static ru.dvfu.assist.model.MyDatabase.VERSION;

/**
 * Created by user on 16.01.2017.
 */
@Database(name = NAME, version = VERSION)
public class MyDatabase {
    public static final String NAME = "MyDB";
    public static final int VERSION = 1;
}

