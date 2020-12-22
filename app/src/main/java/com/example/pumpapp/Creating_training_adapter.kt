package com.example.pumpapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class Creating_training_adapter(val dataSet: ArrayList<Excercise>) : RecyclerView.Adapter<Creating_training_adapter.ViewHolder>()
{

    class ViewHolder(v: View):RecyclerView.ViewHolder(v) {

        val nameExcercise: TextView = v.findViewById<TextView>(R.id.textViewNameOfExcercise)
        val typeExcercise=v.findViewById<TextView>(R.id.textViewType)
        var context=v.getContext()




    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.training_adding_adapter, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Creating_training_adapter.ViewHolder, position: Int) {
        val dataFromDatabase:Excercise=dataSet[position]
        holder.nameExcercise.text = dataFromDatabase.name_of_excercise
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}