package com.course.breakingnews.features.details.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.course.breakingnews.features.details.action.DetailsAction
import com.course.breakingnews.features.details.state.DetailsState
import com.course.breakingnews.features.details.viewmodel.DetailsViewModel
import com.course.breakingnews.features.home.action.HomeAction
import com.course.breakingnews.features.home.screen.HomeContent
import com.course.breakingnews.features.home.viewmodel.HomeViewModel
import com.course.breakingnews.ui.content.DeTailsContentSection
import com.course.breakingnews.ui.header.DetailsHeaderSection
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailsScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<DetailsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    DetailsContent(
        state = state,
        onBackPressed = onBackPressed,
        action = viewModel::submitAction
    )
}

@Composable
fun DetailsContent(
    state: DetailsState,
    onBackPressed: () -> Unit,
    action: (DetailsAction) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = Color.White,
        topBar = {},
        content = { paddingValues ->
            Column(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            )  {

                when (state){
                    is DetailsState.Idle -> {}
                    is DetailsState.Loading -> {}
                    is DetailsState.OnBackPressed -> {
                        action(DetailsAction.Idle)
                        onBackPressed.invoke()
                    }
                }

                DetailsHeaderSection( onClick = { action(DetailsAction.RequestOnBackPressed) })

                DeTailsContentSection(
                    content = """
                Teste teste Teste aqui teste Teste aqui teste
                Teste aqui teste Teste aqui teste Teste aqui teste
                Teste aqui teste
                Teste aqui teste Teste aqui teste Teste aqui teste
                Teste aqui teste Teste aqui teste Teste aqui teste
                Teste aqui teste
                Teste aqui teste Teste aqui teste Teste aqui teste
                Teste aqui teste Teste aqui teste Teste aqui teste
                Teste aqui teste
                Teste aqui teste Teste aqui teste Teste aqui teste
                Teste aqui teste Teste aqui teste Teste aqui teste
                Teste aqui teste
                """.trimIndent()
                )
            }
        }
    )
}
