package com.filimonov.vkclientkmp.presentation.screens.splashscreen

import com.filimonov.vkclientkmp.presentation.navigation.Screen

sealed class StartDestination(val screen: Screen) {

    data object OnboardingScreen : StartDestination(Screen.OnboardingScreen)

    data object LoginScreen : StartDestination(Screen.LoginScreen)

    data object MainScreen : StartDestination(Screen.MainScreen)
}