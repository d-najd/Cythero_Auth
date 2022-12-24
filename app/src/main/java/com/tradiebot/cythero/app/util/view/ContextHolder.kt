package com.tradiebot.cythero.app.util.view

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import com.tradiebot.cythero.util.launchUI
import com.tradiebot.cythero.util.toast
import kotlinx.coroutines.CoroutineScope

class ContextHolder(
    context: Context,
) {
    val appContext: Context

    /**
     * These are delicate components, use with care, alternatives are [appContext] for context across
     * the app and [composeToast] for displaying toasts in any part of the app
     */
    var composeContext: Context? = null
    var composeCoroutineScope: CoroutineScope? = null
    
    val fragmentManager: androidx.fragment.app.FragmentManager

    init {
        this.appContext = context
        fragmentManager = (context as FragmentActivity).supportFragmentManager
    }

    fun getString(id: Int): String{
        return appContext.getString(id)
    }
    
    /**
     * this is delicate api use with care
     */
    fun composeToast(@StringRes resource: Int, duration: Int = Toast.LENGTH_SHORT, block: (Toast) -> Unit = {}){
        composeToast(getString(resource), duration, block)
    }
    
    /**
     * this is delicate api use with care
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun composeToast(text: String?, duration: Int = Toast.LENGTH_SHORT, block: (Toast) -> Unit = {}) {
        composeCoroutineScope!!.launchUI {
            appContext.toast(text, duration, block)
        }
    }
}

