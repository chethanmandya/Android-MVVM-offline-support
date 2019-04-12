package com.chethan.sport

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this,
            listOf(mainModule))

        Stetho.initializeWithDefaults(this);
    }
}