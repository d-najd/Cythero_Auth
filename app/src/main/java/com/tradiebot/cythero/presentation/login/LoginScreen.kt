package com.tradiebot.cythero.presentation.login

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.tradiebot.cythero.app.ui.login.LoginScreenState
import com.tradiebot.cythero.domain.user.model.UserSign
import com.tradiebot.cythero.presentation.login.components.LoginContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    presenter: LoginScreenState.Success,
    onClickUserLogin: (UserSign) -> Unit,
    onClickRegister: (UserSign) -> Unit,
) {
    Scaffold(
    ) { contentPadding ->
        /**
         * attempt to see if the user has internet connection and if the server is connected before
         * continuing, if not return
         */
        LoginContent(
            state = presenter,
            contentPadding = contentPadding,
            onClickUserLogin = onClickUserLogin,
            onClickRegister = onClickRegister,
        )
    }
}
