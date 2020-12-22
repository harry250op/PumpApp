package com.example.pumpapp

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Excercise()
{
    var id=""
    var name_of_excercise=""
    var type_of_excercise=""
}


class Training_creating : AppCompatActivity() {
    val TAG="Trainign_creating"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training_adding)
        val recycle_view=findViewById<RecyclerView>(R.id.recyclerViewAdding)
        recycle_view.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recycle_view.adapter=Creating_training_adapter(getting_dataset())


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