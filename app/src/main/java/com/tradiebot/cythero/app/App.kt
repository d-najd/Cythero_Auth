package com.tradiebot.cythero.app

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner

class App : Application(), DefaultLifecycleObserver
{
    @SuppressLint("LaunchActivityFromNotification")
    override fun onCreate() {
        super<Application>.onCreate()


        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }
}