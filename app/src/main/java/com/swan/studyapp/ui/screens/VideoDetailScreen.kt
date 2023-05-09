package com.swan.studyapp.ui.screens


import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.swan.studyapp.ui.compones.WebView
import com.swan.studyapp.ui.compones.rememberWebViewState
import com.swan.studyapp.ui.compones.video.VideoPlayer
import com.swan.studyapp.ui.compones.video.rememberVodController
import com.swan.studyapp.ui.theme.Blue200
import com.swan.studyapp.viewModel.VideoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VideoDetailScreen(videoViewModel: VideoViewModel = viewModel(), onBack: () -> Unit) {

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        videoViewModel.fetchInfo()
    }

    val webViewState = rememberWebViewState(data = videoViewModel.videoDesc)


    val configuration = LocalConfiguration.current

    var scaffoldModifier by remember { mutableStateOf(Modifier.alpha(0f)) }

    var videoBoxModifier by remember { mutableStateOf(Modifier.aspectRatio(16 / 9f)) }


    Scaffold(
        topBar = {
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                systemUiController.setStatusBarColor(color = Color.Transparent)
                TopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Blue200,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White
                    ),
                    title = {
                        Text(
                            text = "视频详情",
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
                                .clickable {
                                    onBack()
                                }
                                .padding(8.dp)
                        )
                    },
                )
            }
        },
        modifier = scaffoldModifier,
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it), contentAlignment = Alignment.Center) {
            if (videoViewModel.infoLoaded) {
                Column(modifier = Modifier.fillMaxSize()) {
                    val vodController =
                        rememberVodController(
                            videoUrl = videoViewModel.videoUrl,
                            coverUrl = videoViewModel.coverUrl
                        )
                    //TODO 横屏后，点击屏幕状态栏即显示出来，而且不会再隐藏，如何处理这个问题？
                    LaunchedEffect(configuration.orientation) {
                        vodController.restore()
                        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                            videoBoxModifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16 / 9f)
                            systemUiController.isSystemBarsVisible = true

                            scaffoldModifier = Modifier
                                .background(Blue200)
                                .statusBarsPadding()

                        } else {
                            videoBoxModifier = Modifier.fillMaxSize()
                            systemUiController.isSystemBarsVisible = false
                            systemUiController.setStatusBarColor(Blue200)

                            scaffoldModifier = Modifier
                        }
                    }

                    //视频区域
                    Box(modifier = videoBoxModifier) {

                        //VideoView(vodPlayer = vodController.vodPlayer)
                        VideoPlayer(vodController = vodController)
                    }

                    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                        //想让标题一起滚动，有两个方案
                        //方案一：把标题放到视频简介的 html 文本中去
                        //方案二：计算 视频简介在 webview 中的高度，然后动态设置 webview 的高度
                        WebView(state = webViewState)
                    }

                }
            } else {
                CircularProgressIndicator()
            }
        }
    }

}


