package com.tradiebot.cythero.presentation.user_info

import androidx.activity.compose.BackHandler
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.tradiebot.cythero.app.ui.user_info.UserInfoScreenState
import com.tradiebot.cythero.presentation.user_info.components.UserInfoContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoScreen(
    presenter: UserInfoScreenState.Success,
    onBackClicked: () -> Unit
) {
    Scaffold { contentPadding ->
        BackHandler{ onBackClicked() }

        UserInfoContent(
            state = presenter,
            contentPadding = contentPadding,
        )
    }
}
