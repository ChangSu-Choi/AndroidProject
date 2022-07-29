package com.example.Look_back;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {
    SQLiteHelper(Context context,
                 String name,
                 SQLiteDatabase.CursorFactory factory,
                 int version){
        super(context,name,factory,version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertDiary(String date , String diary){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO DIARY VALUES(?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,date);
        statement.bindString(2,diary);
        statement.executeInsert();
    }

    public void updateDiary(String diary, String date){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE DIARY SET diary =? WHERE date=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1,diary);
        statement.bindString(2,date);

        statement.execute();
        database.close();
    }

    public void deleteDiary(String date){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM DIARY WHERE date=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,date);

        statement.execute();
        statement.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i,int il){

    }
}
