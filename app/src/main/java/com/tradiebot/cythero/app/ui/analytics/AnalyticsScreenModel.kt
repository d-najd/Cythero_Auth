package com.tradiebot.cythero.app.ui.analytics

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.analytics.Part
import com.tradiebot.cythero.domain.analytics.part.interactor.RequestPartAnalytics
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.domain.analytics.usage.interactor.RequestUsageAnalytics
import com.tradiebot.cythero.domain.analytics.usage.interactor.RequestUsageAnalyticsLabels
import com.tradiebot.cythero.domain.analytics.usage.model.*
import com.tradiebot.cythero.domain.analytics.user.interactor.RequestUserAnalytics
import com.tradiebot.cythero.domain.analytics.user.model.AnalyticsUser
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.util.launchIO
import com.tradiebot.cythero.util.launchUI
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import logcat.logcat
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.util.*

class AnalyticsScreenModel(
    val context: Context,
    val auth: Auth,
    private val requestUserAnalytics: RequestUserAnalytics = Injekt.get(),
    private val requestPartAnalytics: RequestPartAnalytics = Injekt.get(),
    
    // Usage
    private val requestUsageAnalytics: RequestUsageAnalytics = Injekt.get(),
    private val requestUsageAnalyticsLabels: RequestUsageAnalyticsLabels = Injekt.get(),
) : StateScreenModel<AnalyticsScreenState>(AnalyticsScreenState.Loading) {

    init {
        coroutineScope.launch {
            mutableState.update {
                AnalyticsScreenState.Success(
                    auth = auth
                )
            }
        }
    }

    // region user

    /** requesting analytics for single user and updates the state */
    fun requestUserAnalytics(auth: Auth, userID: Long = auth.user.id!!, dateRange: Pair<Date, Date>){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsScreenState.LoadingType }
            val userAnalytics = requestUserAnalytics.await(auth, userID, dateRange)
            if(userAnalytics != null) {
                mutableState.update {
                    AnalyticsScreenState.UserSuccess(
                        auth = auth,
                        analytics = mapOf(auth.user.id!! to userAnalytics)
                    )
                }
            } else {
                logcat { "Something went wrong" }
                mutableState.update {
                    AnalyticsScreenState.Success(
                        auth = auth
                    )
                }
            }
        }
    }

    /** requesting analytics for multiple users and updates the state */
    @Suppress("unused")
    fun requestUserAnalytics(auth: Auth, userIDs: List<Long>, dateRange: Pair<Date, Date>){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsScreenState.LoadingType }
            val userAnalytics = requestUserAnalytics.await(auth, userIDs, dateRange)
            if(userAnalytics.isNotEmpty()) {
                mutableState.update {
                    AnalyticsScreenState.UserSuccess(
                        auth = auth,
                        analytics = userAnalytics,
                    )
                }
            } else {
                logcat { "Something went wrong" }
                mutableState.update {
                    AnalyticsScreenState.Success(
                        auth = auth
                    )
                }
            }
        }
    }

    //endregion

    //region part

    fun requestPartAnalytics(auth: Auth, userID: Long = auth.user.id!!, part: Part) {
        coroutineScope.launchIO {
            mutableState.update { AnalyticsScreenState.LoadingType }
            val partAnalytics = requestPartAnalytics.await(auth, userID, part)
            if (partAnalytics != null) {
                val temp = listOf(partAnalytics) // TODO fix crash here when large object's mutable state tries to be updated
                mutableState.update {
                    AnalyticsScreenState.PartSuccess(
                        auth = auth,
                        analytics = temp
                    )
                }
            } else {
                logcat { "Something went wrong" }
                mutableState.update {
                    AnalyticsScreenState.Success(
                        auth = auth
                    )
                }
            }
        }
    }

    @Suppress("unused")
    fun requestPartAnalytics(auth: Auth, userIDs: List<Long>, parts: List<Part>){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsScreenState.Loading }
            val partAnalytics = requestPartAnalytics.await(auth, userIDs, parts)
            if(partAnalytics.isNotEmpty()) {
                mutableState.update {
                    AnalyticsScreenState.PartSuccess(
                        auth = auth,
                        analytics = partAnalytics,
                    )
                }
            } else {
                logcat { "Something went wrong" }
                mutableState.update {
                    AnalyticsScreenState.Success(
                        auth = auth,
                    )
                }
            }
        }
    }

    //endregion
    
    //region usage
    
    /** requesting analytics for single user and updates the state */
    fun requestUsageAnalytics(auth: Auth, userID: Long = auth.user.id!!, dateRange: Pair<Date, Date>){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsScreenState.LoadingType }
            
            val userAnalytics = requestUsageAnalytics.await(auth, userID, dateRange)
            val analyticsLabels = requestUsageAnalyticsLabels.await(auth)
            
            if(userAnalytics != null && analyticsLabels.isNotEmpty()) {
                val userAnalyticsSortable = userAnalytics.toAnalyticsSortable()
                mutableState.update {
                    AnalyticsScreenState.UsageSuccess(
                        auth = auth,
                        analytics = userAnalyticsSortable.sortByType(userAnalyticsSortable.sortType, userAnalyticsSortable.reverse),
                        analyticsLabels = analyticsLabels,
                    )
                }
            } else {
                logcat { "Something went wrong" }
                mutableState.update {
                    AnalyticsScreenState.Success(
                        auth = auth,
                    )
                }
            }
        }
    }
    
    fun sortUsageAnalytics(type: AnalyticsUsageSortType, reverse: Boolean){
        coroutineScope.launchUI {
            mutableState.update {
                if(state.value !is AnalyticsScreenState.UsageSuccess)
                    throw IllegalStateException("Sorting usage analytics when not in usage analytics?")
                val usageState = (state.value as AnalyticsScreenState.UsageSuccess)
                AnalyticsScreenState.UsageSuccess(
                    auth = auth,
                    analytics = usageState.analytics.sortByType(
                        type = type,
                        reverse = reverse,
                    ),
                    usageState.analyticsLabels
                )
            }
        }
    }
    
    fun showUsageDialog(dialog: AnalyticsUsageDialog) {
        mutableState.update {
            when (it) {
                is AnalyticsScreenState.UsageSuccess -> { it.copy(dialog = dialog) }
                else -> it
            }
        }
    }
    
    fun dismissUsageDialog() {
        mutableState.update {
            when (it) {
                is AnalyticsScreenState.UsageSuccess -> it.copy(dialog = null)
                else -> it
            }
        }
    }
    
    // endregion
    
}

