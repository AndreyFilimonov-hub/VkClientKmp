package com.filimonov.vkclientkmp.data.repository

import com.filimonov.vkclientkmp.domain.repository.OnboardingRepository
import platform.Foundation.NSUserDefaults

class OnboardingRepositoryImpl(private val usersDefaults: NSUserDefaults = NSUserDefaults.standardUserDefaults) :
    OnboardingRepository {

    companion object {

        private const val KEY_COMPLETED = "onboarding_completed"
    }

    override suspend fun isOnboardingCompleted(): Boolean {
        return usersDefaults.boolForKey(KEY_COMPLETED)
    }

    override suspend fun setOnboardingCompleted() {
        usersDefaults.setBool(true, KEY_COMPLETED)
    }
}