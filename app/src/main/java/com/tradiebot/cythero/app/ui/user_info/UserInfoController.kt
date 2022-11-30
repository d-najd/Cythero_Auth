package com.tradiebot.cythero.app.ui.user_info

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import cafe.adriel.voyager.navigator.Navigator
import com.google.gson.Gson
import com.tradiebot.cythero.app.ui.base.controller.FullComposeController
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.domain.user.model.User

class UserInfoController: FullComposeController {

    constructor(authString: String) : super(
        bundleOf(
            AUTH_STRING_EXTRA to authString,
        ),
    )

    /**
     * TODO test if this causes performance issues
     */
    constructor(auth: Auth): super(
        bundleOf(
            AUTH_STRING_EXTRA to Gson().toJson(auth),
        )
    )

    @Suppress("unused")
    constructor(bundle: Bundle) : this(
        bundle.getString(AUTH_STRING_EXTRA)!!,
    )

    val auth: Auth
        get() = Gson().fromJson(args.getString(AUTH_STRING_EXTRA), Auth::class.java)!!

    @Composable
    override fun ComposeContent() {
        Navigator(screen = UserInfoScreen(auth))
    }

    companion object{
        const val AUTH_STRING_EXTRA = "auth"
    }
}