package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "RentHouse.db";
    private static final String TABLE_NAME ="Reservation";
    private static final String COL_1 ="Rno";
    private static final String COL_2 ="Name";
    private static final String COL_3 ="City";
    private static final String COL_4 ="HouseNo";
    private static final String COL_5 ="Duration";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(" create table " + TABLE_NAME + " (Rno INTEGER PRIMARY KEY AUTOINCREMENT , Name TEXT , City TEXT , HouseNo INTEGER , Duration INTEGER  ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name ,String city ,String hNo ,String duration ){


        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,city);
        contentValues.put(COL_4,hNo);
        contentValues.put(COL_5,duration);

         long result=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

         if(result==-1) {
             return false;
         }
         else {
             return true;
         }
    }

    public Cursor getAllData(){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+ TABLE_NAME,null);
        return res;
    }



    public boolean updateData(String rno,String name ,String city ,String hNo ,String duration ){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,rno);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,city);
        contentValues.put(COL_4,hNo);
        contentValues.put(COL_5,duration);

        sqLiteDatabase.update(TABLE_NAME , contentValues,"Rno = ?", new String[] { rno });

        return true;
    }

    public boolean deleteData(String rno,String name ,String city ,String hNo ,String duration ){

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,rno);
       contentValues.put(COL_2,name);
        contentValues.put(COL_3,city);
        contentValues.put(COL_4,hNo);
        contentValues.put(COL_5,duration);

        sqLiteDatabase.delete(TABLE_NAME,"Rno = ?", new String[] { rno });

        return true;
    }
}
