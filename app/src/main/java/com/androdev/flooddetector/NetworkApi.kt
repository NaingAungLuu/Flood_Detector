package com.androdev.flooddetector

import com.androdev.flooddetector.network.responses.DataResponse
import com.androdev.flooddetector.utils.PARAM_API_KEY
import com.androdev.flooddetector.utils.READ_FEED
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {

    @GET(READ_FEED)
    fun getFeedData(@Query(PARAM_API_KEY) apiKey : String):Call<DataResponse>

}