package com.example.pumpapp

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView




class TrainingData{
    lateinit var name: String
    var exce_1: String = ""
    var exce_2: String = ""
    var exce_3: String = ""
    var exce_4:String=""
    var exce_5: String = ""
    var exce_6: String = ""
    var exce_7: String = ""
    var exce_8: String = ""
    var exce_9: String = ""
}



class TrainigAdapter(private val dataSet:ArrayList<TrainingData>): RecyclerView.Adapter<TrainigAdapter.ViewHolder>()
{
    var TAG="trainingAdapter"


    class ViewHolder(v:View):RecyclerView.ViewHolder(v) {
        val nameExcercise: TextView = v.findViewById<TextView>(R.id.nameTraining)
        val button:Button=v.findViewById(R.id.start_training)
        var context=v.getContext()




    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.training_holder, viewGroup, false)

        return ViewHolder(view)
    }
    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val dataFromDatabase:TrainingData=dataSet[position]
        viewHolder.nameExcercise.text = dataFromDatabase.name
        viewHolder.button.setOnClickListener()
        {
            Log.d(TAG,"The button has been clicked with ${dataFromDatabase.name}")
            var dataInString:String

            if(dataSet[position].exce_2=="")
            {
                dataInString=dataSet[position].exce_1
            }
            else if(dataSet[position].exce_3=="")
            {
                dataInString=dataSet[position].exce_1+"!"+dataSet[position].exce_2
            }
            else if(dataSet[position].exce_4=="")
            {
                dataInString=dataSet[position].exce_1+"!"+dataSet[position].exce_2+"!"+dataSet[position].exce_3
            }
            else if(dataSet[position].exce_5=="")
            {
                dataInString=dataSet[position].exce_1+"!"+dataSet[position].exce_2+"!"+dataSet[position].exce_3+"!"+dataSet[position].exce_4
            }
            else if(dataSet[position].exce_6=="")
            {
                dataInString=dataSet[position].exce_1+"!"+dataSet[position].exce_2+"!"+dataSet[position].exce_3+"!"+dataSet[position].exce_4+"!"+dataSet[position].exce_5
            }
            else if(dataSet[position].exce_7=="")
            {
                dataInString=dataSet[position].exce_1+"!"+dataSet[position].exce_2+"!"+dataSet[position].exce_3+"!"+dataSet[position].exce_4+"!"+dataSet[position].exce_5+"!"+dataSet[position].exce_6
            }
            else if(dataSet[position].exce_8=="")
            {
                dataInString=dataSet[position].exce_1+"!"+dataSet[position].exce_2+"!"+dataSet[position].exce_3+"!"+dataSet[position].exce_4+"!"+dataSet[position].exce_5+"!"+dataSet[position].exce_6+"!"+dataSet[position].exce_7
            }
            else if(dataSet[position].exce_9=="")
            {
                dataInString=dataSet[position].exce_1+"!"+dataSet[position].exce_2+"!"+dataSet[position].exce_3+"!"+dataSet[position].exce_4+"!"+dataSet[position].exce_5+"!"+dataSet[position].exce_6+"!"+dataSet[position].exce_7+"!"+dataSet[position].exce_8
            }
            else
            {
                dataInString=dataSet[position].exce_1+"!"+dataSet[position].exce_2+"!"+dataSet[position].exce_3+"!"+dataSet[position].exce_4+"!"+dataSet[position].exce_5+"!"+dataSet[position].exce_6+"!"+dataSet[position].exce_7+"!"+dataSet[position].exce_8+"!"+dataSet[position].exce_9
            }




            val intent= Intent(viewHolder.context,Training::class.java)
            intent.putExtra("training",dataInString)
            Log.d(TAG, dataInString)
            viewHolder.context.startActivity(intent)

        }


    }


    override fun getItemCount(): Int {
        return dataSet.size
    }



}
