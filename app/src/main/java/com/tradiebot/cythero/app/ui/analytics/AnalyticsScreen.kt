package com.tradiebot.cythero.app.ui.analytics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsReportTypeScreenModel
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsReportTypeScreenState
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsUserTypeScreenModel
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.presentation.components.LoadingScreen
import com.tradiebot.cythero.presentation.analytics.AnalyticsScreen
import com.tradiebot.cythero.presentation.util.LocalRouter

class AnalyticsScreen(
    private val auth: Auth,
) : Screen {
    @Composable
    override fun Content() {
        // val navigator = LocalNavigator.currentOrThrow
        val router = LocalRouter.currentOrThrow
        val context = LocalContext.current
        val reportTypeScreenModel = rememberScreenModel { AnalyticsReportTypeScreenModel(context, auth) }
        val userReportScreenModel = rememberScreenModel { AnalyticsUserTypeScreenModel(context, auth) }

        val reportTypeState by reportTypeScreenModel.state.collectAsState()
        val userReportState by userReportScreenModel.state.collectAsState()

        if (reportTypeState is AnalyticsReportTypeScreenState.Loading) {
            LoadingScreen()
            return
        }

        fun changeReportScreenModel(reportType: SelectedReportType){
            when(reportType) {
                SelectedReportType.USER -> {
                    userReportScreenModel.requestAnalytics(
                        auth = auth,
                        userID = 4L,
                    )
                }
                SelectedReportType.PART -> {
                    userReportScreenModel.requestedDifferentAnalytics()
                }
                SelectedReportType.USAGE -> {
                    userReportScreenModel.requestedDifferentAnalytics()
                }
            }
        }

        val reportTypeSuccessState = reportTypeState as AnalyticsReportTypeScreenState.Success

        AnalyticsScreen(
            reportTypeState = reportTypeSuccessState,
            userReportState = userReportState,
            onBackClicked = router::popCurrentController,
            onGenerateReportClicked = { changeReportScreenModel(it) }
        )
    }

    enum class SelectedReportType(val reportTypeId: Int) {
        USER(R.string.action_select_user_report),
        PART(R.string.action_select_part),
        USAGE(R.string.action_select_usage),
    }

}


