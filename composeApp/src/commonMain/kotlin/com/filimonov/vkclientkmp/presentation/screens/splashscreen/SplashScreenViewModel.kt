package com.filimonov.vkclientkmp.presentation.screens.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filimonov.vkclientkmp.di.AppComponentTemp
import com.filimonov.vkclientkmp.domain.usecase.GetAuthStatusUseCase
import com.filimonov.vkclientkmp.domain.usecase.GetOnboardingStatesUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val getOnboardingStatesUseCase = GetOnboardingStatesUseCase(AppComponentTemp.onboardingRepository)
    private val getAuthStatusUseCase = GetAuthStatusUseCase(AppComponentTemp.tokenRepository)

    private val _startDestination = MutableSharedFlow<StartDestination>()
    val startDestination = _startDestination.asSharedFlow()

    init {
        viewModelScope.launch {
            try {
                val onboardingDeferred = async {
                    getOnboardingStatesUseCase()
                }
                val authDeferred = async {
                    getAuthStatusUseCase()
                }

                val isOnboardingCompleted = onboardingDeferred.await()
                val isAuthorized = authDeferred.await()

                when {
                    isAuthorized -> _startDestination.emit(StartDestination.MainScreen)

                    !isOnboardingCompleted -> _startDestination.emit(StartDestination.OnboardingScreen)

                    else -> _startDestination.emit(StartDestination.LoginScreen)
                }
            } catch (e: Exception) {
                if (e is CancellationException) {
                    throw e
                }
                _startDestination.emit(StartDestination.LoginScreen)
            }
        }
    }
}