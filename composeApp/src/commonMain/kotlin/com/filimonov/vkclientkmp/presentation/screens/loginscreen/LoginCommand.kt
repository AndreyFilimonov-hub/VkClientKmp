package com.filimonov.vkclientkmp.presentation.screens.loginscreen

sealed interface LoginCommand {

    data object Login : LoginCommand
}