package com.example.webviewbanner.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by QinQinBaoBei on 2017/12/4.
 */

public class MyUtils {

    private MySplKu mySplKu;

    public MyUtils(Context context){
        mySplKu = new MySplKu(context);
    }
//添加数据库
    public void add(String title){
        SQLiteDatabase writableDatabase = mySplKu.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title",title);
        writableDatabase.insert("lei",null,values);
       writableDatabase.close();
    }
    //查询数据库
    public List<String> select(){
        List<String> list = new ArrayList<>();
        SQLiteDatabase writableDatabase = mySplKu.getWritableDatabase();
        Cursor cursor = writableDatabase.query("lei", null, null, null, null, null, null);
        while(cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex("title"));
            list.add(title);
        }
        return list;
    }
    //清空数据库

    public void clearDataBase(){
        SQLiteDatabase writableDatabase = mySplKu.getWritableDatabase();
        writableDatabase.execSQL("DELETE FROM lei");
    }
}
