package com.example.android.weatherApp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherApp.retrofit.MainData

class TodayFragment : Fragment() {
    private var todayRecyclerViewAdapter: TodayRecyclerViewAdapter? = null
    private var mTemperature: TextView? = null
    private var mWeatherDescription: TextView? = null
    private var mMainText: TextView? = null
    private var mBackGround: ImageView? = null
    private var mWeatherCondition: ImageView? = null
    var todayViewFlipper: ViewFlipper? = null
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todayRecyclerViewAdapter = TodayRecyclerViewAdapter(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_today, container, false)

        val mRecyclerView: RecyclerView = root.findViewById(R.id.today_recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mRecyclerView.adapter = todayRecyclerViewAdapter
        mRecyclerView.itemAnimator = DefaultItemAnimator()

        mTemperature = root.findViewById(R.id.temperature)
        mTemperature?.text = MainActivity.Companion.weatherData.get("temperature")

        mMainText = root.findViewById(R.id.text_main)

        mWeatherDescription = root.findViewById(R.id.weather_description_todayFragment)

        mBackGround = root.findViewById(R.id.today_background)

        mWeatherCondition = root.findViewById(R.id.weather_condition)
        mWeatherCondition?.setImageDrawable(MainActivity.Companion.weatherIcons.get(MainActivity.Companion.weatherData["icon"]))

        todayViewFlipper = root.findViewById(R.id.viewFlipperToday)
        return root
    }

    fun setMainText(text: String?) {
        mMainText!!.text = text
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setDataTodayFragment(values: Array<MainData>?) {
        todayRecyclerViewAdapter!!.setDataTodayFragment(values)
        setMainText(values!![0].weathers?.get(0)!!.weatherCondition)
        mTemperature?.text = (values[0].mainDataValues.getTemp())
        mWeatherCondition?.setImageDrawable(MainActivity.Companion.weatherIcons[values[0]!!.weathers?.get(0)?.weatherIcon])
        mWeatherDescription?.text = (values[0].weathers?.get(0)?.weatherCondition)
        mBackGround!!.visibility = View.VISIBLE
    }
}