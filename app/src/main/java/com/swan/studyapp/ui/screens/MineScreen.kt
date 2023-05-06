package com.swan.studyapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.swan.studyapp.ui.compones.TopAppBar

@Composable
fun MineScreen(
    modifier: Modifier = Modifier
) {
    Column {
        TopAppBar {
            Text(text = "我的")
        }
        Text(text = "我的页面")
    }
}

@Preview(name = "MineScreen")
@Composable
private fun PreviewMineScreen() {
    MineScreen()
}