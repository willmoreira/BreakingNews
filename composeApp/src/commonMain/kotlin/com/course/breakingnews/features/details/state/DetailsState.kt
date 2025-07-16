package com.course.breakingnews.features.details.state

import com.course.breakingnews.features.home.state.HomeState

sealed interface DetailsState {
    data object Idle: DetailsState
    data object Loading: DetailsState

    data object OnBackPressed: DetailsState

}