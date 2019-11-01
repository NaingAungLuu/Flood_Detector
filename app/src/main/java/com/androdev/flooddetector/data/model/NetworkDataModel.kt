package com.androdev.flooddetector.data.model

import com.androdev.flooddetector.data.vos.FeedVO

interface NetworkDataModel {

    fun getFeedData(apiKey : String , onSuccess: (List<FeedVO>) -> Unit , onFailure : (String) -> Unit)

}