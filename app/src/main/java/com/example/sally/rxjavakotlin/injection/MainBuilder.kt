package com.example.sally.rxjavakotlin.injection

import com.example.sally.rxjavakotlin.ui.details.DetailsActivity
import com.example.sally.rxjavakotlin.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Sally Salem on 2/3/18.
 */
@Module
abstract class MainBuilder {

    @ContributesAndroidInjector
    abstract fun homeActivityBuilder(): HomeActivity

    @ContributesAndroidInjector
    abstract fun detailsActivityBuilder(): DetailsActivity
}