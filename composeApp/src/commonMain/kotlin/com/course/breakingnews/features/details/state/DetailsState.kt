package com.course.breakingnews.features.details.state

sealed interface DetailsState {
    data object Idle: DetailsState
    data object Loading: DetailsState
}