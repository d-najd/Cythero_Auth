package com.tradiebot.cythero.presentation.register

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.tradiebot.cythero.app.ui.register.RegisterScreenState
import com.tradiebot.cythero.domain.user.model.UserRegister
import com.tradiebot.cythero.presentation.register.components.RegisterContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenContent(
    presenter: RegisterScreenState.Success,
    onClickUserRegister: (UserRegister) -> Unit,
    onClickLogin: () -> Unit,

    onMissingFields: () -> Unit,
    onNotMatchingPassword: () -> Unit,
) {
    Scaffold { contentPadding ->
        RegisterContent(
            state = presenter,
            contentPadding = contentPadding,
            onClickUserRegister = onClickUserRegister,
            onClickLogin = onClickLogin,
            onMissingFields = onMissingFields,
            onNotMatchingPassword = onNotMatchingPassword,
        )
    }
}
