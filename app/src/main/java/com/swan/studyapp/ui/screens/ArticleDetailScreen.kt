package com.swan.studyapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.swan.studyapp.ui.compones.WebView
import com.swan.studyapp.ui.compones.rememberWebViewState
import com.swan.studyapp.ui.theme.Blue200
import com.swan.studyapp.viewModel.ArticleViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ArticleDetailScreen(articleViewModel: ArticleViewModel = viewModel(), onBack: () -> Unit) {

    //LaunchedEffect(Unit) {
    //    articleViewModel.fetchInfo()
    //}

    //val webViewState = rememberWebViewState(data = articleViewModel.content)
    val webViewState = rememberWebViewState(url = "https://www.baidu.com")

    var fontScale by remember {
        mutableStateOf(1.0f)
    }

    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Blue200,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                title = {
                    Text(
                        text = "文章详情",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.NavigateBefore,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { onBack() }
                            .padding(8.dp)
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.TextFields,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                coroutineScope.launch {
                                    if (scaffoldState.bottomSheetState.isCollapsed) {
                                        scaffoldState.bottomSheetState.expand()
                                    } else {
                                        scaffoldState.bottomSheetState.collapse()
                                    }
                                }
                            }
                            .padding(8.dp)
                    )
                }
            )
        },
        sheetContent = {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = "字体大小", fontSize = 16.sp)

                Slider(value = fontScale, onValueChange = {
                    fontScale = it
                    webViewState.evaluateJavascript("document.body.style.zoom = $it")
                }, steps = 3, valueRange = 0.75f..1.75f)

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "较小",
                        fontSize = 14.sp,
                        color = Color(0xFF999999)
                    ) // 0.75
                    Text(
                        text = "标准",
                        fontSize = 14.sp,
                        color = Color(0xFF999999)
                    ) // 1.0
                    Text(
                        text = "普大",
                        fontSize = 14.sp,
                        color = Color(0xFF999999)
                    ) // 1.25
                    Text(
                        text = "超大",
                        fontSize = 14.sp,
                        color = Color(0xFF999999)
                    ) // 1.5
                    Text(
                        text = "特大",
                        fontSize = 14.sp,
                        color = Color(0xFF999999)
                    ) // 1.75
                }


                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        },
        sheetPeekHeight = 0.dp
    ) {
        WebView(state = webViewState)
    }
}

