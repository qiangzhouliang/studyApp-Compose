package com.swan.studyapp.model.service

import com.swan.studyapp.model.Network
import com.swan.studyapp.model.entity.UserInfoResponse
import retrofit2.http.*

interface UserService {
    @FormUrlEncoded
    @POST("user/signIn")
    suspend fun signIn(
        @Field("userName") useName: String,
        @Field("password") password: String,
    ): UserInfoResponse

    companion object {
        fun instance(): UserService {
            return Network.createService(UserService::class.java)
        }
    }

}