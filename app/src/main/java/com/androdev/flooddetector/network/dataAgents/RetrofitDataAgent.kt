package com.androdev.flooddetector.network.dataAgents

import androidx.lifecycle.LiveData
import com.androdev.flooddetector.NetworkApi
import com.androdev.flooddetector.data.vos.FeedVO
import com.androdev.flooddetector.network.responses.DataResponse
import com.androdev.flooddetector.utils.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgent : NetworkDataAgent {

    private lateinit var networkApi : NetworkApi

    init {
        val okHttpClient = OkHttpClient.Builder().readTimeout(10 , TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
            .connectTimeout(10 , TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .create()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        networkApi = retrofit.create(NetworkApi::class.java)

    }

    override fun getData(
        apiKey: String,
        onSuccess: (List<FeedVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        val call = networkApi.getFeedData(apiKey)

        call.enqueue(object : Callback<DataResponse>{

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {

                onFailure(t.localizedMessage)

            }

            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {

                val dataResponse = response.body()
                val liveData : LiveData<List<FeedVO>>

                if(dataResponse != null)
                {
                    onSuccess(dataResponse.feed)

                }
                else
                {
                    onFailure("Something went wrong")
                }



            }

        })


    }

}