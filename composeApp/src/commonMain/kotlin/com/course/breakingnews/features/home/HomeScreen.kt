package com.course.breakingnews.features.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    navigateToAboutScreen: () -> Unit
) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(64.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor =  Color.LightGray,
                contentColor = Color.Black
            ),
            onClick = {
                navigateToAboutScreen.invoke()
            },
            modifier =  Modifier.border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(2.dp)
            )
        ) {
                Text("Navegar")
        }
    }
}