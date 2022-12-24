package com.tradiebot.cythero.presentation.util

import android.content.Context
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.app.util.view.ContextHolder
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

open class CytheroStateScreenModel<S> (context: Context, initialState: S):
	StateScreenModel<S>(initialState = initialState) {
	init {
		Injekt.get<ContextHolder>().composeContext = context
		Injekt.get<ContextHolder>().composeCoroutineScope = coroutineScope
	}
}
