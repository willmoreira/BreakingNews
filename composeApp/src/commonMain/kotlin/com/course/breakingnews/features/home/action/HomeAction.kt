package com.course.breakingnews.features.home.action

import breakingnews.composeapp.generated.resources.Res

sealed interface HomeAction {
    data object Idle: HomeAction
    data class RequestNavigateToDetails(
        val urlToImage: String,
        val description: String
    ): HomeAction
    data object RequestNavigateToAbout: HomeAction
    data object RequestBreakingNews: HomeAction

}