package com.androdev.flooddetector.mvp.presenters

import android.view.View
import com.androdev.flooddetector.data.model.NetworkDataModelImpl
import com.androdev.flooddetector.data.vos.FeedVO
import com.androdev.flooddetector.mvp.DataView
import com.androdev.flooddetector.utils.API_KEY

class DataPresenter {

    private val model = NetworkDataModelImpl
    private lateinit var mView : DataView

    fun initPresenter(dataView: DataView)
    {
        mView = dataView
    }

    fun showData(feedData : List<FeedVO>)
    {
        mView.showData(feedData)
    }

    fun onUiready()
    {
        model.getFeedData(API_KEY ,{ feedData->

            showData(feedData)
            println("network Called : first data = ${feedData.get(0).entryValue}")

        } ,{

        })
    }


}