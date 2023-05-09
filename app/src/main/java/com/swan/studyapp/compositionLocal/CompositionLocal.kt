package com.swan.studyapp.compositionLocal

import androidx.compose.runtime.compositionLocalOf
import com.swan.studyapp.viewModel.UserViewModel
/**
 * 全局共享 UserViewModel
 */
val LocalUserViewModel =
    compositionLocalOf<UserViewModel> { error("User View Model Context Not Found") }