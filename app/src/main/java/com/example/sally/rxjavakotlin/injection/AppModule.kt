package com.example.sally.rxjavakotlin.injection

import android.app.Application
import android.content.Context
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import com.example.sally.rxjavakotlin.ui.base.BaseActivity
import android.app.Activity
import android.os.Handler
import com.example.sally.rxjavakotlin.utils.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Sally Salem on 2/3/18.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @ActivityContext
    fun provideActivity(baseActivity: BaseActivity): Activity {
        return baseActivity
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideHandler(): Handler {
        return Handler()
    }

    @Provides
    fun provideRetrofitService(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}