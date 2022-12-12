package com.tradiebot.cythero.app.ui.analytics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.presentation.analytics.AnalyticsScreen
import com.tradiebot.cythero.presentation.components.LoadingScreen
import com.tradiebot.cythero.presentation.util.LocalRouter

class AnalyticsScreen(
    private val auth: Auth,
) : Screen {
    @Composable
    override fun Content() {
        // val navigator = LocalNavigator.currentOrThrow
        val router = LocalRouter.currentOrThrow
        val context = LocalContext.current
        val screenModel = rememberScreenModel { AnalyticsScreenModel(context, auth) }

        val state by screenModel.state.collectAsState()

        if (state is AnalyticsScreenState.Loading) {
            LoadingScreen()
            return
        }

        AnalyticsScreen(
            presenter = state,
            onBackClicked = router::popCurrentController,
            onGenerateUserReportClicked = {
                screenModel.requestUserAnalytics(auth, userID = 4L, it)
            },
            onGeneratePartReportClicked = {
                screenModel.requestPartAnalytics(auth, userID = 4L, it)
            }
        )
    }


}


