package com.filimonov.vkclientkmp.domain.usecase

import com.filimonov.vkclientkmp.domain.repository.OnboardingRepository

class SetOnboardingCompletedUseCase (private val repository: OnboardingRepository) {

    suspend operator fun invoke() = repository.setOnboardingCompleted()
}