package com.filimonov.vkclientkmp.data.auth

expect class TokenStorage {

    suspend fun getToken(): String?
}