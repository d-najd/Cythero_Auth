package com.tradiebot.cythero.app

import android.app.Application
import androidx.lifecycle.DefaultLifecycleObserver

class App : Application(), DefaultLifecycleObserver
{
    override fun onCreate() {
        super<Application>.onCreate()

        // ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }
}