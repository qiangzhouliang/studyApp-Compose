package com.swan.studyapp.ui.compones


import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.swan.studyapp.compositionLocal.LocalUserViewModel
import com.swan.studyapp.ui.navigation.Destinations
import com.swan.studyapp.ui.screens.ArticleDetailScreen
import com.swan.studyapp.ui.screens.LoginScreen
import com.swan.studyapp.ui.screens.MainFrame
import com.swan.studyapp.ui.screens.VideoDetailScreen
import com.swan.studyapp.viewModel.UserViewModel


/**
 * 导航控制器
 *
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp() {

    val navController = rememberAnimatedNavController()

    CompositionLocalProvider(LocalUserViewModel provides UserViewModel(LocalContext.current)) {

        val userViewModel = LocalUserViewModel.current
        AnimatedNavHost(
            navController = navController,
            startDestination = Destinations.HomeFrame.route
        ) {

            //首页大框架
            composable(
                Destinations.HomeFrame.route,
                // 进入动画
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                },
                // 退出页面的动画
                exitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                },
            ) {
                MainFrame(onNavigateToArticle = {
                    Log.i("===", "onNavigateToArticle")
                    navController.navigate(Destinations.ArticleDetail.route)
                }, onNavigateToVideo = {
                    navController.navigate(Destinations.VideoDetail.route)
                }, onNavigateToStudyHistory = {
                    if (userViewModel.logged) {
                        //已登录
                    } else {
                        //未登录
                        navController.navigate(Destinations.Login.route)
                    }
                })
            }

            //文章详情页
            composable(
                Destinations.ArticleDetail.route,
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                },
            ) {
                ArticleDetailScreen(onBack = {
                    navController.popBackStack()
                })
            }

            //视频详情页
            composable(
                Destinations.VideoDetail.route,
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                },
            ) {
                VideoDetailScreen(onBack = {
                    navController.popBackStack()
                })
            }

            composable(
                Destinations.Login.route,
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                },
            ) {
                LoginScreen {
                    navController.popBackStack()
                }
            }
        }
    }
}

@Preview
@Composable
fun NavHostAppPreview() {
    NavHostApp()
}

