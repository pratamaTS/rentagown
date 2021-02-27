package com.example.rentagown.v2

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class RAGApp : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}