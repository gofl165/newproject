package com.example.msi.newproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    final static String TAG="SQLiteDBTest";
//강의자료참고
    public DBHelper(Context context) {
        super(context, UserContract.DB_NAME, null, UserContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,getClass().getName()+".onCreate()");
        db.execSQL(UserContract.Users.CREATE_TABLE);
        db.execSQL(UserContract.Users.CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(TAG,getClass().getName() +".onUpgrade()");
        db.execSQL(UserContract.Users.DELETE_TABLE);
        db.execSQL(UserContract.Users.DELETE_TABLE);
        onCreate(db);
    }

    public long insertUserByMethod(String name,String address, String phone,String img) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserContract.Users.KEY_NAME, name);
        values.put(UserContract.Users.KEY_ADDRESS, address);
        values.put(UserContract.Users.KEY_PHONE,phone);
        values.put(UserContract.Users.KEY_IMGNAME,img);
        return db.insert(UserContract.Users.TABLE_NAME,null,values);
    }
    public long insertUserByMethod2(String name,String price, String detail,String img) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserContract.Users.KEY_NAME, name);
        values.put(UserContract.Users.KEY_PRICE, price);
        values.put(UserContract.Users.KEY_DETAIL,detail);
        values.put(UserContract.Users.KEY_IMGNAME,img);
        return db.insert(UserContract.Users.TABLE_NAME2,null,values);
    }

    public Cursor getAllUsersByMethod() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(UserContract.Users.TABLE_NAME,null,null,null,null,null,null);
    }
    public Cursor getAllUsersByMethod2() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(UserContract.Users.TABLE_NAME2,null,null,null,null,null,null);
    }


}
