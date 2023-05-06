package com.swan.studyapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.swan.studyapp.ui.compones.TopAppBar

@Composable
fun StudyScreen(
    modifier: Modifier = Modifier
) {
    Column {
        TopAppBar {
            Text(text = "学习页")
        }
        Text(text = "学习页")
    }
}

@Preview(name = "StudyScreen")
@Composable
private fun PreviewStudyScreen() {
    StudyScreen()
}