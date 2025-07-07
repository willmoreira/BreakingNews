package com.course.breakingnews

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform