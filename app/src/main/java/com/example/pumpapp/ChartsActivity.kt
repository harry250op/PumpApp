package com.example.pumpapp

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pumpapp.Adapters.ChartsAdapter
import java.text.SimpleDateFormat

class RepsDone() {
    var id_exce: String = ""
    var name_exce: String = ""
    var time: String = ""
    var weight: Float = 0.0F
}

class DayOfExcercise() {
    var day: String = ""
    var list = ArrayList<RepsDone>()
}

class ChartsActivity : AppCompatActivity() {
    val TAG = "ChartActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.charts_activity)
        var recyclerView = findViewById<RecyclerView>(R.id.RecycleView_charts)
        val toAdapter = dataToAdapter()
        Log.d(TAG, "test")

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = ChartsAdapter(toAdapter)
    }


    private fun openDatabase(): ArrayList<RepsDone> {
        val databaseTraining =
            baseContext.openOrCreateDatabase("exce_done", Context.MODE_PRIVATE, null)
        val cursor: Cursor = databaseTraining.rawQuery("SELECT * from exce_done ", null)
        val dataSet = ArrayList<RepsDone>()
        val typeOfDay: SimpleDateFormat = SimpleDateFormat("YYYY : MM : DD")
        cursor.use {
            if (it.moveToFirst()) {
                with(cursor) {
                    do {
                        val training = RepsDone()
                        training.id_exce = getString(1)
                        training.weight = getString(2).toFloat()
                        training.name_exce = getting_name_of_excercise(training.id_exce)
                        val timestamp = getString(3).toLong()
                        training.time = typeOfDay.format(timestamp)


                        Log.d(TAG, "The data has been download  with ${training.time}")
                        dataSet.add(training)
                    } while (it.moveToNext())
                }
            }

        }
        cursor.close()
        return dataSet

    }

    private fun dataToAdapter(): Map<String, List<RepsDone>> {
        val daysOfExcercise = ArrayList<DayOfExcercise>()
        val ass = DayOfExcercise()
        daysOfExcercise.add(ass)
        val database = openDatabase()
        var score = database.groupBy { it.time }
        return score


    }

    private fun getting_name_of_excercise(id: String): String {
        var name_exce: String = ""
        val databaseExce = baseContext.openOrCreateDatabase("exce", Context.MODE_PRIVATE, null)
        val cursor: Cursor = databaseExce.rawQuery("SELECT name from exce WHERE _id=$id ", null)

        cursor.use {
            if (it.moveToFirst()) {
                with(cursor) {
                    name_exce = getString(0)
                    Log.d(TAG, "The excercise name is $name_exce")

                }
            }

        }
        cursor.close()
        databaseExce.close()
        return name_exce

    }
}