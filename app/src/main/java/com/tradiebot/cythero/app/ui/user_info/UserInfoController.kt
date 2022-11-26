package com.tradiebot.cythero.app.ui.user_info

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import cafe.adriel.voyager.navigator.Navigator
import com.google.gson.Gson
import com.tradiebot.cythero.app.ui.base.controller.FullComposeController
import com.tradiebot.cythero.app.ui.login.LoginScreen
import com.tradiebot.cythero.domain.user.model.User

class UserInfoController: FullComposeController {

    constructor(userString: String) : super(
        bundleOf(
            USER_STRING_EXTRA to userString,
        ),
    )

    constructor(user: User): super(
        bundleOf(
            USER_STRING_EXTRA to Gson().toJson(user),
        )
    )

    @Suppress("unused")
    constructor(bundle: Bundle) : this(
        bundle.getString(USER_STRING_EXTRA)!!,
    )

    val user: User
        get() = Gson().fromJson(args.getString(USER_STRING_EXTRA), User::class.java)!!

    @Composable
    override fun ComposeContent() {
        Navigator(screen = UserInfoScreen(user))
    }

    companion object{
        const val USER_STRING_EXTRA = "user"
    }
}