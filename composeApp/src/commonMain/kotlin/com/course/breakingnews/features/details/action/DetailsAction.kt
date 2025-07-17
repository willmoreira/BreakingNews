package com.course.breakingnews.features.details.action

import com.course.breakingnews.features.home.action.HomeAction

sealed interface DetailsAction {
    data object Idle: DetailsAction
    data object RequestOnBackPressed: DetailsAction
    data object RequestUpdateView: DetailsAction
}