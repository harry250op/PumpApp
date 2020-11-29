package com.example.pumpapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TrainingData{
    var name: String = ""
    var exce_1: String = ""
    var exce_2: String = ""
    var exce_3: String = ""
}



class TrainigAdapter( private val dataSet:ArrayList<TrainingData>): RecyclerView.Adapter<TrainigAdapter.ViewHolder>()
{

    class ViewHolder(v:View):RecyclerView.ViewHolder(v) {
        val nameExcercise: TextView = v.findViewById<TextView>(R.id.nameTraining)


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
        var holer:TrainingData=dataSet[position]
        viewHolder.nameExcercise.text = holer.name
    }


    override fun getItemCount(): Int {
        return dataSet.size
    }
}