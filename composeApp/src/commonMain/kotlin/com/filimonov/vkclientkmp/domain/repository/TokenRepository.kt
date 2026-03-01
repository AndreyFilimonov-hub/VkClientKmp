package com.filimonov.vkclientkmp.domain.repository

interface TokenRepository {

    suspend fun getToken(): String?

    suspend fun isAuthorized(): Boolean
}