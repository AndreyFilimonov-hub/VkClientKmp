package com.filimonov.vkclientkmp.domain.repository

interface OnboardingRepository {

    suspend fun isOnboardingCompleted(): Boolean

    suspend fun setOnboardingCompleted()
}