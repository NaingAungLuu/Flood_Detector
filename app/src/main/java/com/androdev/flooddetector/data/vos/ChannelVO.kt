package com.androdev.flooddetector.data.vos

import com.google.gson.annotations.SerializedName

data class ChannelVO(

    @SerializedName("id")
    val channelID: Int,

    @SerializedName("name")
    val channelName : String,

    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("longitude")
    val Longitude : Double,

    @SerializedName("field1")
    val fieldName : String,

    @SerializedName("created_at")
    val createdDate : String,

    @SerializedName("updated_at")
    val updatedDate : String,

    @SerializedName("last_entry_id")
    val lastEntryID : String

) {
}