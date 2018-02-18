package com.example.sally.rxjavakotlin.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


/**
 * Created by Sally Salem on 2/3/18.
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {
    var presenter: BasePresenter<*>? = null

    abstract fun initializeDagger()
    abstract fun initializePresenter()

    abstract var layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initializeDagger()
        initializePresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }
}