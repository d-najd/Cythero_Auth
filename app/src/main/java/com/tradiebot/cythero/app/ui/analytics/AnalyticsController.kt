package com.tradiebot.cythero.app.ui.analytics

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import cafe.adriel.voyager.navigator.Navigator
import com.google.gson.Gson
import com.tradiebot.cythero.app.ui.base.controller.FullComposeController
import com.tradiebot.cythero.domain.auth.model.Auth
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class AnalyticsController: FullComposeController {

    constructor(authString: String) : super(
        bundleOf(
            AUTH_STRING_EXTRA to authString,
        ),
    )

    /**
     * TODO auth should be saved as shared preference instead, this has the benefit of being able to read
     *  it after the app is closed and probably has better performance
     */
    constructor(auth: Auth): super(
        bundleOf(
            AUTH_STRING_EXTRA to Injekt.get<Gson>().toJson(auth),
        )
    )

    @Suppress("unused")
    constructor(bundle: Bundle) : this(
        bundle.getString(AUTH_STRING_EXTRA)!!,
    )

    val auth: Auth
        get() = Injekt.get<Gson>().fromJson(args.getString(AUTH_STRING_EXTRA), Auth::class.java)!!

    @Composable
    override fun ComposeContent() {
        Navigator(screen = AnalyticsScreen(auth))
    }

    companion object{
        const val AUTH_STRING_EXTRA = "auth"
    }
}