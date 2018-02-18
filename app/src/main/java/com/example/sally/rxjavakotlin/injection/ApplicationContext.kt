package com.example.sally.rxjavakotlin.injection

import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier
import java.lang.annotation.Retention;


/**
 * Created by Sally Salem on 2/3/18.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class ApplicationContext