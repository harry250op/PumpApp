package com.example.pumpapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class Excercise()
{
    var id=""
    var name_of_excercise=""
    var type_of_excercise=""
}


class Training_creating : AppCompatActivity() {
    val TAG="trainign_creating"
    var listOfExce=ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training_adding)
        val recycle_view=findViewById<RecyclerView>(R.id.recyclerViewAdding)
        val adsView=findViewById<AdView>(R.id.adView2)

        MobileAds.initialize(this) {}
        val adRequest: AdRequest = AdRequest.Builder().build()
        adsView.loadAd(adRequest)
        LocalBroadcastManager.getInstance(this).registerReceiver(broadCastReceiver, IntentFilter("custom-message"))
        recycle_view.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recycle_view.adapter=Creating_training_adapter(getting_dataset())


    }
    val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {

            val row: String? = intent?.getStringExtra("exce")
            Log.d(TAG,"The data has been receive $row")
            if (row != null) {
                listOfExce.add(row)
            }
        }
    }


    fun getting_dataset(): ArrayList<Excercise> {

        val databaseExcercise=baseContext.openOrCreateDatabase("exce", Context.MODE_PRIVATE,null)
        val cursor: Cursor =databaseExcercise.rawQuery("SELECT * from exce ",null)
        val dataSet=ArrayList<Excercise>()
        cursor.use {
            if (it.moveToFirst()) {
                with(cursor) {
                    do {


                        val exce = Excercise()
                        exce.id = getString(0)
                        exce.name_of_excercise = getString(1)
                        exce.type_of_excercise = getString(2)

                        Log.d(TAG, "The data has been download  with${exce.name_of_excercise}")
                        dataSet.add(exce)
                    }while(it.moveToNext())
                }
            }
        }


        cursor.close()
        return dataSet
    }
}