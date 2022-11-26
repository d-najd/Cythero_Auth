package com.tradiebot.cythero.presentation.register

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.tradiebot.cythero.app.ui.register.RegisterScreenState
import com.tradiebot.cythero.presentation.register.components.RegisterContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    presenter: RegisterScreenState.Success,
) {
    Scaffold { contentPadding ->
        /**
         * attempt to see if the user has internet connection and if the server is connected before
         * continuing, if not return
         */


        RegisterContent(
            state = presenter,
            contentPadding = contentPadding,
        )
        /*
        LoginContent(
            state = presenter,
            contentPadding = contentPadding,
        )

         */
    }
}
