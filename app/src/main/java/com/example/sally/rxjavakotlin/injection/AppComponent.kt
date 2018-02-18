package com.example.sally.rxjavakotlin.injection

import com.example.sally.rxjavakotlin.ui.MainAppApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by Sally Salem on 2/3/18.
 */
@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        MainBuilder::class,
        AppModule::class))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applications(app: MainAppApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: MainAppApplication)
}