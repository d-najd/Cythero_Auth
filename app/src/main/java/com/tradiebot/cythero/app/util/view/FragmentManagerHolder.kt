package com.tradiebot.cythero.app.util.view

import android.content.Context
import androidx.fragment.app.FragmentActivity

class FragmentManagerHolder(
    context: Context,
) {
    private val context: Context
    val fragmentManager: androidx.fragment.app.FragmentManager

    init {
        this.context = context
        fragmentManager = (context as FragmentActivity).supportFragmentManager
    }

    /*
    @Composable
    fun rememberFragmentManager(): FragmentManager {
        return remember(context) {
            fragmentManager
        }
    }
     */
}

