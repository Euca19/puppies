package com.app.puppies

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.app.puppies.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


/**
 * Created by Eucaris Guerrero on 24-04-20.
 */
class MyApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(AppModule)
        }
    }


}