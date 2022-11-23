package com.tradiebot.cythero

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tradiebot.cythero.ui.theme.CytheroTheme
import logcat.logcat
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val test: Activity = this

        Thread(Runnable {
            this@MainActivity.runOnUiThread(java.lang.Runnable {
                val client = OkHttpClient().newBuilder()
                    .build()
                // val mediaType = "text/plain".toMediaTypeOrNull()
                val body: RequestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
                    // .setType(mediaType!!)
                    .addFormDataPart("email", "dimitar.najdovski.example@gmail.com")
                    .addFormDataPart("password", "Dimitar123")
                    .addFormDataPart("from_web", "true")
                    .build()
                val request: Request = Request.Builder()
                    .url("https://api.cythero.com/api/user/auth/login")
                    .method("POST", body)
                    .build()
                val response = client.newCall(request).execute()
                logcat { "$response test $response" }
            })
        }).start()

        TestAuth().test(test)


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