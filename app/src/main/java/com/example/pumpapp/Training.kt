package com.example.pumpapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.training_holder.*


class Training: AppCompatActivity() {
    var iterator_training=0
    var reps=0
    var time=0
    var weight=0.0
    var name_of_excercise=""
    lateinit var textViewName:TextView
    lateinit var textViewReps:TextView
    lateinit var textViewWeight:TextView
    lateinit var training:Array<String>
    lateinit var textViewTraining:TextView
    lateinit var buttonAddingWeight:Button
    lateinit var buttonAddingReps:Button
    lateinit var buttonSubtracionWeight:Button
    lateinit var buttonSubtracionReps:Button
    lateinit var buttonNext:Button
    lateinit var buttonYoutube:Button


    val Tag="training"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.training)
        var toCatchingData: String? =intent.getStringExtra("training")

        Log.d(Tag,"Dataset has been recieved $toCatchingData")


        training= toCatchingData?.split("!")?.toTypedArray()!!
        Log.d(Tag,"Dataset has been recieved $training")
        textViewName= findViewById(R.id.NameofExcercise)
        textViewReps=findViewById(R.id.Rep)
        textViewTraining=findViewById(R.id.Timer)
        textViewWeight=findViewById(R.id.Weight)
        buttonAddingReps=findViewById(R.id.adding_reps)
        buttonAddingWeight=findViewById(R.id.adding_weight)
        buttonSubtracionReps=findViewById(R.id.deleting_reps)
        buttonSubtracionWeight=findViewById(R.id.deleting_weight)
        buttonNext=findViewById(R.id.next_excercise)
        buttonYoutube=findViewById(R.id.go_to_Youtube)

        update()

        buttonSubtracionWeight.setOnClickListener {
            weight -= 2.5
            textViewWeight.text= "${weight.toString()} Kg"

        }
        buttonAddingWeight.setOnClickListener {
            weight += 2.5
            textViewWeight.text= "${weight.toString()} Kg"

        }
        buttonSubtracionReps.setOnClickListener {
            reps -= 1
            textViewReps.text= "${reps.toString()} Reps"

        }
        buttonAddingReps.setOnClickListener {
            reps += 1
            textViewReps.text= "${reps.toString()} Reps"

        }
        buttonNext.setOnClickListener{
            update()
        }
        buttonYoutube.setOnClickListener{
           var intent=Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=$name_of_excercise+how+to+do"))
            startActivity(intent)
        }


    }






    fun finish_Training()
    {

    }
    @SuppressLint("SetTextI18n")
    fun update()
    {
        var data: Array<String> =training[iterator_training].split("|").toTypedArray()
        name_of_excercise=getting_name_of_excercise(data[0])
        textViewName.text=name_of_excercise
        reps=data[1].toInt()
        textViewReps.text= "${reps.toString()} Reps"
        time=data[2].toInt()
        textViewWeight.text= "${weight.toString()} Kg"
        val stoper=Stoper((time*1000).toLong(),1,textViewTraining)
        stoper.start()



iterator_training++
     if(training.size<=iterator_training)
     {
         setResult(RESULT_OK,intent)
         finish()

     }


    }

    @SuppressLint("SetTextI18n")
    fun next_exce()
    {

        textViewWeight.text= "${time.toString()} Sec"
        textViewReps.text= "${reps.toString()} Reps"
        val stoper=Stoper((time*1000).toLong(),1,textViewTraining)
        stoper.start()
    }

    fun getting_name_of_excercise(id:String): String
    {   var name_exce:String=""
        val databaseExce=baseContext.openOrCreateDatabase("exce", Context.MODE_PRIVATE,null)
        val cursor: Cursor =databaseExce.rawQuery("SELECT name from exce WHERE _id=$id ",null)

        cursor.use {
            if(it.moveToFirst())
            {
                with(cursor) {
                    name_exce = getString(0)
                    Log.d(Tag, "The excercise name is$name_exce")

                }
            }

        }
        cursor.close()
        databaseExce.close()
        return name_exce

    }

}