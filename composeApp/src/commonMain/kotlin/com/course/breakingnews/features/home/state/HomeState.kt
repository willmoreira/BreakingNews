package com.course.breakingnews.features.home.state

import coil3.Image
import com.course.breakingnews.domain.model.news.NewsDomain

sealed interface HomeState {
    data object Idle: HomeState
    data object Loading: HomeState

    data object NavigateToDetails: HomeState
    data object NavigateToAbout: HomeState

    //data class ShowData(val data: List<NewsDomain>) : HomeState
}