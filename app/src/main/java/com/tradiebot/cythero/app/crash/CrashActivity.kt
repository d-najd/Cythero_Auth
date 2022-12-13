package com.tradiebot.cythero.app.crash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.tradiebot.cythero.app.MainActivity
import com.tradiebot.cythero.app.util.view.setComposeContent
import com.tradiebot.cythero.presentation.crash.CrashScreen

class CrashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val exception = GlobalExceptionHandler.getThrowableFromIntent(intent)
        setComposeContent {
            CrashScreen(
                exception = exception,
                onRestartClick = {
                    finishAffinity()
                    startActivity(Intent(this@CrashActivity, MainActivity::class.java))
                },
            )
        }
    }
}
