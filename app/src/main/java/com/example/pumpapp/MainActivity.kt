package com.example.pumpapp

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val TAG="mainActivity"
    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val databaseUser=baseContext.openOrCreateDatabase("user_info", MODE_PRIVATE,null)
        try{
            val test="SELECT * from user"
            databaseUser?.execSQL(test)
        }catch ( e:Exception)
        {
            //database dont exist
            val sqlcreatingtable1="CREATE TABLE user(_id INTEGER PRIMARY KEY NOT NULL, name TEXT,age INT,sex TEXT,weight INT,height INT)"
            databaseUser?.execSQL(sqlcreatingtable1)
                Log.d(TAG, "The database exist but it's empty")
                val intent= Intent(this,RegistrationActivity::class.java)
                startActivityForResult(intent,1)

        }


        }



    }

