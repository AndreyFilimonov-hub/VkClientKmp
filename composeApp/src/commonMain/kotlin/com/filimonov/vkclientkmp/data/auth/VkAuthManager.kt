package com.filimonov.vkclientkmp.data.auth

expect class VkAuthManager {
    suspend fun authorize(): String
}