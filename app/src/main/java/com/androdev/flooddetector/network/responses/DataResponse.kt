package com.androdev.flooddetector.network.responses

import com.androdev.flooddetector.data.vos.ChannelVO
import com.androdev.flooddetector.data.vos.FeedVO
import com.google.gson.annotations.SerializedName

data class DataResponse(

    @SerializedName("channel")
    val channel : ChannelVO,

    @SerializedName("feeds")
    val feed : List<FeedVO>

) {
}