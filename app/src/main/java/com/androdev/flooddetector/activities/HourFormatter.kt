package com.androdev.flooddetector.activities

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ViewPortHandler
import java.text.SimpleDateFormat
import java.util.*

class HourFormatter(refTimeStamp : Long) : ValueFormatter(){

    private val referenceTimestamp: Long = refTimeStamp
    private var mDate: Date = Date()


    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val convertedTimestamp = value.toLong()

        // Retrieve original timestamp
        val originalTimestamp = referenceTimestamp + convertedTimestamp
        return getHour(originalTimestamp)
    }



    private fun getHour(time : Long):String
    {
        mDate.time = time*1000
        val mDataFormat =  SimpleDateFormat("HH:mm", Locale.ENGLISH)
        return mDataFormat.format(mDate).toString()
    }
}