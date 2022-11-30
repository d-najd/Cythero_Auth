package com.tradiebot.cythero.presentation.user_info

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(175.dp)
                .background(MaterialTheme.colorScheme.primary)
        )

        UserInfoContent(
            state = presenter,
            contentPadding = contentPadding,
        )
    }
}
