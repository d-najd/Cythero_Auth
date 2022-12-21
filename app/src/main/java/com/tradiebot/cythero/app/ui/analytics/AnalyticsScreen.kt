package com.tradiebot.cythero.app.ui.analytics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.presentation.analytics.AnalyticsScreenContent
import com.tradiebot.cythero.presentation.analytics.components.reports.usage.components.AnalyticsUsageItemInfoDialog
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
    
        AnalyticsScreenContent(
            presenter = state,
            onBackClicked = router::popCurrentController,
            onGenerateUserReportClicked = { screenModel.requestUserAnalytics(auth, userID = 4L, it) },
            onGeneratePartReportClicked = { screenModel.requestPartAnalytics(auth, userID = 4L, it) },
            onGenerateUsageReportClicked = { screenModel.requestUsageAnalytics(auth, userID = 4L, it) },
            onSortUsageReport = screenModel::sortUsageAnalytics,
            onShowUsageItemInfo = { screenModel.showUsageDialog(AnalyticsUsageDialog.ItemInfo(it, it.sessionID)) }
        )
    
        if(state is AnalyticsScreenState.UsageSuccess){
            val usageState = (state as AnalyticsScreenState.UsageSuccess)
            when(val dialog = usageState.dialog){
                null -> {}
                is AnalyticsUsageDialog.ItemInfo -> {
                    AnalyticsUsageItemInfoDialog(
                        onDismissRequest = screenModel::dismissUsageDialog,
                        analyticSessionInfo = usageState.analyticsSessionInfo!!,
                        analyticsLabels = usageState.analyticsLabels,
                        analyticUsage = dialog.analytic,
                    )
                }
            }
        }
    }
}


