package com.example.optusdemo.utils

import android.app.Application
import android.content.Context

/*
* Singleton class which gives the context of Application
 */

class OptusDemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        lateinit var context: Context
    }
}