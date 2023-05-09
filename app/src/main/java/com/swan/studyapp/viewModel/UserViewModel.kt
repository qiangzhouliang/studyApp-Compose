package com.swan.studyapp.viewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swan.studyapp.model.entity.UserInfoEntity
import com.swan.studyapp.model.service.UserInfoManager
import com.swan.studyapp.model.service.UserService
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.security.MessageDigest

class UserViewModel(context: Context) : ViewModel() {

    private val userInfoManager = UserInfoManager(context)
    private val userService = UserService.instance()

    var userName by mutableStateOf("")

    var password by mutableStateOf("")

    var userInfo: UserInfoEntity? = null
        private set

    init {
        //其实这里可以使用 DataStore 的对象存储，直接存储整个对象。
        viewModelScope.launch {
            val userName = userInfoManager.userName.firstOrNull()
            userInfo = if (userName?.isNotEmpty() == true) {
                UserInfoEntity(userName)
            } else {
                null
            }
        }
    }

    //是否已登录
    val logged: Boolean
        get() {
            return userInfo != null
        }

    //是否正在登录
    var loading by mutableStateOf(false)
        private set

    var error by mutableStateOf("")
        private set

    /**
     * 登录操作
     *
     */
    suspend fun login(onClose: () -> Unit) {
        error = ""
        loading = true
        userName = "18600000000"
        password = "123456"
        // userName 18600000000
        // password e10adc3949ba59abbe56e057f20f883e 或 123456
        val res = userService.signIn(userName, md5(password))
        //val res = userService.signIn("18600000000", "e10adc3949ba59abbe56e057f20f883e")
        if (res.code == 0 && res.data != null) {
            userInfo = res.data
            userInfoManager.save(userInfo?.userName ?: "")
            onClose()
        } else {
            //失败
            error = res.message
        }
        loading = false
    }

    fun md5(content: String): String {
        val hash = MessageDigest.getInstance("MD5").digest(content.toByteArray())
        val hex = StringBuilder(hash.size * 2)
        for (b in hash) {
            var str = Integer.toHexString(b.toInt())
            if (b < 0x10) {
                str = "0$str"
            }
            hex.append(str.substring(str.length - 2))
        }
        return hex.toString()
    }


    fun clear() {
        viewModelScope.launch {
            userInfoManager.clear() //清除本地数据存储
            userInfo = null //置空内存数据
        }
    }
}