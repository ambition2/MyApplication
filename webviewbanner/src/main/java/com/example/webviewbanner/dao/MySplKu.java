package com.example.webviewbanner.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by QinQinBaoBei on 2017/12/4.
 */

public class MySplKu extends SQLiteOpenHelper {

 //创建数据库
    public MySplKu(Context context) {
        super(context,"shilei.db",null, 1);
    }
  //默认数据
    @Override
    public void onCreate(SQLiteDatabase db) {
           db.execSQL("create table lei(title text)");
        ContentValues values = new ContentValues();
        values.put("title","羊毛衫男");
        //values.put("title","衬衣");
        db.insert("lei",null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
