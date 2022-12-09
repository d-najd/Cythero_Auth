package com.tradiebot.cythero.app.ui.analytics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tradiebot.cythero.app.ui.analytics.screen_models.*
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
        val userAnalyticsScreenModel = rememberScreenModel { AnalyticsUserScreenModelType(context, auth) }
        val partAnalyticsScreenModel = rememberScreenModel { AnalyticsPartScreenModelType(context, auth) }
        val screenModels = listOf<AnalyticsScreenModelType>(userAnalyticsScreenModel, partAnalyticsScreenModel)

        val reportTypeState by reportTypeScreenModel.state.collectAsState()
        val userReportState by userAnalyticsScreenModel.state.collectAsState()
        val partReportState by partAnalyticsScreenModel.state.collectAsState()

        if (reportTypeState is AnalyticsReportTypeScreenState.Loading) {
            LoadingScreen()
            return
        }

        /**
         * un-focuses the screen models which are not the given screen
         */
        fun unFocusOtherScreens(
            reportType: AnalyticsType,
        ){
            for(screenModel in screenModels){
                if(screenModel.getReportType() != reportType){
                    screenModel.requestedDifferentAnalytics()
                }
            }
        }

        val reportTypeSuccessState = reportTypeState as AnalyticsReportTypeScreenState.Success

        AnalyticsScreen(
            reportTypeState = reportTypeSuccessState,
            userReportState = userReportState,
            partReportState = partReportState,
            onBackClicked = router::popCurrentController,
            onGenerateUserReportClicked = {
                unFocusOtherScreens(AnalyticsType.USER)
                userAnalyticsScreenModel.requestAnalytics(auth, userID = 4L, it)
            },
            onGeneratePartReportClicked = {
                unFocusOtherScreens(AnalyticsType.PART)
                partAnalyticsScreenModel.requestAnalytics(auth, userID = 4L, it)
            }
        )
    }


}


