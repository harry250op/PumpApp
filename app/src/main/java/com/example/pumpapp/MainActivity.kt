package com.example.pumpapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_getting_data_user.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val TAG="mainActivity"
    var userName:String=""
    lateinit var textViewName: TextView
    @SuppressLint("Recycle", "SetTextI18n", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycle_view=findViewById<RecyclerView>(R.id.list_recycle_view)
        val adsView=findViewById<AdView>(R.id.adView)
        textViewName=findViewById<TextView>(R.id.textViewHello)






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
            excercies_done_database_creating()
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

        recycle_view.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recycle_view.adapter=TrainigAdapter(training_database_getting())






        }


    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                Log.d(TAG,"The user has sent the data")
                val returnedResult = data?.dataString
                textViewName.text="Hello $returnedResult"



            }
        }

    }

    private fun excercise_database_creating()
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
        user.clear()
            user.put("name","Crunch" )
        user.put("type", "Abs")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()
        user.put("name","Sit-Up with cable" )
        user.put("type", "Abs")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()
        user.put("name","High Leg Pull-In" )
        user.put("type", "Abs")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.put("name","Pull-up" )
        user.put("type", "Back")
        user.clear()
        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.put("name","Inverted Rows" )
        user.put("type", "Back")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()
        user.put("name","Chin-Ups" )
        user.put("type", "Back")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()
        user.put("name","Chest Press" )
        user.put("type", "Chest")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()
        user.put("name","Pullover" )
        user.put("type", "Chest")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()
        user.put("name","Push-Ups" )
        user.put("type", "Chest")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()
        user.put("name","Arnold Press" )
        user.put("type", "Shoulder")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()
        user.put("name","Overhead Machine Press" )
        user.put("type", "Shoulder")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()
        user.put("name","Cable Curls" )
        user.put("type", "Biceps")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()
        user.put("name","Seated Dumbbell Curls" )
        user.put("type", "Biceps")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()
        user.put("name","Dips" )
        user.put("type", "Triceps")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()
        user.put("name","Skull Crushers" )
        user.put("type", "Triceps")

        databaseExcercise.insert(
            "exce",
            null,
            user
        )
        user.clear()
        databaseExcercise.close()
        Log.d(TAG,"The database for excercise has been created")

    }

    private fun training_database_getting():ArrayList<TrainingData>
    {
        val databaseTraining=baseContext.openOrCreateDatabase("training", Context.MODE_PRIVATE,null)
        val cursor:Cursor=databaseTraining.rawQuery("SELECT * from exce ",null)
        var dataSet=ArrayList<TrainingData>()
        cursor.use {
            if(it.moveToFirst())
            {
                with(cursor) {
                    val training=TrainingData()
                    training.name= getString(1)
                    training.exce_1= getString(2)
                    training.exce_2= getString(3)

                    Log.d(TAG, "The data has been download  with${training.name}")
                    dataSet.add(training)
                }
            }

        }
        cursor.close()
        return dataSet
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
    fun excercies_done_database_creating()
    {
        Log.d(TAG,"Creating database for done excercises")
        val databaseExcerciseDone=baseContext.openOrCreateDatabase("exce_done", Context.MODE_PRIVATE,null)
        val sqlcreatingtable1="CREATE TABLE exce(_id INTEGER PRIMARY KEY NOT NULL, id_exce INTEGER,weight INTEGER)"
        databaseExcerciseDone?.execSQL(sqlcreatingtable1)
    }




    }


