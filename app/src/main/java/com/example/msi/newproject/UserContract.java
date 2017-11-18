package com.example.msi.newproject;


import android.provider.BaseColumns;

public final class UserContract {
    public static final String DB_NAME="newuser.db";
    public static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UserContract() {}

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME="News";
        public static final String KEY_NAME = "Name";
        public static final String KEY_ADDRESS="Address";
        public static final String KEY_PHONE = "Phone";
        public static final String KEY_IMGNAME="Imgname";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                KEY_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_ADDRESS + TEXT_TYPE + COMMA_SEP +
                KEY_PHONE + TEXT_TYPE +COMMA_SEP +
                KEY_IMGNAME + TEXT_TYPE +" )";
//        public static final String CREATE_TABLE ="CREATE TABLE Users "+ " (" +
//                _ID + " INTEGER PRIMARY KEY"+", Name TEXT Address TEXT  Imgname TEXT)";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
