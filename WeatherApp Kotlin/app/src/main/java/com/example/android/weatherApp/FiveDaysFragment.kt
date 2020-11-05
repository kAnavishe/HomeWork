package com.example.android.weatherApp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherApp.retrofit.MainData

class FiveDaysFragment : Fragment() {
    private var fiveDaysRecyclerViewAdapter: FiveDaysRecyclerViewAdapter? = null
    private var mBackGround: ImageView? = null
    var fiveDaysViewFlipper: ViewFlipper? = null
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(SavedInstanceState: Bundle?) {
        super.onCreate(SavedInstanceState)
        fiveDaysRecyclerViewAdapter = FiveDaysRecyclerViewAdapter(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_seven_days, container, false)
        val mRecyclerView: RecyclerView = root.findViewById(R.id.five_days_recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = fiveDaysRecyclerViewAdapter
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        mBackGround = root.findViewById(R.id.five_days_background)
        fiveDaysViewFlipper = root.findViewById(R.id.viewFlipperSevenDays)
        return root
    }

    fun setDataFiveDaysFragment(values: Array<MainData>?) {
        fiveDaysRecyclerViewAdapter!!.setDataFiveDayFragment(values)
        mBackGround!!.visibility = View.VISIBLE
    }
}