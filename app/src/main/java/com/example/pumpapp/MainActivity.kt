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
            val test="SELECT * from user "
            databaseUser.rawQuery(test,null)

        }catch ( e:Exception)
        {
            Log.d(TAG, e.toString())
            //database dont exist
            val sqlcreatingtable1="CREATE TABLE user (_id INTEGER PRIMARY KEY NOT NULL, name TEXT,age TEXT,sex TEXT,weight TEXT,height TEXT)"
            databaseUser?.execSQL(sqlcreatingtable1)
                Log.d(TAG, "The database exist but it's empty")

                val intent= Intent(this,RegistrationActivity::class.java)
                startActivityForResult(intent,1)

        }
        Log.d(TAG,"horse")



        }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                Log.d(TAG,"The user has sent the data")



            }
        }
    }
    }


