package com.example.pumpapp

import android.content.*
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pumpapp.Adapters.Creating_training_adapter
import com.example.pumpapp.Models.Excercise
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds


class Training_creating : AppCompatActivity() {
    val TAG = "training_creating"
    var listOfExce = ArrayList<String>()
    lateinit var textViewName: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training_adding)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAdding)
        val adsView = findViewById<AdView>(R.id.adView2)
        val saveButton: Button = findViewById(R.id.ButtonSaving)
        textViewName = findViewById(R.id.editTextNameForTraining)

        MobileAds.initialize(this) {}
        val adRequest: AdRequest = AdRequest.Builder().build()
        adsView.loadAd(adRequest)
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(broadCastReceiver, IntentFilter("custom-message"))
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = Creating_training_adapter(gettingDataset())


        saveButton.setOnClickListener()
        {
            addingToDatabase()
            val data = Intent()
            Log.d(TAG, "The database with training has been added")
            setResult(RESULT_OK, data)
            finish()

        }

    }

    private val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {

            val row: String? = intent?.getStringExtra("exce")
            Log.d(TAG, "The data has been receive $row")
            if (row != null) {
                listOfExce.add(row)
            }
        }
    }


    private fun gettingDataset(): ArrayList<Excercise> {

        val databaseExcercise = baseContext.openOrCreateDatabase("exce", Context.MODE_PRIVATE, null)
        val cursor: Cursor = databaseExcercise.rawQuery("SELECT * from exce ", null)
        val dataSet = ArrayList<Excercise>()
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
                    } while (it.moveToNext())
                }
            }
        }


        cursor.close()
        return dataSet
    }

    private fun addingToDatabase() {
        val databaseTraining =
            baseContext.openOrCreateDatabase("training", Context.MODE_PRIVATE, null)
        val user = ContentValues()

        user.put("name", textViewName.text.toString())
        val rangeFor = listOfExce.size
        Log.d(TAG, rangeFor.toString())
        for (i in 1..rangeFor) {
            Log.d(TAG, "test ${listOfExce[i - 1]}")
            user.put("exce_$i", listOfExce[i - 1])
        }
        for (i in rangeFor + 1..9) {
            user.put("exce_$i", "")
        }
        databaseTraining.insert("exce", null, user)
        databaseTraining.close()
    }
}