package com.tradiebot.cythero.app

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.tradiebot.cythero.app.ui.base.Screens
import com.tradiebot.cythero.app.ui.base.controller.setRoot
import com.tradiebot.cythero.app.ui.register.LoginController
import com.tradiebot.cythero.app.ui.theme.CytheroTheme
import com.tradiebot.cythero.databinding.MainActivityBinding
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()

    lateinit var binding: MainActivityBinding
    private lateinit var router: Router

    private val startScreenId = Screens.LOGIN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TestAuth().run()

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val container: ViewGroup = binding.controllerContainer
        router = Conductor.attachRouter(this, container, savedInstanceState)
            .setPopRootControllerMode(Router.PopRootControllerMode.NEVER)

        if(router.backstack.firstOrNull() == null) {
            router.setRoot(LoginController(), startScreenId)
        }

        /*
        setContent {
            CytheroTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
         */

    }
}



@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CytheroTheme {
        Greeting("Android")
    }
}