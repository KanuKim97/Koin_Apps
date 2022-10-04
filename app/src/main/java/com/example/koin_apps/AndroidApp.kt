package com.example.koin_apps

import android.app.Application
import android.content.Context

class AndroidApp: Application() {
    init{
        instance = this
    }

    companion object {
        lateinit var instance: AndroidApp

        fun getApplicationContext(): Context {
            return instance.applicationContext
        }
    }
}