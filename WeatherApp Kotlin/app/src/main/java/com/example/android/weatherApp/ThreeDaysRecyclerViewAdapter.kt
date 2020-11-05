package com.example.android.weatherApp

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherApp.retrofit.MainData
import java.time.LocalDate

class ThreeDaysRecyclerViewAdapter(private val context: Context?) : RecyclerView.Adapter<ThreeDaysRecyclerViewAdapter.ViewHolder>() {
    private var mainData: Array<MainData>? = null
    private var count = 0
    fun setDataThreeDaysFragment(values: Array<MainData>?) {
        mainData = values
        count = 0
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.three_days_list_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mainData != null) {
            holder.imageView.setImageDrawable(MainActivity.weatherIcons[mainData!![count].weathers?.get(0)!!.weatherIcon])
        }
        if (mainData != null) {
            val date = LocalDate.parse(mainData!![count].getDate())
            val dayOfMonth = date.dayOfMonth.toString() + " " + date.month
            holder.textView2.text = dayOfMonth
        }
        if (mainData != null) {
            val date = LocalDate.parse(mainData!![count].getDate())
            val dayOfWeek = date.dayOfWeek
            holder.textView1.text = dayOfWeek.toString()
        }
        if (mainData != null) {
            holder.textView3.text = mainData!![count].mainDataValues.getTemp()
            count += 8
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView1: TextView = itemView.findViewById(R.id.day_of_week)
        var textView2: TextView = itemView.findViewById(R.id.day_of_month)
        var textView3: TextView = itemView.findViewById(R.id.temperature)
        var imageView: ImageView = itemView.findViewById(R.id.weather_condition)

    }
}