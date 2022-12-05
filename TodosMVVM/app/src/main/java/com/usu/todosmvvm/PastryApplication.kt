package com.usu.todosmvvm

import android.app.Application

class PastryApplication : Application() {
    init{
        instance = this
    }

    companion object{
        private var instance: Application? = null

        fun getInstance() : Application {
            return instance!!
        }
    }
}