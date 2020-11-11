package com.example.pumpapp

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    private val TAG="mainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val databaseUser=baseContext.openOrCreateDatabase("user_info", MODE_PRIVATE,null)
        val mCursor:Cursor=databaseUser.rawQuery("SELECT * FROM user" , null);
        if(!mCursor.moveToFirst()) {
            val sqlcreatingtable1="CREATE TABLE user(_id INTEGER PRIMARY KEY NOT NULL, name TEXT,age INT,sex TEXT,weight INT,height INT)"
            databaseUser?.execSQL(sqlcreatingtable1)
            Log.d(TAG, "The database exist but it's empty")
        }
        else
        {
            //TODO:Adding if user already install app
        }

        Log.d(TAG,"creating database $databaseUser")
    }
}