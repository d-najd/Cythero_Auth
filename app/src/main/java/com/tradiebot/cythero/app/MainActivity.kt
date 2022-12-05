package com.tradiebot.cythero.app

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.asTransaction
import com.tradiebot.cythero.app.ui.analytics.AnalyticsController
import com.tradiebot.cythero.app.ui.login.LoginController
import com.tradiebot.cythero.databinding.MainActivityBinding
import com.tradiebot.cythero.domain.DomainModule
import com.tradiebot.cythero.domain.auth.model.Auth
import uy.kohesive.injekt.Injekt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO move this to the app module, doing so will crash the app because the presenter's will
        // get instantiated before the DomainModule gets called
        Injekt.importModule(DomainModule())

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val container: ViewGroup = binding.controllerContainer
        router = Conductor.attachRouter(this, container, savedInstanceState)
            .setPopRootControllerMode(Router.PopRootControllerMode.NEVER)

        // if there is no controller (in other words starting the app) set a root controller
        if(router.backstack.firstOrNull() == null) {
            // router.setRoot(AnalyticsController(auth = Auth.testingInstance()).asTransaction())
            router.setRoot(LoginController().asTransaction())
        }
    }
}
