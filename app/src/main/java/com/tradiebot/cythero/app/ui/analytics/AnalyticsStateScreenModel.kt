package com.tradiebot.cythero.app.ui.analytics

import android.content.Context
import androidx.compose.runtime.*
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.analytics.CoverageType
import com.tradiebot.cythero.domain.analytics.Part
import com.tradiebot.cythero.domain.analytics.part.interactor.RequestPartAnalytics
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.domain.analytics.shared.interactor.RequestAnalyticSessionInfo
import com.tradiebot.cythero.domain.analytics.shared.interactor.RequestAnalyticsLabels
import com.tradiebot.cythero.domain.analytics.shared.model.AnalyticSession
import com.tradiebot.cythero.domain.analytics.shared.model.AnalyticsLabel
import com.tradiebot.cythero.domain.analytics.usage.interactor.RequestUsageAnalytics
import com.tradiebot.cythero.domain.analytics.usage.model.*
import com.tradiebot.cythero.domain.analytics.user.interactor.RequestUserAnalytics
import com.tradiebot.cythero.domain.analytics.user.model.AnalyticsUser
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.presentation.util.CytheroStateScreenModel
import com.tradiebot.cythero.util.launchIO
import com.tradiebot.cythero.util.launchUI
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.util.*
import kotlin.math.roundToInt

class AnalyticsStateScreenModel(
    val context: Context,
    val auth: Auth,
    private val requestUserAnalytics: RequestUserAnalytics = Injekt.get(),
    private val requestPartAnalytics: RequestPartAnalytics = Injekt.get(),
    
    // Usage
    private val requestUsageAnalytics: RequestUsageAnalytics = Injekt.get(),
    private val requestAnalyticsLabels: RequestAnalyticsLabels = Injekt.get(),
    private val requestAnalyticsSessionInfo: RequestAnalyticSessionInfo = Injekt.get(),
) : CytheroStateScreenModel<AnalyticsScreenState>(context, AnalyticsScreenState.Loading) {
    
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
            mutableState.update {
                AnalyticsScreenState.UserSuccess(
                    auth = auth,
                    analytics = if(userAnalytics != null) mapOf(auth.user.id!! to userAnalytics) else emptyMap()
                )
            }
        }
    }
    
    /** requesting analytics for multiple users and updates the state */
    @Suppress("unused")
    fun requestUserAnalytics(auth: Auth, userIDs: List<Long>, dateRange: Pair<Date, Date>){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsScreenState.LoadingType }
            val userAnalytics = requestUserAnalytics.await(auth, userIDs, dateRange)
            mutableState.update {
                AnalyticsScreenState.UserSuccess(
                    auth = auth,
                    analytics = userAnalytics
                )
            }
        }
    }
    
    //endregion
    
    //region part
    
    fun requestPartAnalytics(auth: Auth, userID: Long = auth.user.id!!, part: Part) {
        coroutineScope.launchIO {
            mutableState.update { AnalyticsScreenState.LoadingType }
            val partAnalytics = requestPartAnalytics.await(auth, userID, part)
            mutableState.update {
                AnalyticsScreenState.PartSuccess(
                    auth = auth,
                    analytics = if(partAnalytics != null) listOf(partAnalytics) else emptyList(),
                )
            }
        }
    }
    
    @Suppress("unused")
    fun requestPartAnalytics(auth: Auth, userIDs: List<Long>, parts: List<Part>){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsScreenState.Loading }
            val partAnalytics = requestPartAnalytics.await(auth, userIDs, parts)
            mutableState.update {
                AnalyticsScreenState.PartSuccess(
                    auth = auth,
                    analytics = partAnalytics,
                )
            }
        }
    }
    
    fun updatePartCoverageType(selectedCoverageType: CoverageType){
        coroutineScope.launchUI {
            mutableState.update {
                (it as AnalyticsScreenState.PartSuccess).copy(
                    selectedCoverageType = selectedCoverageType
                )
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
            val analyticsLabels = requestAnalyticsLabels.await(auth)
            val userAnalyticsSortable = userAnalytics?.toAnalyticsSortable() ?: AnalyticsUsageSortableHolder.emptyObj()
            
            mutableState.update {
                // the call is not useless since the labels are sorted down the line and having
                // null value crashes the app
                @Suppress("UselessCallOnCollection")
                AnalyticsScreenState.UsageSuccess(
                    auth = auth,
                    analytics = userAnalyticsSortable.sortByType(userAnalyticsSortable.sortType, userAnalyticsSortable.reverse),
                    analyticsLabels = analyticsLabels.filterNotNull(),
                )
            }
        }
    }
    
    fun updateUsageScreenIndex(increment: Boolean){
        coroutineScope.launchUI {
            mutableState.update {
                (it as AnalyticsScreenState.UsageSuccess).copy(
                    screenIndex = if(increment &&
                        it.screenIndex < (it.analytics.analyticsList.size/10f).roundToInt())
                        it.screenIndex + 1
                    else if (!increment && it.screenIndex > 1)
                        it.screenIndex - 1
                    else
                        it.screenIndex
                )
            }
        }
    }
    
    fun sortUsageAnalytics(type: AnalyticsUsageSortType, reverse: Boolean){
        coroutineScope.launchUI {
            mutableState.update {
                (it as AnalyticsScreenState.UsageSuccess).copy(
                    analytics = it.analytics.sortByType(
                        type = type,
                        reverse = reverse,
                    ),
                )
            }
        }
    }
    
    fun showUsageDialog(dialog: AnalyticsUsageDialog) {
        val usageState = (state.value as AnalyticsScreenState.UsageSuccess)
        
        when (dialog) {
            is AnalyticsUsageDialog.ItemInfo -> {
                coroutineScope.launchIO {
                    val analyticSessionInfo = requestAnalyticsSessionInfo.await(
                        userAuth = usageState.auth,
                        sessionID = dialog.sessionID,
                    )
                    if (analyticSessionInfo.isNotEmpty()) {
                        mutableState.update {
                            usageState.copy(
                                analyticsSessionInfo = analyticSessionInfo,
                                dialog = dialog
                            )
                        }
                    }
                }
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
    data class ItemInfo(val analytic: AnalyticsUsageSortable, val sessionID: String) : AnalyticsUsageDialog()
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
    
    /**
     * if the screen has loaded successfully but before content has loaded in or has failed to load in,
     * is called after [Loading] and before [LoadingType]
     **/
    @Immutable
    data class Success(
        val auth: Auth
    ) : AnalyticsScreenState()
    
    /** if a report type is being processed, this happens after [Success]
     **/
    @Immutable
    object LoadingType : AnalyticsScreenState()
    
    /** if user analytics were success, gets called after [LoadingType] */
    @Immutable
    data class UserSuccess(
        val auth: Auth,
        val analytics: Map<Long, AnalyticsUser>,
    ) : AnalyticsScreenState()
    
    
    /** if part analytics were success, gets called after [LoadingType] */
    @Immutable
    data class PartSuccess(
        val auth: Auth,
        val analytics: List<AnalyticsPart>,
        val selectedCoverageType: CoverageType = CoverageType.OVERALL,
    ) : AnalyticsScreenState()
    
    
    /** if usage analytics were success, gets called after [LoadingType] */
    @Immutable
    data class UsageSuccess(
        val auth: Auth,
        val analytics: AnalyticsUsageSortableHolder,
        val analyticsLabels: List<AnalyticsLabel>,
        val analyticsSessionInfo: List<AnalyticSession>? = null,
        val screenIndex: Int = 1,
        val dialog: AnalyticsUsageDialog? = null,
    ) : AnalyticsScreenState()
    
}
