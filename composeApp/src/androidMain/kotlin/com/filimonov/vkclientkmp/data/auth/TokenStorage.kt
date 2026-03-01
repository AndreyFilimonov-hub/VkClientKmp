package com.filimonov.vkclientkmp.data.auth

import com.vk.id.VKID

actual class TokenStorage {

    actual suspend fun getToken(): String? {
        return VKID.instance.accessToken?.token
    }
}