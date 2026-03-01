package com.filimonov.vkclientkmp.di

import com.filimonov.vkclientkmp.data.auth.TokenStorage
import com.filimonov.vkclientkmp.data.auth.VkAuthManager
import com.filimonov.vkclientkmp.data.repository.LoginRepositoryImpl
import com.filimonov.vkclientkmp.data.repository.TokenRepositoryImpl
import com.filimonov.vkclientkmp.domain.repository.LoginRepository
import com.filimonov.vkclientkmp.domain.repository.OnboardingRepository
import com.filimonov.vkclientkmp.domain.repository.TokenRepository

object AppComponentTemp {

    private lateinit var vkAuthManager: VkAuthManager
    private lateinit var tokenStorage: TokenStorage

    lateinit var loginRepository: LoginRepository
    lateinit var tokenRepository: TokenRepository
    lateinit var onboardingRepository: OnboardingRepository

    fun init(
        vkAuthManager: VkAuthManager,
        tokenStorage: TokenStorage,
        onboardingRepository: OnboardingRepository
    ) {
        AppComponentTemp.vkAuthManager = vkAuthManager
        AppComponentTemp.tokenStorage = tokenStorage

        loginRepository = LoginRepositoryImpl(vkAuthManager)
        tokenRepository = TokenRepositoryImpl(AppComponentTemp.tokenStorage)
        this.onboardingRepository = onboardingRepository
    }
}