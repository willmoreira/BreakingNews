package com.course.breakingnews.di.presentation

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import com.course.breakingnews.features.details.viewmodel.DetailsViewModel
import com.course.breakingnews.features.home.viewmodel.HomeViewModel
import com.course.breakingnews.features.about.viewmodel.AboutViewModel

val presentationModule = module {
    // Home
    viewModelOf(::HomeViewModel)

    // Details
    viewModelOf(::DetailsViewModel)

    // About
    viewModelOf(::AboutViewModel)
}