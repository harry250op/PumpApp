package com.example.pumpapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private val TAG="mainActivity"
    @SuppressLint("Recycle", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adsView=findViewById<AdView>(R.id.adView)
       val textViewName=findViewById<TextView>(R.id.textViewHello)
        var userName=""





        val databaseUser=baseContext.openOrCreateDatabase("user_info", Context.MODE_PRIVATE,null)

        try{
            val cursor:Cursor=databaseUser.rawQuery("SELECT * from user ",null)

            cursor.use {
                if(it.moveToFirst())
                {
                    with(cursor) {
                        userName = getString(1)
                        Log.d(TAG, "The data has been download  with$userName")

                    }
                    }

            }
            cursor.close()
        }catch ( e:Exception)
        {
            Log.d(TAG, e.toString())
            //database dont exist
            excercise_database_creating()
            training_database_creating()
            val sqlcreatingtable1="CREATE TABLE user(_id INTEGER PRIMARY KEY NOT NULL, name TEXT,age INTEGER,sex TEXT,weight INTEGER,height INTEGER)"
            databaseUser?.execSQL(sqlcreatingtable1)
                Log.d(TAG, "The database exist but it's empty")
                    databaseUser.close()
                val intent= Intent(this,RegistrationActivity::class.java)
                startActivityForResult(intent,1)

        }
        MobileAds.initialize(this) {}
        val adRequest:AdRequest = AdRequest.Builder().build()
        adsView.loadAd(adRequest)
        textViewName.text = "Hello $userName"

        databaseUser.close()




        }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                Log.d(TAG,"The user has sent the data")



            }
        }
    }

    fun excercise_database_creating()
    {
        Log.d(TAG,"Creating database for excercises")
        val databaseExcercise=baseContext.openOrCreateDatabase("exce", Context.MODE_PRIVATE,null)
        val sqlcreatingtable1="CREATE TABLE exce(_id INTEGER PRIMARY KEY NOT NULL, name TEXT,type TEXT)"
        databaseExcercise?.execSQL(sqlcreatingtable1)

        val user = ContentValues()

        user.put("name","Squats" )
        user.put("type", "Legs")

                databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()

        user.put("name","Lunges" )
        user.put("type", "Legs")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()
        user.put("name","Dumbbell rows" )
        user.put("type", "Backs")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()
        user.put("name","Side planks" )
        user.put("type", "Abs")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        databaseExcercise.close()
        Log.d(TAG,"The database for excercise has been created")

    }

    fun training_database_creating()
    {
        Log.d(TAG,"Creating database for trainings")
        val databaseTraining=baseContext.openOrCreateDatabase("training", Context.MODE_PRIVATE,null)
        //format in database id_excercise|reps|break
        val sqlcreatingtable1="CREATE TABLE exce(_id INTEGER PRIMARY KEY NOT NULL, name TEXT,exce_1 TEXT,exce_2 TEXT,exce_3 TEXT)"
        databaseTraining?.execSQL(sqlcreatingtable1)

        val user = ContentValues()

        user.put("name","Monday" )
        user.put("exce_1", "1|3|90")
        user.put("exce_2","3|2|60")


        databaseTraining.insert(
            "exce",
            null,
            user
        )
        databaseTraining.close()
        Log.d(TAG,"The database for training has been created")
    }




    }


