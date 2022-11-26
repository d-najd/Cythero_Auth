package com.tradiebot.cythero.presentation.user_info

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.tradiebot.cythero.app.ui.login.LoginScreenState
import com.tradiebot.cythero.app.ui.user_info.UserInfoScreenState
import com.tradiebot.cythero.presentation.user_info.components.UserInfoContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoScreen(
    presenter: UserInfoScreenState, //.Success
) {
    Scaffold(
    ) { contentPadding ->
        /**
         * attempt to see if the user has internet connection and if the server is connected before
         * continuing, if not return
         */
        UserInfoContent(
            state = presenter,
            contentPadding = contentPadding,
        )
    }
}
