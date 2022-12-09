package com.tradiebot.cythero.app.util.view

import android.content.Context
import androidx.fragment.app.FragmentActivity

class ContextHolder(
    context: Context,
) {
    private val context: Context
    val fragmentManager: androidx.fragment.app.FragmentManager

    init {
        this.context = context
        fragmentManager = (context as FragmentActivity).supportFragmentManager
    }

    fun getString(id: Int): String{
        return context.getString(id)
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

