package com.course.breakingnews.features.about.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.breakingnews.features.about.action.AboutAction
import com.course.breakingnews.features.about.state.AboutState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AboutViewModel: ViewModel() {

    private val pendingActions = MutableSharedFlow<AboutAction>()

    private var _state: MutableStateFlow<AboutState> =
        MutableStateFlow(AboutState.Loading)
    val state: StateFlow<AboutState> = _state

    init {
        handleActions()
    }

    private fun handleActions() = viewModelScope.launch {
        pendingActions.collect() {action ->
            when (action) {
                is AboutAction.Idle -> {}
                is AboutAction.RequestOnBackPressed -> requestOnBackPressed()
            }
        }
    }

    private fun requestOnBackPressed() {
        viewModelScope.launch {
            AboutState.OnBackPressed.updateState()
        }
    }
    fun submitAction(action: AboutAction) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }

    private fun AboutState.updateState() = _state.update { this }
}