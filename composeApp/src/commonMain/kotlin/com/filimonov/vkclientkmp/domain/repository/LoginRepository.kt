package com.filimonov.vkclientkmp.domain.repository

interface LoginRepository {

    suspend fun login(): Result<Unit>
}