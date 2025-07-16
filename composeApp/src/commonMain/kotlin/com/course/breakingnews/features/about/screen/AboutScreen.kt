package com.course.breakingnews.features.about.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.course.breakingnews.features.about.action.AboutAction
import com.course.breakingnews.features.about.state.AboutState
import com.course.breakingnews.features.about.viewmodel.AboutViewModel
import com.course.breakingnews.platform.Platform
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AboutScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<AboutViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    AboutContent(
        state = state,
        onBackPressed = onBackPressed,
        action = viewModel::submitAction
    )
}

@Composable
fun AboutContent(
    state: AboutState,
    onBackPressed: () -> Unit,
    action: (AboutAction) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(androidx.compose.foundation.layout.WindowInsets.systemBars.asPaddingValues())
    ) {



        items(makeItems()) { item ->
            AboutComponent(
                title = item.first,
                subTitle = item.second,
            )
        }

        item {
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                when (state){
                    is AboutState.Idle -> {}
                    is AboutState.Loading -> {}
                    is AboutState.OnBackPressed -> {
                        action(AboutAction.Idle)
                        onBackPressed.invoke()
                    }
                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor =  Color.LightGray,
                        contentColor = Color.Black
                    ),
                    onClick = { action(AboutAction.RequestOnBackPressed) }
                ) {
                    Text("Voltar")
                }
            }
        }
    }
}

@Composable
private fun AboutComponent(
    title: String,
    subTitle: String
) {

    Column (modifier = Modifier.padding(16.dp)) {

        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = subTitle,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))


    }

    HorizontalDivider()
}

private fun makeItems() : List<Pair<String, String>> {
    val platform = Platform()
    return  listOf(
        Pair("Sistema operacional", "${platform.osName} ${platform.osVersion}"),
        Pair("Dispositivo", platform.deviceModel),
        )
}