package com.tradiebot.cythero.presentation.analytics

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(
    presenter: AnalyticsScreenState.Success,
    onBackClicked: () -> Unit
) {
    Scaffold { contentPadding ->
        BackHandler{ onBackClicked() }

        AnalyticsContent(
            state = presenter,
            contentPadding = contentPadding,
        )
    }
}
