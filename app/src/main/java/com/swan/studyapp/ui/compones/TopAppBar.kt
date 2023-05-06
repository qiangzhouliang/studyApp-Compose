package com.swan.studyapp.ui.compones


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.swan.studyapp.ui.theme.Blue200
import com.swan.studyapp.ui.theme.Blue700

//标题栏高度
val appBarHeight = 56.dp

/**
 * 统一标题栏
 *
 * @param modifier
 * @param content  标题栏内容
 */
@Composable
fun TopAppBar(modifier: Modifier = Modifier, content: @Composable () -> Unit) {

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(key1 = Unit) {
        systemUiController.setStatusBarColor(Color.Transparent)
    }

    //转换状态栏高度 px 为 dp
    val statusBarHeightDp = with(LocalDensity.current) {
        // 获取状态栏高度
        WindowInsets.statusBars.getTop(this).toDp()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Brush.linearGradient(listOf(Blue700, Blue200))) // 背景颜色线性渐变
            .height(appBarHeight + statusBarHeightDp)
            .padding(top = statusBarHeightDp)
            .then(modifier),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }

}

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar() {
        Text("标题")
    }
}

