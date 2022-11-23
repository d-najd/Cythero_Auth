package com.tradiebot.cythero.presentation.login

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tradiebot.cythero.app.ui.register.LoginPresenter

@Stable
interface LoginState {
    /*
    val isLoading: Boolean
    val categories: List<Category>
    var searchQuery: String?
    val selection: List<LibraryManga>
    val selectionMode: Boolean
    var hasActiveFilters: Boolean
     */
    var dialog: LoginPresenter.Dialog?
}

fun LoginState(): LoginState {
    return LoginStateImpl()
}

class LoginStateImpl : LoginState {
    /*
    override var isLoading: Boolean by mutableStateOf(true)
    override var categories: List<Category> by mutableStateOf(emptyList())
    override var searchQuery: String? by mutableStateOf(null)
    override var selection: List<LibraryManga> by mutableStateOf(emptyList())
    override val selectionMode: Boolean by derivedStateOf { selection.isNotEmpty() }
    override var hasActiveFilters: Boolean by mutableStateOf(false)
     */
    override var dialog: LoginPresenter.Dialog? by mutableStateOf(null)
}
