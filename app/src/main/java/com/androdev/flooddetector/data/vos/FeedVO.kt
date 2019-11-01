package com.androdev.flooddetector.data.vos

import com.google.gson.annotations.SerializedName
import java.util.*

data class FeedVO(

    @SerializedName("created_at")
    val entryTime: Date,

    @SerializedName("entry_id")
    val entryID : Int,

    @SerializedName("field1")
    val entryValue: Float

) {
}