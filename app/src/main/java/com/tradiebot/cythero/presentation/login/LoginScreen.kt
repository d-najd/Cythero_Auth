package com.tradiebot.cythero.presentation.login

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.tradiebot.cythero.app.ui.login.LoginPresenter
import com.tradiebot.cythero.domain.user.model.UserLoginUpdate
import com.tradiebot.cythero.presentation.login.components.LoginContent
import kotlin.reflect.KFunction1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    presenter: LoginPresenter,
    onLoginUser: (UserLoginUpdate) -> Unit,
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
            onLoginUser = onLoginUser,
        )
    }
}
