package com.tradiebot.cythero.app

import androidx.appcompat.app.AppCompatActivity
import com.tradiebot.cythero.app.util.view.FragmentManagerHolder
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addSingleton
import uy.kohesive.injekt.api.addSingletonFactory

class MainActivityModule(val app: AppCompatActivity) : InjektModule {
    override fun InjektRegistrar.registerInjectables() {
        addSingleton(app)

        addSingletonFactory { FragmentManagerHolder(app) }
    }
}

/*
class PreferenceModule(val application: Application) : InjektModule {
    override fun InjektRegistrar.registerInjectables() {
    }
}
 */
