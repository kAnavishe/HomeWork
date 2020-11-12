package com.example.android.weatherApp

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.android.weatherApp.retrofit.ApiClient
import com.example.android.weatherApp.retrofit.ApiInterface
import com.example.android.weatherApp.retrofit.ListData
import com.example.android.weatherApp.retrofit.MainData
import com.example.android.weatherApp.retrofit.NetworkConnectionInterceptor.NoConnectivityException
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mViewPager: ViewPager? = null
    var mViewPagerAdapter: PagerAdapter? = null
    var mToolBar: Toolbar? = null
    private var mTabLayout: TabLayout? = null
    private var mSearchButton: Button? = null
    private var mEditText: EditText? = null
    var mPicChangeFlag: Boolean? = null
    var error: Boolean? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var searchText: String? = null
    var count = 0
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todayFragment = TodayFragment()
        threeDaysFragment = ThreeDaysFragment()
        fiveDaysFragment = FiveDaysFragment()

        swipeRefreshLayout = findViewById(R.id.mainSwipeRefresh)
        mSearchButton = findViewById(R.id.location_search_button)
        mToolBar = findViewById(R.id.toolBar)
        mTabLayout = findViewById(R.id.tabLayout)
        mViewPager = findViewById(R.id.ViewPager)
        mViewPagerAdapter = mTabLayout?.tabCount?.let { MyPagerAdapter(supportFragmentManager, it) }
        mViewPager?.adapter = mViewPagerAdapter
        mEditText = findViewById(R.id.location_search_edit_text)
        mPicChangeFlag = false
        error = false
        searchText = ""

        weatherIcons["01d"] = resources.getDrawable(R.drawable.i01d)
        weatherIcons["01n"] = resources.getDrawable(R.drawable.i01n)
        weatherIcons["02d"] = resources.getDrawable(R.drawable.i02d)
        weatherIcons["02n"] = resources.getDrawable(R.drawable.i02n)
        weatherIcons["03d"] = resources.getDrawable(R.drawable.i03d)
        weatherIcons["03n"] = resources.getDrawable(R.drawable.i03n)
        weatherIcons["04d"] = resources.getDrawable(R.drawable.i04d)
        weatherIcons["04n"] = resources.getDrawable(R.drawable.i04n)
        weatherIcons["09d"] = resources.getDrawable(R.drawable.i09d)
        weatherIcons["09n"] = resources.getDrawable(R.drawable.i09n)
        weatherIcons["10d"] = resources.getDrawable(R.drawable.i10d)
        weatherIcons["10n"] = resources.getDrawable(R.drawable.i10n)
        weatherIcons["11d"] = resources.getDrawable(R.drawable.i11d)
        weatherIcons["11n"] = resources.getDrawable(R.drawable.i11n)
        weatherIcons["13d"] = resources.getDrawable(R.drawable.i13d)
        weatherIcons["13n"] = resources.getDrawable(R.drawable.i13n)
        weatherIcons["50d"] = resources.getDrawable(R.drawable.i50d)
        weatherIcons["50n"] = resources.getDrawable(R.drawable.i50n)

        mSearchButton?.setOnClickListener(View.OnClickListener {
            swipeRefreshLayout?.isRefreshing = true
            count = 0
            searchText = mEditText?.text.toString().trim { it <= ' ' }
            getWeatherData(searchText)
            closeKeyBoard()
        })

        swipeRefreshLayout?.setOnRefreshListener(OnRefreshListener {
            getWeatherData(mEditText?.text.toString().trim { it <= ' ' })
            closeKeyBoard()
        })

        mTabLayout?.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mViewPager?.currentItem = tab.position
                closeKeyBoard()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        mViewPager?.addOnPageChangeListener(TabLayoutOnPageChangeListener(mTabLayout))
        mViewPager?.offscreenPageLimit = 3
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private class MyPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {
        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return todayFragment!!
                1 -> return threeDaysFragment!!
                2 -> return fiveDaysFragment!!
            }
            return todayFragment!!
        }

        override fun getCount(): Int {
            return 3
        }
    }

    fun getWeatherData(name: String?) {
        Log.d("MAX", "getWeatherData")
        count += 1
        val apiInterface = ApiClient.getClient(this)!!.create(ApiInterface::class.java)
        val call = apiInterface.getWeatherData(name)
        call!!.enqueue(object : Callback<ListData?> {

            @SuppressLint("CheckResult")
            override fun onResponse(call: Call<ListData?>?, response: Response<ListData?>?) {
                val observableCityData = Observable.fromCallable { response?.body()?.cityData?.timezone }
                observableCityData.subscribe({ unix: Int? -> TodayRecyclerViewAdapter.getLocalDate(unix) }) {
                    error = true
                    closeKeyBoard()
                }

                val observableMainData = Observable.fromCallable { response?.body()?.mainData }

                observableMainData.subscribe({ values: Array<MainData>? -> todayFragment!!.setDataTodayFragment(values) }) { closeKeyBoard() }
                observableMainData.subscribe({ values: Array<MainData>? -> threeDaysFragment!!.setDataThreeDaysFragment(values) }) { closeKeyBoard() }
                observableMainData.subscribe({ values: Array<MainData>? -> fiveDaysFragment!!.setDataFiveDaysFragment(values) }) { closeKeyBoard() }

                if (error == true) {
                    todayFragment!!.todayViewFlipper!!.displayedChild = 1
                    threeDaysFragment!!.threeDaysViewFlipper!!.displayedChild = 1
                    fiveDaysFragment!!.fiveDaysViewFlipper!!.displayedChild = 1
                    swipeRefreshLayout!!.isRefreshing = false
                    error = false
                } else {
                    count = 0
                    todayFragment!!.todayViewFlipper!!.displayedChild = 0
                    threeDaysFragment!!.threeDaysViewFlipper!!.displayedChild = 0
                    fiveDaysFragment!!.fiveDaysViewFlipper!!.displayedChild = 0
                    swipeRefreshLayout!!.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<ListData?>?, t: Throwable?) {
                if (t is NoConnectivityException) {
                    if (count <= 3) {
                        getWeatherData(searchText)
                    } else {
                        todayFragment!!.todayViewFlipper!!.displayedChild = 3
                        threeDaysFragment!!.threeDaysViewFlipper!!.displayedChild = 3
                        fiveDaysFragment!!.fiveDaysViewFlipper!!.displayedChild = 3
                        swipeRefreshLayout!!.isRefreshing = false
                    }
                }
                if (count <= 3) {
                    getWeatherData(searchText)
                } else {
                    Log.d("MAX", t.toString())
                    todayFragment!!.todayViewFlipper!!.displayedChild = 2
                    threeDaysFragment!!.threeDaysViewFlipper!!.displayedChild = 2
                    fiveDaysFragment!!.fiveDaysViewFlipper!!.displayedChild = 2
                    swipeRefreshLayout!!.isRefreshing = false
                }
            }
        })
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var todayFragment: TodayFragment? = null

        @SuppressLint("StaticFieldLeak")
        var threeDaysFragment: ThreeDaysFragment? = null

        @SuppressLint("StaticFieldLeak")
        var fiveDaysFragment: FiveDaysFragment? = null
        var weatherData = HashMap<String, String>()
        var weatherIcons = HashMap<String?, Drawable>()
    }
}