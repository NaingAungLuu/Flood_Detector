package com.androdev.flooddetector.mvp

import com.androdev.flooddetector.data.vos.FeedVO

interface DataView {

    fun showData(feedData : List<FeedVO>)

}