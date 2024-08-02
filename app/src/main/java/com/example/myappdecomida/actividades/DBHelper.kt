package com.example.myappdecomida.actividades

import android.content.Context
import  android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Userdata", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db?.execSQL("CREATE TABLE Userdata (username  TEXT PRIMARY KEY , password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Userdata")
        onCreate(db)
    }

    fun insertData(username: String, password: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
cv.put("username", username)
cv.put("password", password)
        val result =db.insert("Userdata", null, cv)
        if(result == -1.toLong())
        {
            return false
        }
        return true
    }


    fun checkuserpass(username: String, password: String): Boolean {
        val db = this.writableDatabase
        val query = "select * from Userdata where username = '$username' and password = '$password'"
        val cursor = db.rawQuery(query, null)
        if(cursor.count <= 0)
        {
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }
}