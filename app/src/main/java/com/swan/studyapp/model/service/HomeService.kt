package com.swan.studyapp.model.service

import com.swan.studyapp.model.Network
import com.swan.studyapp.model.entity.CategoryResponse
import com.swan.studyapp.model.entity.SwiperResponse
import retrofit2.http.GET

interface HomeService {

    // 分类
    @GET("category/list")
    suspend fun category(): CategoryResponse

    // 轮播图
    @GET("recommand/banner")
    suspend fun banner(): SwiperResponse

    companion object {
        fun instance(): HomeService {
            return Network.createService(HomeService::class.java)
        }
    }


}