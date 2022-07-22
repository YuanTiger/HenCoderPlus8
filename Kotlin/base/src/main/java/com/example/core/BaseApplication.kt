package com.example.core

import android.app.Application
import android.content.Context
import com.example.core.BaseApplication

class BaseApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        currentApplication = this
    }

    companion object {
        private lateinit var currentApplication: Context
        fun currentApplication(): Context {
            return currentApplication
        }
    }
}