sealed class AnalyticsUsageDialog {
    data class ItemInfo(val analytic: AnalyticsUsageSortable): AnalyticsUsageDialog()
    /*
    object Create : CategoryDialog()
    data class Rename(val category: Category) : CategoryDialog()
    data class Delete(val category: Category) : CategoryDialog()
     */
}

sealed class AnalyticsScreenState {

    /** if the screen is loading in */
    @Immutable
    object Loading : AnalyticsScreenState()

    /** if the screen has successfully loaded but no screen has been selected */
    @Immutable
    data class Success(
        val auth: Auth,
    ) : AnalyticsScreenState()

    /** if a report type is being processed but the screen has loaded */
    @Immutable
    object LoadingType : AnalyticsScreenState()

    /** if user analytics were success */
    @Immutable
    data class UserSuccess(
        val auth: Auth,
        val analytics: Map<Long, AnalyticsUser>,
    ) : AnalyticsScreenState()


    /** if part analytics were success */
    @Immutable
    data class PartSuccess(
        val auth: Auth,
        val analytics: List<AnalyticsPart>,
    ) : AnalyticsScreenState()
    
    
    /** if usage analytics were success */
    @Immutable
    data class UsageSuccess(
        val auth: Auth,
        val analytics: AnalyticsUsageSortableHolder,
        val analyticsLabels: List<AnalyticsUsageLabel>,
        val dialog: AnalyticsUsageDialog? = null,
    ) : AnalyticsScreenState()
    
}
