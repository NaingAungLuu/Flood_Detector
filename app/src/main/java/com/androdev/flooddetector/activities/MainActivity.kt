package com.androdev.flooddetector.activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import me.itangqi.waveloadingview.WaveLoadingView
import com.androdev.flooddetector.R
import com.androdev.flooddetector.data.vos.FeedVO
import com.androdev.flooddetector.mvp.DataView
import com.androdev.flooddetector.mvp.presenters.DataPresenter
import com.androdev.flooddetector.utils.API_KEY
import kotlinx.android.synthetic.main.activity_main.*
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Handler
import com.github.mikephil.charting.components.YAxis


class MainActivity : BaseActivity() , DataView{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var progress = 0


        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.RECTANGLE)
        mWaveLoadingView.progressValue = progress
        mWaveLoadingView.setAnimDuration(2000)

        mPresenter = DataPresenter()
        mPresenter.initPresenter(this)
        mPresenter.onUiready()

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                mPresenter.onUiready()
                    println("Worked")
                handler.postDelayed(this, 30000)
            }
        }, 5000)


    }

    private fun setWaterLevel(level : Float)
    {
        val waterLevel = level.toInt()
        if(waterLevel in 0..100)
        {
           mWaveLoadingView.progressValue = waterLevel
            tvWaterLevel.text = waterLevel.toString()
            if(waterLevel >80)
            {
                tvWaterLevel.setTextColor(Color.WHITE)
                mWaveLoadingView.waveColor = Color.RED
            }
            else if(waterLevel > 60)
            {
                tvWaterLevel.setTextColor(Color.WHITE)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mWaveLoadingView.waveColor = resources.getColor(R.color.colorAccent, null)
                }
            }
            else
            {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvWaterLevel.setTextColor(resources.getColor(R.color.colorWater , null))
                    mWaveLoadingView.waveColor = resources.getColor(R.color.colorWater , null)
                }
            }

        }
        else if(waterLevel > 100)
        {
            mWaveLoadingView.progressValue = 100

        }
    }

    override fun showData(feedData : List<FeedVO>)
    {
        setWaterLevel(100-(feedData.last().entryValue))
        setChartData(feedData)
        if(chart.data == null)
        {

            chart.invalidate()
        }
        else{

            chart.notifyDataSetChanged()

        }
    }


    fun setChartData(feedData : List<FeedVO>)
    {
        val entries = ArrayList<Entry>()
        val labels = ArrayList<String>()
        var dateData = 0f
        val ref_timeStamp = feedData.get(0).entryTime.time.toFloat()
        val xAxisFormatter = HourFormatter(ref_timeStamp.toLong())
        chart.xAxis.valueFormatter = xAxisFormatter
        chart.setScaleEnabled(true)
        chart.setTouchEnabled(true)
        chart.setPadding(16 , 16 , 16 , 16)

        chart.setBorderWidth(2f)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            chart.xAxis.axisLineColor = resources.getColor(R.color.colorPrimary , null)
            chart.axisLeft.axisLineColor = resources.getColor(R.color.colorPrimary , null)
        }



        for (data : FeedVO in feedData)
        {
            dateData = data.entryTime.time.toFloat()
            entries.add(Entry(dateData - ref_timeStamp , 100f - data.entryValue))
            val mDataFormat =  SimpleDateFormat("HH:mm", Locale.ENGLISH)
            val time = mDataFormat.format(data.entryTime)
            labels.add(time)

        }

        val dataSet = LineDataSet(entries , "Flood Level")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dataSet.color = resources.getColor(R.color.colorAccent , null)
        }
        dataSet.setCircleColor(R.color.colorPrimary)
        dataSet.lineWidth = 2f


        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.setNoDataTextColor(resources.getColor(R.color.colorAccent))
        chart.description.text = "Water Level Indication Chart"
        chart.setVisibleYRangeMaximum(1000f , YAxis.AxisDependency.RIGHT)

    }


}
