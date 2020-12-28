package com.example.pumpapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView



class Creating_training_adapter(val dataSet: ArrayList<Excercise>) : RecyclerView.Adapter<Creating_training_adapter.ViewHolder>()
{
    var time=0
    var reps=0
    val TAG="adapter_creating"

    class ViewHolder(v: View):RecyclerView.ViewHolder(v) {

        val nameExcercise = v.findViewById<TextView>(R.id.textViewNameOfExcercise)
        val typeExcercise=v.findViewById<TextView>(R.id.textViewType)
        val reps=v.findViewById<TextView>(R.id.textViewReps)
        val time=v.findViewById<TextView>(R.id.textViewTime)
        val plusReps=v.findViewById<Button>(R.id.buttonAddingReps)
        val minusReps=v.findViewById<Button>(R.id.buttonDeletingReps)
        val plusTime=v.findViewById<Button>(R.id.buttonAddingTime)
        val minusTime=v.findViewById<Button>(R.id.buttonDeletingTime)
        val addExcercise=v.findViewById<Button>(R.id.ButtonAddingExcercise)
        var context=v.getContext()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.training_adding_adapter, viewGroup, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Creating_training_adapter.ViewHolder, position: Int) {
        val dataFromDatabase:Excercise=dataSet[position]
        holder.nameExcercise.text = dataFromDatabase.name_of_excercise
        holder.typeExcercise.text=dataFromDatabase.type_of_excercise
        holder.setIsRecyclable(false)
        holder.plusReps.setOnClickListener()
        {
            reps+=1
            holder.reps.text= "Reps: $reps"
        }
        holder.minusReps.setOnClickListener()
        {
            reps-=1
            holder.reps.text= "Reps: $reps"
        }
        holder.plusTime.setOnClickListener()
        {
            time+=30
            holder.time.text= "Time: $time"
        }
        holder.minusTime.setOnClickListener()
        {
            time-=30
            holder.time.text= "Time: $time"
        }
        holder.addExcercise.setOnClickListener()
        {
            holder.plusTime.isClickable = false
            holder.minusTime.isClickable = false
            holder.plusReps.isClickable = false
            holder.minusReps.isClickable = false
            holder.addExcercise.isClickable=false
                holder.addExcercise.setBackgroundColor(Color.GREEN)
        val intent=Intent("custom-message")
        intent.putExtra("exce","${dataFromDatabase.id}|$reps|$time")
           Log.d(TAG,"dog")
            LocalBroadcastManager.getInstance(holder.context).sendBroadcast(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}