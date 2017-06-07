package com.nerdspoint.android.promoter.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by android on 5/10/2017.
 */

public class OfflineDB extends SQLiteOpenHelper{
    Context context;

    public OfflineDB(Context context) {
        super(context,"offlineDB.db", null, 1);
        this.context =context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void createHistory()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db == null) {
            return;
        }
        String sql = "CREATE TABLE IF NOT EXISTS History (LinkId Integer);";
        db.execSQL(sql);
        db.close();

    }

    public void insertHistory(String LinkId)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        if (db == null) {
            return;
        }
        String sql = "INSERT INTO History (LinkId) values ('"+LinkId+"');";
        db.execSQL(sql);
        db.close();
    }

    public Cursor getLinkIds()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        if(db== null)
        {
            return null;
        }
        return db.rawQuery("select LinkId from History",null);
    }
}
