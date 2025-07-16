package com.course.breakingnews.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.breakingnews.features.home.action.HomeAction
import com.course.breakingnews.features.home.state.HomeState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val pendingActions = MutableSharedFlow<HomeAction>()

    private var _state: MutableStateFlow<HomeState> =
        MutableStateFlow(HomeState.Loading)
    val state: StateFlow<HomeState> = _state

    init {
        handleActions()
    }

    private fun handleActions() = viewModelScope.launch {
        pendingActions.collect() {action ->
            when (action) {
                is HomeAction.Idle -> requestIdleState()
                is HomeAction.RequestNavigateToDetails -> navigateToDetails()
                is HomeAction.RequestNavigateToAbout -> navigateToAbout()
            }
        }
    }

    private fun requestIdleState() {
        viewModelScope.launch {
            HomeState.Idle.updateState()
        }
    }

    private fun navigateToDetails() {
        viewModelScope.launch {
            HomeState.NavigateToDetails.updateState()
        }
    }

    private fun navigateToAbout() {
        viewModelScope.launch {
            HomeState.NavigateToAbout.updateState()
        }
    }

    fun submitAction(action: HomeAction) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }

    private fun HomeState.updateState() = _state.update { this }
}
