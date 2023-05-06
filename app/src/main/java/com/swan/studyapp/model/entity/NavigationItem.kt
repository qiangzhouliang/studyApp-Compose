package com.swan.studyapp.model.entity

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 导航栏对象
 * @property title String 导航栏标题
 * @property icon ImageVector 导航栏图标
 * @constructor
 */
data class NavigationItem(
    val title: String,
    val icon: ImageVector
)
