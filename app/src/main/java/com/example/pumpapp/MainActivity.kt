package com.example.pumpapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val TAG="mainActivity"
    @SuppressLint("Recycle", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adsView=findViewById<AdView>(R.id.adView)

       var textViewName=findViewById<TextView>(R.id.textViewHello)




        var userName=""





        val databaseUser=baseContext.openOrCreateDatabase("user_info", Context.MODE_PRIVATE,null)
        Log.d(TAG,"The data has been download ")
        try{
            val cursor:Cursor=databaseUser.rawQuery("SELECT * from user ",null)
            Log.d(TAG,"The data has been download ")
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
    }


