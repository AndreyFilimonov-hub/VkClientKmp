package com.filimonov.vkclientkmp.presentation.screens.onboardingscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filimonov.vkclientkmp.di.AppComponentTemp
import com.filimonov.vkclientkmp.domain.usecase.SetOnboardingCompletedUseCase
import kotlinx.coroutines.launch

class OnboardingViewModel : ViewModel() {

    private val setOnboardingCompletedUseCase = SetOnboardingCompletedUseCase(AppComponentTemp.onboardingRepository)

    fun setOnboardingCompleted() {
        viewModelScope.launch {
            setOnboardingCompletedUseCase()
        }
    }
}