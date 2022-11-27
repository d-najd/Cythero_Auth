package com.tradiebot.cythero.presentation.login

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.tradiebot.cythero.app.ui.login.LoginScreenState
import com.tradiebot.cythero.domain.user.model.UserLogin
import com.tradiebot.cythero.presentation.login.components.LoginContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    presenter: LoginScreenState.Success,
    onClickUserLogin: (UserLogin) -> Unit,
    onClickRegister: () -> Unit,

    onMissingFields: () -> Unit,
) {
    Scaffold { contentPadding ->
        LoginContent(
            state = presenter,
            contentPadding = contentPadding,
            onClickUserLogin = onClickUserLogin,
            onClickRegister = onClickRegister,
            onMissingFields = onMissingFields,
        )
    }
}
