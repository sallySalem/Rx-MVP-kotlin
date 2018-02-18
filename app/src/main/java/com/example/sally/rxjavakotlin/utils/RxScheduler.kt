package com.example.sally.rxjavakotlin.utils

import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by Sally Salem on 2/4/18.
 */
//Maybe
fun <T> Maybe<T>.applyIoScheduler() = applyScheduler(Schedulers.io())
fun <T> Maybe<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun <T> Maybe<T>.applyScheduler(scheduler: Scheduler) =
        subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

//Single
fun <T> Single<T>.applyIoScheduler() = applyScheduler(Schedulers.io())
fun <T> Single<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun <T> Single<T>.applyScheduler(scheduler: Scheduler) =
        subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

//Flowable
fun <T> Flowable<T>.applyIoScheduler() = applyScheduler(Schedulers.io())
fun <T> Flowable<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun <T> Flowable<T>.applyScheduler(scheduler: Scheduler) =
        subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())


//Observable
fun <T> Observable<T>.applyIoScheduler() = applyScheduler(Schedulers.io())
fun <T> Observable<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun <T> Observable<T>.applyScheduler(scheduler: Scheduler) =
        subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

