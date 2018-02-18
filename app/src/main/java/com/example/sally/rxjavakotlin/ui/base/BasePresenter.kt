package com.example.sally.rxjavakotlin.ui.base

import io.reactivex.disposables.CompositeDisposable


/**
 * Created by Sally Salem on 2/3/18.
 */
abstract class BasePresenter<T : BaseView> constructor(val compositeDisposable: CompositeDisposable) {
    var view: T? = null
    fun onDestroy() {
        compositeDisposable.clear()
    }
}