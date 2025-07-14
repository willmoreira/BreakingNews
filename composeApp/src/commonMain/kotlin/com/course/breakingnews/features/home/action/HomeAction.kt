package com.course.breakingnews.features.home.action

sealed interface HomeAction {
    data object Idle: HomeAction
}