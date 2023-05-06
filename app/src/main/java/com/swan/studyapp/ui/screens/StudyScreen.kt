package com.swan.studyapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.H
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.HorizontalPager
import com.swan.studyapp.ui.compones.NotificationContent
import com.swan.studyapp.ui.compones.SwiperContent
import com.swan.studyapp.ui.compones.TopAppBar
import com.swan.studyapp.ui.theme.contentColor
import com.swan.studyapp.viewModel.MainViewModel

@Composable
fun StudyScreen(
    vm: MainViewModel = viewModel(),
) {
    Column {
        // 标题栏
        TopAppBar(modifier = Modifier.padding(horizontal = 8.dp)) {
            //搜索按钮
            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .weight(1f),
                color = Color(0x33FFFFFF)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )

                    Text(
                        "搜索感兴趣的资讯或课程",
                        color = Color.White,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            //学习进度
            Text(
                text = "学习\n进度",
                fontSize = 10.sp,
                color = Color.White,
                modifier = Modifier.clickable {
                    //onNavigateToStudyHistory()
                })

            Spacer(modifier = Modifier.width(8.dp))

            Text("26%", fontSize = 12.sp, color = Color.White)

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = Color.White
            )
        }

        // 分类标签
        Category(vm)

        //类型标签
        Types(vm)

        // 轮播图
        SwiperContent(vm)

        //通知公告
        NotificationContent(vm)
    }
}


@Composable
fun Category(vm: MainViewModel){
    TabRow(
        selectedTabIndex = vm.categoryIndex,
        containerColor = Color(0x22149EE7),
        contentColor = contentColor,
    ) {
        vm.categories.forEachIndexed { index, category ->
            Tab(
                selected = vm.categoryIndex == index,
                onClick = {
                    vm.updateCategoryIndex(index)
                },
                selectedContentColor = Color(0xFF149EE7),
                unselectedContentColor = Color(0xFF666666),
            ) {
                Text(
                    text = category.title,
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun Types(vm: MainViewModel){
    TabRow(
        selectedTabIndex = vm.currentTypeIndex,
        containerColor = Color.Transparent,
        contentColor = contentColor,
        indicator = {},
        divider = {}
    ) {
        vm.types.forEachIndexed { index, dataType ->
            LeadingIconTab(
                selected = vm.currentTypeIndex == index, onClick = {
                    vm.updateTypeIndex(index)
                },
                selectedContentColor = Color(0xFF149EE7),
                unselectedContentColor = Color(0xFF666666),
                icon = {
                    Icon(imageVector = dataType.icon, contentDescription = null)
                },
                text = {
                    Text(
                        text = dataType.title,
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontSize = 16.sp
                    )
                }
            )
        }
    }
}

@Preview(name = "StudyScreen")
@Composable
private fun PreviewStudyScreen() {
    StudyScreen()
}