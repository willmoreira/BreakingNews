package com.course.breakingnews.features.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.course.breakingnews.features.home.action.HomeAction
import com.course.breakingnews.features.home.state.HomeState
import com.course.breakingnews.features.home.viewmodel.HomeViewModel
import com.course.breakingnews.ui.card.BreakingNewsCard
import com.course.breakingnews.ui.loading.Loading
import com.course.breakingnews.ui.top.BreakingNewsTopBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    navigateToAboutScreen: () -> Unit,
    navigateToDetailsScreen: (String, String) -> Unit
) {

    val viewModel = koinViewModel<HomeViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LifecycleEventEffect(
        event = Lifecycle.Event.ON_START
    ) {
        viewModel.submitAction(HomeAction.RequestBreakingNews)
    }

    HomeContent(
        state = state,
        navigateToAboutScreen = navigateToAboutScreen,
        navigateToDetailsScreen = navigateToDetailsScreen,
        action = viewModel::submitAction
    )
}

@Composable
fun HomeContent(
    state: HomeState,
    action: (HomeAction) -> Unit,
    navigateToAboutScreen: () -> Unit,
    navigateToDetailsScreen: (String, String) -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = Color.White,
        topBar = {
            BreakingNewsTopBar(onClick = {
                action(HomeAction.RequestNavigateToAbout)
            })
        },
        content = {paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.Start
            ) {

                when (state) {
                    is HomeState.Idle -> {}
                    is HomeState.Loading -> Loading()
                    is HomeState.NavigateToDetails -> {
                        action(HomeAction.Idle)
                        navigateToDetailsScreen.invoke(state.urlToImage, state.description)
                    }

                    is HomeState.NavigateToAbout -> {
                        action(HomeAction.Idle)
                        navigateToAboutScreen.invoke()
                    }

                    is HomeState.ShowData -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {

                            item {
                                Text(
                                    modifier = Modifier.padding(16.dp),
                                    text = "BreakingNews",
                                    fontSize = 24.sp,
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    textAlign = TextAlign.Start
                                )
                            }

                            items(state.data) { item ->
                                BreakingNewsCard(
                                    title = item.title.orEmpty(),
                                    author = item.author.orEmpty(),
                                    date = item.publishedAt.orEmpty(),
                                    imageUrl = item.urlToImage.orEmpty(),
                                    onClick = { action(HomeAction.RequestNavigateToDetails(
                                        urlToImage = item.urlToImage.orEmpty(),
                                        description = item.description.orEmpty()
                                    )) }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}