package com.example.pumpapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Training : AppCompatActivity() {
    private var iteratorReps = 0
    private var iteratorExce = 0
    var reps = 0
    var time = 0
    var weight = 0.0
    private var nameOfExcercise = ""
    lateinit var textViewName: TextView
    lateinit var textViewReps: TextView
    lateinit var textViewWeight: TextView
    lateinit var training: Array<String>
    lateinit var stoper: Stoper
    var time_begin: Long = 0
    var full_weight = 0.0
    lateinit var textViewTraining: TextView
    lateinit var buttonAddingWeight: Button
    lateinit var buttonAddingReps: Button
    lateinit var buttonSubtracionWeight: Button
    private lateinit var buttonSubtracionReps: Button
    lateinit var buttonNext: Button
    lateinit var buttonYoutube: Button
    lateinit var databaseExce: SQLiteDatabase


    val Tag = "training"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.training)
        var toCatchingData: String? = intent.getStringExtra("training")


        Log.d(Tag, "Dataset has been recieved $toCatchingData")


        training = toCatchingData?.split("!")?.toTypedArray()!!


        Log.d(Tag, "Dataset has been recieved $training")
        databaseExce = baseContext.openOrCreateDatabase("exce_done", Context.MODE_PRIVATE, null)

        textViewName = findViewById(R.id.NameofExcercise)
        textViewReps = findViewById(R.id.Rep)
        textViewTraining = findViewById(R.id.Timer)
        textViewWeight = findViewById(R.id.Weight)
        buttonAddingReps = findViewById(R.id.adding_reps)
        buttonAddingWeight = findViewById(R.id.adding_weight)
        buttonSubtracionReps = findViewById(R.id.deleting_reps)
        buttonSubtracionWeight = findViewById(R.id.deleting_weight)
        buttonNext = findViewById(R.id.next_excercise)
        buttonYoutube = findViewById(R.id.go_to_Youtube)

        start()

        buttonSubtracionWeight.setOnClickListener {
            weight -= 2.5
            textViewWeight.text = "$weight Kg"

        }
        buttonAddingWeight.setOnClickListener {
            weight += 2.5
            textViewWeight.text = "$weight Kg"

        }
        buttonSubtracionReps.setOnClickListener {
            if (iteratorReps <= reps - 1) {
                reps -= 1
                textViewReps.text = "$iteratorReps /$reps Reps"
            }


        }
        buttonAddingReps.setOnClickListener {
            reps += 1
            textViewReps.text = "$iteratorReps /$reps Reps"

        }
        buttonNext.setOnClickListener {
            update()
        }
        buttonYoutube.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/results?search_query=$nameOfExcercise+how+to+do+gym")
            )
            startActivity(intent)
        }


    }

    @SuppressLint("SetTextI18n")
    fun start() {
        time_begin = System.currentTimeMillis()
        val data: Array<String> = training[iteratorExce].split("|").toTypedArray()
        nameOfExcercise = gettingNameOfExcercise(data[0])
        textViewName.text = nameOfExcercise
        reps = data[1].toInt()
        textViewReps.text = "$iteratorReps /$reps Reps"
        time = data[2].toInt()
        textViewWeight.text = "$weight Kg"
        stoper = Stoper((time * 1000).toLong(), 1, textViewTraining)
        stoper.start()
        iteratorReps++
        iteratorExce = 0

    }

    @SuppressLint("SetTextI18n")
    fun update() {
        val user = ContentValues()
        val data: Array<String> = training[iteratorExce].split("|").toTypedArray()
        user.put("id_exce", data[0].toInt())
        user.put("weight", weight.toString())
        user.put("time", System.currentTimeMillis().toString())

        databaseExce.insert(
            "exce_done",
            null,
            user
        )
        user.clear()

        if (iteratorReps >= reps || iteratorReps == 0) {
            //when the all reps of excer was done
            iteratorExce++
            if (training.size <= iteratorExce) {
                //when all exce was done
                full_weight += weight
                windowAlert()
            } else {

                stoper.cancel()
                full_weight += weight

                val data: Array<String> = training[iteratorExce].split("|").toTypedArray()
                nameOfExcercise = gettingNameOfExcercise(data[0])
                textViewName.text = nameOfExcercise
                reps = data[1].toInt()

                iteratorReps = 1

                textViewReps.text = "$iteratorReps /${reps.toString()} Reps"
                time = data[2].toInt()
                textViewWeight.text = "${weight.toString()} Kg"
                stoper = Stoper((time * 1000).toLong(), 1, textViewTraining)
                stoper.start()
            }


        } else {
            //when rep was done
            full_weight += weight
            stoper.cancel()
            iteratorReps++
            textViewReps.text = "$iteratorReps /${reps.toString()} Reps"
            stoper = Stoper((time * 1000).toLong(), 1, textViewTraining)
            stoper.start()

        }


    }


    private fun gettingNameOfExcercise(id: String): String {
        var nameExce: String = ""
        val databaseExce = baseContext.openOrCreateDatabase("exce", Context.MODE_PRIVATE, null)
        val cursor: Cursor = databaseExce.rawQuery("SELECT name from exce WHERE _id=$id ", null)

        cursor.use {
            if (it.moveToFirst()) {
                with(cursor) {
                    nameExce = getString(0)
                    Log.d(Tag, "The excercise name is$nameExce")

                }
            }

        }
        cursor.close()
        databaseExce.close()
        return nameExce

    }

    @SuppressLint("ResourceType", "SetTextI18n")
    fun windowAlert() {
        val time_ending = System.currentTimeMillis()
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.after_workout)
        val timeWorkout = dialog.findViewById<TextView>(R.id.TextViewTimeWorkout)
        val weightWourkout = dialog.findViewById<TextView>(R.id.TextViewWeightWorkout)
        val caloriesWourkout = dialog.findViewById<TextView>(R.id.TextViewCaloriesWorkout)
        val buttonToMenu = dialog.findViewById<Button>(R.id.Button_return_to_menu)
        weightWourkout.text = "${full_weight.toString()} kg"
        timeWorkout.text = changingTime(time_ending - time_begin)
        caloriesWourkout.text = "${((time_ending - time_begin) / 7500)} kcal"

        buttonToMenu.setOnClickListener {
            databaseExce.close()
            setResult(RESULT_OK)
            finish()

        }

        dialog.show()


    }

    private fun changingTime(time: Long): String {
        val minutes = time / 60000
        val seconds = (time / 1000) % 60
        return "${minutes.toString()} min : ${seconds.toString()} sec"
    }


}


