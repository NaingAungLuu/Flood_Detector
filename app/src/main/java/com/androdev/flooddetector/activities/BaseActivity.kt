package com.androdev.flooddetector.activities

import androidx.appcompat.app.AppCompatActivity
import com.androdev.flooddetector.data.model.NetworkDataModelImpl
import com.androdev.flooddetector.mvp.presenters.DataPresenter

abstract class BaseActivity : AppCompatActivity() {

    protected val model = NetworkDataModelImpl

    protected lateinit var mPresenter : DataPresenter


}