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

class ThreeDaysFragment : Fragment() {
    var threeDaysViewFlipper: ViewFlipper? = null
    private var threeDaysRecyclerViewAdapter: ThreeDaysRecyclerViewAdapter? = null
    private var mBackGround: ImageView? = null
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(SavedInstanceState: Bundle?) {
        super.onCreate(SavedInstanceState)
        threeDaysRecyclerViewAdapter = ThreeDaysRecyclerViewAdapter(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_three_days, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.three_days_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = threeDaysRecyclerViewAdapter
        recyclerView.itemAnimator = DefaultItemAnimator()
        mBackGround = root.findViewById(R.id.three_days_background)
        threeDaysViewFlipper = root.findViewById(R.id.viewFlipperThreeDays)
        return root
    }

    fun setDataThreeDaysFragment(values: Array<MainData>?) {
        threeDaysRecyclerViewAdapter!!.setDataThreeDaysFragment(values)
        mBackGround!!.visibility = View.VISIBLE
    }
}