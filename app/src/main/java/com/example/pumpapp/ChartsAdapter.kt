package com.example.pumpapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.enums.Position
import com.anychart.graphics.vector.Anchor


class ChartsAdapter(val dataSet: Map<String, List<RepsDone>>): RecyclerView.Adapter<ChartsAdapter.ViewHolder>() {
    val TAG="chartAdapter"
    class ViewHolder(v: View):RecyclerView.ViewHolder(v) {
        var textViewDate=v.findViewById<TextView>(R.id.textViewDay)
        var textViewVolume=v.findViewById<TextView>(R.id.textViewVolume)
        var charts=v.findViewById<AnyChartView>(R.id.any_chart_view)


    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ChartsAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.charts_adapter, viewGroup, false)

        return ChartsAdapter.ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ChartsAdapter.ViewHolder, position: Int) {
        var i=0
        var cartesian:Cartesian=AnyChart.column()
        for((key, value) in dataSet)
        {
            if(i==position)
            {

                holder.textViewDate.text= "Date: ${value.get(0).time}"
                holder.textViewVolume.text="You lifted: ${countingVolume(value).toString()}"
                var column=cartesian.column(getting_charts(value))
                column.tooltip()
                    .titleFormat("{%X}")
                    .position(Position.CENTER_BOTTOM)
                    .offsetX(0.0)
                    .offsetY(5.0)
                    .format("\${%Value}{groupsSeparator: }")
                    holder.charts.setChart(cartesian)
            }
            i++
        }

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    private fun countingVolume(dateS: List<RepsDone>): Double {
        var volume = 0.0
        for(i in dateS)
        {  volume+=i.weight}
        return volume
    }
    private fun getting_charts(dateS: List<RepsDone>): ArrayList<DataEntry> {
        var date=ArrayList<DataEntry>()
        var mapsOfExce=dateS.groupBy { it.name_exce }
        for((key, value) in mapsOfExce)
        {
            var weight=0.0
                for(i in value)
                {  weight+=i.weight}
            date.add(ValueDataEntry(key, weight))

        }
        return date


    }
}