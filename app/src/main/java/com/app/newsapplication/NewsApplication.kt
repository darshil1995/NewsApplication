package com.app.newsapplication

import android.app.Application
import android.content.Context

class NewsApplication : Application() {
    companion object {
        lateinit var context: Context
    }
}