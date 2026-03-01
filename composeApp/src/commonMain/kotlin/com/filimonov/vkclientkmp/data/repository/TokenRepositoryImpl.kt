package com.filimonov.vkclientkmp.data.repository

import com.filimonov.vkclientkmp.data.auth.TokenStorage
import com.filimonov.vkclientkmp.domain.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class TokenRepositoryImpl(
    private val tokenStorage: TokenStorage
) : TokenRepository {

    override suspend fun getToken(): String? {
        return withContext(Dispatchers.IO) {
            tokenStorage.getToken()
        }
    }

    override suspend fun isAuthorized(): Boolean {
        return withContext(Dispatchers.IO) {
            tokenStorage.getToken() != null
        }
    }
}