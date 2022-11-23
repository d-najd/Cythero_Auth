package com.tradiebot.cythero.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.app.ui.register.LoginPresenter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    presenter: LoginPresenter,
    //onTestClicked: (Long) -> Unit,
) {
    Scaffold(

    ) { contentPadding ->
        Text(text = "re", Modifier.padding(contentPadding))

    }

    /*
    Scaffold { _ ->
        /**
         * attempt to see if the user has internet connection and if the server is connected before
         * continuing, if not return
         */

        LibraryContent(
            state = presenter,
        )
    }

     */
}
