package com.androdev.flooddetector.network.dataAgents

import com.androdev.flooddetector.data.vos.FeedVO

interface NetworkDataAgent {

    fun getData(apiKey : String,
                onSuccess : (List<FeedVO>) -> Unit,
                onFailure : (String) -> Unit)

}