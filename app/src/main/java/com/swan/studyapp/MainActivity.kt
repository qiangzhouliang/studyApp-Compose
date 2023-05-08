package com.swan.studyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.swan.studyapp.ui.compones.NavHostApp
import com.swan.studyapp.ui.theme.StudyAppTheme
import com.tencent.live2.V2TXLivePremier
import com.tencent.live2.V2TXLivePremier.V2TXLivePremierObserver


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 处理不同机型 导航栏不透明问题
        //window.statusBarColor = Color.Transparent.value.toInt()
        // 处理不同机型 导航栏遮盖内容问题
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        // 关闭系统导航栏 让内容显示在状态栏和系统导航栏后面: 状态栏和导航栏会遮盖部分内容
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val licenceURL = "https://license.vod2.myqcloud.com/license/v2/1311977056_1/v_cube.license" // 获取到的 licence url
        val licenceKey = "41f64096ca6403b23704d1e172fc6873" // 获取到的 licence key

        V2TXLivePremier.setLicence(this, licenceURL, licenceKey)
        V2TXLivePremier.setObserver(object : V2TXLivePremierObserver() {
            override fun onLicenceLoaded(result: Int, reason: String) {
            }
        })



        setContent {
            StudyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHostApp()
                }
            }
        }
    }
}