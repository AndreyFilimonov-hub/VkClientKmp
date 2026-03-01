package com.filimonov.vkclientkmp.data.repository

import com.filimonov.vkclientkmp.data.auth.VkAuthManager
import com.filimonov.vkclientkmp.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val vkAuthManager: VkAuthManager
) : LoginRepository {

    override suspend fun login(): Result<Unit> {
        return try {
            vkAuthManager.authorize()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}