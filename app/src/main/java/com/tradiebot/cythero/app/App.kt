package com.tradiebot.cythero.app

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.tradiebot.cythero.domain.DomainModule
import uy.kohesive.injekt.Injekt

class App : Application(), DefaultLifecycleObserver{
    @SuppressLint("LaunchActivityFromNotification")
    override fun onCreate() {
        super<Application>.onCreate()

        Injekt.importModule(AppModule(this))
        Injekt.importModule(DomainModule())

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }
}