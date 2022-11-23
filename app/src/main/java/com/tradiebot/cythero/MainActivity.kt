package com.tradiebot.cythero

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.tradiebot.cythero.databinding.MainActivityBinding
import com.tradiebot.cythero.ui.base.Screens
import com.tradiebot.cythero.ui.theme.CytheroTheme
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

        // If there is no controller set one
        if(router.backstack.firstOrNull() == null) {
            //router.setRoot()
        }

        val container: ViewGroup = binding.controllerContainer
        router = Conductor.attachRouter(this, container, savedInstanceState)
            .setPopRootControllerMode(Router.PopRootControllerMode.NEVER)


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