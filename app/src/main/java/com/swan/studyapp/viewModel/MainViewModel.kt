package com.swan.studyapp.viewModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.swan.studyapp.model.entity.Category
import com.swan.studyapp.model.entity.DataType
import com.swan.studyapp.model.entity.SwiperEntity

class MainViewModel : ViewModel() {

    //private val homeService = HomeService.instance()

    //分类数据是否加载成功
    var categoryLoaded by mutableStateOf(false)
        private set

    //分类数据
    var categories by mutableStateOf(
        listOf(
            Category("思想政治1", "1"),
            Category("法律法规2", "2"),
            Category("职业道德3", "3"),
            Category("诚信自律4", "4")
        )
    )
        private set

    //suspend fun categoryData() {
    //    val categoryRes = homeService.category()
    //    if (categoryRes.code == 0) {
    //        categories = categoryRes.data
    //        categoryLoaded = true
    //    } else {
    //        //不成功的情况下，读取 message
    //        val message = categoryRes.message
    //    }
    //}

    //当前分类下标
    var categoryIndex by mutableStateOf(0)
        private set

    /**
     * 更新分类下标
     *
     * @param index
     */
    fun updateCategoryIndex(index: Int) {
        categoryIndex = index
    }


    //类型数据
    val types by mutableStateOf(
        listOf(
            DataType("相关资讯", Icons.Default.Description),
            DataType("视频课程", Icons.Default.SmartDisplay)
        )
    )

    //当前类型下标
    var currentTypeIndex by mutableStateOf(0)
        private set

    //是否文章列表
    var showArticleList by mutableStateOf(true)
        private set

    /**
     * 更新类型下标
     *
     * @param index
     */
    fun updateTypeIndex(index: Int) {
        currentTypeIndex = index
        showArticleList = currentTypeIndex == 0
    }

    //轮播图数据
    var swiperData by mutableStateOf(
        listOf(
            SwiperEntity("https://img0.baidu.com/it/u=501558154,2179992071&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1683478800&t=5ba6804ac99a636236f9010851a34f7a"),
            SwiperEntity("https://img2.baidu.com/it/u=3392420875,1412947139&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1683478800&t=31af96b501aec561ebc5c13c1f8e60c6"),
            SwiperEntity("https://img1.baidu.com/it/u=3905587923,1551975743&fm=253&app=120&size=w931&n=0&f=JPEG&fmt=auto?sec=1683478800&t=b9cab01d61f66b18ad959f5978069d1c"),
            SwiperEntity("https://t7.baidu.com/it/u=2420942434,2938556987&fm=218&app=126&size=f242,150&n=0&f=JPEG&fmt=auto?s=329630C8471309C832D0480D030070D3&sec=1683478800&t=a18002b8b784c6afd92afd4fe9562a04"),
            SwiperEntity("https://t8.baidu.com/it/u=551153044,1906485926&fm=218&app=126&size=f242,150&n=0&f=JPEG&fmt=auto?s=F428BE57A8B25394BE30C1280300306F&sec=1683478800&t=03cd9d3a532664a42e8b0750a4f002b2"),
        )
    )
        private set

    var swiperLoaded by mutableStateOf(false)
        private set

    //suspend fun swiperData() {
    //    val swiperRes = homeService.banner()
    //    if (swiperRes.code == 0 && swiperRes.data != null) {
    //        swiperData = swiperRes.data
    //        swiperLoaded = true
    //    } else {
    //        val message = swiperRes.message
    //    }
    //}

    //通知数据
    val notifications =
        listOf("人社部向疫情防控期", "湖北黄冈新冠肺炎患者治愈病例破千连续5治愈病例破千连续5", "安徽单日新增确诊病例首次降至个位数累计")


}