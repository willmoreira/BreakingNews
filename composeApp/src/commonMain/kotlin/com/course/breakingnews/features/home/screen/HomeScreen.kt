package com.course.breakingnews.features.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.course.breakingnews.ui.card.BreakingNewsCard
import com.course.breakingnews.ui.top.BreakingNewsTopBar


@Composable
fun HomeScreen(
    navigateToAboutScreen: () -> Unit,
    navigateToDetailsScreen: () -> Unit
) {
    HomeContent(
        navigateToAboutScreen = navigateToAboutScreen,
        navigateToDetailsScreen = navigateToDetailsScreen
    )
}

@Composable
fun HomeContent(
    navigateToAboutScreen: () -> Unit,
    navigateToDetailsScreen: () -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = Color.White,
        topBar = {
            BreakingNewsTopBar(onClick = { navigateToAboutScreen.invoke() })
        },
        content = {paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "BreakingNews",
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )

                BreakingNewsCard(
                    title = "aqui aqui aqiu",
                    author = "William Moreira",
                    date = "9 de abril de 2025",
                    onClick = { navigateToDetailsScreen.invoke() }
                )

                BreakingNewsCard(
                    title = "aqui aqui aqiu",
                    author = "William Moreira",
                    date = "9 de abril de 2025",
                    onClick = { navigateToDetailsScreen.invoke() }
                )
            }
        }
    )
}