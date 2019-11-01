package com.androdev.flooddetector.data.model

import com.androdev.flooddetector.data.vos.FeedVO
import com.androdev.flooddetector.utils.API_KEY

object NetworkDataModelImpl : NetworkDataModel , BaseModel() {

    override fun getFeedData(
        apiKey: String,
        onSuccess: (List<FeedVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        dataAgent.getData(apiKey , {

            onSuccess(it)

        },{

            onFailure(it)

        })


    }



}