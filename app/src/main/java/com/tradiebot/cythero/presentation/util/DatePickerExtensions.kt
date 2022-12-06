package com.tradiebot.cythero.presentation.util

import androidx.fragment.app.DialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.tradiebot.cythero.app.util.view.FragmentManagerHolder
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

fun DialogFragment.show(tag: String? = null){
    show(Injekt.get<FragmentManagerHolder>().fragmentManager, tag)
}
