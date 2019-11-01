package com.androdev.flooddetector.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.androdev.flooddetector.activities.BaseActivity
import com.androdev.flooddetector.activities.MainActivity
import com.androdev.flooddetector.mvp.presenters.DataPresenter
import com.androdev.flooddetector.network.dataAgents.RetrofitDataAgent
import com.androdev.flooddetector.utils.API_KEY

class GetDataWorker(context : Context, workParams : WorkerParameters) : Worker(context , workParams) {

    val dataAgent = RetrofitDataAgent
    private val mPresenter = DataPresenter()

    override fun doWork(): Result {

        var result : Result = Result.failure()



        dataAgent.getData(API_KEY , {



        }, {

        })

        return result

    }

}