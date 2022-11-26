package com.tradiebot.cythero.app.ui.user_info

import android.os.Bundle
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.tradiebot.cythero.app.ui.base.controller.FullComposeController
import com.tradiebot.cythero.domain.user.model.User

class UserInfoController(
    user: User? = null,
) : FullComposeController() {
    val user: User

    init {
        this.user = user!!
    }

    @Composable
    override fun ComposeContent() {
        Navigator(screen = UserInfoScreen(user))
    }

}