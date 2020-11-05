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
import java.text.SimpleDateFormat
import java.util.*

class TodayRecyclerViewAdapter(var mContext: Context?) : RecyclerView.Adapter<TodayRecyclerViewAdapter.ViewHolder>() {
    var mainData: Array<MainData>? = null
    fun setDataTodayFragment(values: Array<MainData>?) {
        mainData = values
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(R.layout.today_list_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mainData != null) {
            holder.imageView.setImageDrawable(MainActivity.Companion.weatherIcons[mainData!![position].weathers?.get(0)!!.weatherIcon])
        }
        if (mainData != null) {
            holder.textView1.text = mainData!![position].mainDataValues.getTemp()
        }
        if (mainData != null) {
            val date = Date((mainData!![position].rawDate?.plus(timeZone!!))?.times(1000L)!!)
            val isoFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            isoFormat.timeZone = TimeZone.getTimeZone("UTC")
            holder.textView2.text = isoFormat.format(date)
        }
    }

    override fun getItemCount(): Int {
        return if (mainData != null) {
            mainData!!.size
        } else {
            1
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView1: TextView = itemView.findViewById(R.id.temperature)
        var textView2: TextView = itemView.findViewById(R.id.current_time)
        var imageView: ImageView = itemView.findViewById(R.id.weather_condition)

    }

    companion object {
        private var timeZone: Int? = null
        fun getLocalDate(unix: Int?) {
            timeZone = unix
        }
    }
}