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

class FiveDaysRecyclerViewAdapter(private val context: Context?) : RecyclerView.Adapter<FiveDaysRecyclerViewAdapter.ViewHolder>() {
    private var mainData: Array<MainData>? = null
    private var count = 0
    fun setDataFiveDayFragment(values: Array<MainData>?) {
        count = 0
        mainData = values
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.seven_days_list_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mainData != null) {
            holder.imageView.setImageDrawable(MainActivity.Companion.weatherIcons[mainData!![count].weathers?.get(0)!!.weatherIcon])
        }
        if (mainData != null) {
            val rowDate = LocalDate.parse(mainData!![count].getDate())
            val date = rowDate.dayOfMonth.toString() + "." + rowDate.monthValue + "." + rowDate.year
            holder.textView1.text = date
        }
        if (mainData != null) {
            holder.textView2.text = mainData!![count].mainDataValues.getTemp()
        }
        count += 8
    }

    override fun getItemCount(): Int {
        return 5
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView1: TextView = itemView.findViewById(R.id.date)
        var textView2: TextView = itemView.findViewById(R.id.temperature)
        var imageView: ImageView = itemView.findViewById(R.id.weather_condition)
    }
}