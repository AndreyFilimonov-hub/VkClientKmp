package com.filimonov.vkclientkmp.domain.usecase

import com.filimonov.vkclientkmp.domain.repository.TokenRepository

class GetAuthStatusUseCase(private val repository: TokenRepository) {

    suspend operator fun invoke(): Boolean {
        return repository.isAuthorized()
    }
}