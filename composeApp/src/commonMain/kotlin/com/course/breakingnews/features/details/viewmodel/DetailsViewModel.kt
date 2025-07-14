package com.course.breakingnews.features.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.breakingnews.features.details.action.DetailsAction
import com.course.breakingnews.features.details.state.DetailsState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {

    private val pendingActions = MutableSharedFlow<DetailsAction>()

    private var _state: MutableStateFlow<DetailsState> =
        MutableStateFlow(DetailsState.Loading)
    val state: StateFlow<DetailsState> = _state

    init {
        handleActions()
    }

    private fun handleActions() = viewModelScope.launch {
        pendingActions.collect() {action ->
            when (action) {
                is DetailsAction.Idle -> {}
            }
        }
    }

    fun submitAction(action: DetailsAction) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }

    private fun DetailsState.updateState() = _state.update { this }
}