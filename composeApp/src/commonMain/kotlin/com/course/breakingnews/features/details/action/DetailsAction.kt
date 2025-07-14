package com.course.breakingnews.features.details.action

sealed interface DetailsAction {
    data object Idle: DetailsAction
}