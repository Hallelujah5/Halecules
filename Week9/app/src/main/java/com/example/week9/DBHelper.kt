package com.example.week9


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import androidx.core.content.contentValuesOf


var DATABASENAME = "UniManagerment"
var TABLENAME = "Student"
var COL_NAME = "name"
var COL_ID = "id"

class DBHelper(var context: Context): SQLiteOpenHelper(context, DATABASENAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var createTable = "CREATE TABLE " + TABLENAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME  + " VARCHAR(256))"
        Log.d("debug", "DATABASE RAN!!!!!!!!!!!!!!")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        TODO("Not yet implemented")
    }


    fun insertData(studentName: String){
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, studentName)
        val result = database.insert(TABLENAME, null, contentValues)
        if (result ==(0).toLong()){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }



    @SuppressLint("Range", "Recycle")
    fun readData(): MutableList<String> {
        val db = this.readableDatabase
        val query =  "Select * from $TABLENAME"
        val result = db.rawQuery(query,null)

        val list: MutableList<String> = ArrayList()
        if (result.moveToFirst()){
            do {
                var name = result.getString(result.getColumnIndex(COL_NAME))
                list.add(name)
            }
            while (result.moveToNext())
        }
        return list
    }



}