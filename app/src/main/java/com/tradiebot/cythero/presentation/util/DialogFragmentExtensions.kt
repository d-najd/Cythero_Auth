package com.tradiebot.cythero.presentation.util

import androidx.fragment.app.DialogFragment
import com.tradiebot.cythero.app.util.view.ContextHolder
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

fun DialogFragment.show(
    tag: String? = null
){
    // Preventing showing the dialog fragment multiple times and thus crashing the app
    if(!isAdded) {
        show(Injekt.get<ContextHolder>().fragmentManager, tag)
    }
}
