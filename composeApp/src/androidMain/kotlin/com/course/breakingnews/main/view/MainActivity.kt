package com.course.breakingnews.main.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.course.breakingnews.main.App
import com.course.breakingnews.ui.card.BreakingNewsCard
import com.course.breakingnews.ui.content.DeTailsContentSection
import com.course.breakingnews.ui.header.DetailsHeaderSection
import com.course.breakingnews.ui.top.BreakingNewsTopBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComponentsPreview() {
    Column {
        BreakingNewsTopBar( onClick = {})
        BreakingNewsCard(
            title = "aqui aqui aqiu",
            author = "William Moreira",
            date = "9 de abril de 2025",
            onClick = {}
        )
        DetailsHeaderSection( onClick = {})
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

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}