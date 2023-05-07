package com.swan.studyapp.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swan.studyapp.R
import com.swan.studyapp.ui.compones.TopAppBar

@Composable
fun MineScreen() {
    val menus = listOf(
        MenuItem(R.drawable.points, "学习积分"),
        MenuItem(R.drawable.browser_history, "浏览记录"),
        MenuItem(R.drawable.archives, "学习档案"),

        MenuItem(R.drawable.questions, "常见问题"),
        MenuItem(R.drawable.version, "版本信息"),
        MenuItem(R.drawable.settings, "个人设置")
    )

    Column {
        TopAppBar() {
            Text(text = "我的", fontSize = 18.sp, color = Color.White)
        }
        LazyColumn(modifier = Modifier) {
            //头像部分
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 24.dp, horizontal = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.newbanner3),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(62.dp)
                            .clip(CircleShape)
                    )
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .height(62.dp)
                    ) {
                        Text(text = "未登录", color = Color(0xFF333333), fontSize = 18.sp)
                        Text(text = "已坚持学习0天", color = Color(0xFF999999), fontSize = 12.sp)
                    }
                }
            }

            //菜单部分
            itemsIndexed(menus) { index, menu ->
                if (index == 3) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFF5F5F5))
                            .height(16.dp)
                            .fillMaxWidth()
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = menu.icon), contentDescription = null,
                        tint = Color(0xFF149EE7),
                        modifier = Modifier.size(17.dp)
                    )

                    Column(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = menu.title,
                                color = Color(0xFF333333),
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowForwardIos,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.width(13.dp)
                            )
                        }

                        Divider()
                    }

                }
            }
        }
    }
}

data class MenuItem(@DrawableRes val icon: Int, val title: String)

@Preview(name = "MineScreen")
@Composable
private fun PreviewMineScreen() {
    MineScreen()
}