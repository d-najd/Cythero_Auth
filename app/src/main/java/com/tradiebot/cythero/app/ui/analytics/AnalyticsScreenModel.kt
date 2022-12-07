package com.tradiebot.cythero.app.ui.analytics

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.analytics.interactor.RequestAnalytics
import com.tradiebot.cythero.domain.analytics.model.Analytics
import com.tradiebot.cythero.domain.auth.model.Auth
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class AnalyticsScreenModel(
    val context: Context,
    val auth: Auth,
    private val requestAnalytics: RequestAnalytics = Injekt.get(),
) : StateScreenModel<AnalyticsScreenState>(AnalyticsScreenState.Loading) {

    init {
        coroutineScope.launch {
            mutableState.update {
                AnalyticsScreenState.SelectingData
            }
        }

        /*
        coroutineScope.launchIO {
            // TODO replace this with user id when done
            val userAnalytics = requestAnalytics.await(auth, 4L)
            if(userAnalytics != null) {
                mutableState.update {
                    AnalyticsScreenState.Success(
                        auth = auth,
                        // FIXME passing the user id here is not correct
                        userAnalytics = mapOf(auth.user.id!! to userAnalytics),
                    )
                }
            } else {
                logcat { "Something went wrong" }
            }
        }
         */
    }

}
sealed class AnalyticsScreenState {

    /** The Screen is loading */
    @Immutable
    object Loading : AnalyticsScreenState()

    /** The Screen has loaded but the user has not requested data report, this happens after [Loading] */
    @Immutable
    object SelectingData: AnalyticsScreenState()

    /** The Screen has loaded and the report type is being requested,
     * this happens after is after [SelectingData] */
    @Immutable
    data class RequestingData(
        // TODO Should have enum report type, user ids (list), start date, end date
        val test: String,
    ) : AnalyticsScreenState()

    @Immutable
    data class Success(
        val auth: Auth,
        val userAnalytics: Map<Long, Analytics>,
    ) : AnalyticsScreenState()
}
