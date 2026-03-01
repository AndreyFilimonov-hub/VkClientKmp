package com.filimonov.vkclientkmp.presentation.screens.loginscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filimonov.vkclientkmp.di.AppComponentTemp
import com.filimonov.vkclientkmp.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val loginUseCase = LoginUseCase(AppComponentTemp.loginRepository)

    private val _state = MutableStateFlow(LoginUiState(isError = false))
    val state = _state.asStateFlow()

    private val _loginEvent = MutableSharedFlow<LoginUiEvent>()
    val loginEvent = _loginEvent.asSharedFlow()

    fun processCommand(command: LoginCommand) {
        viewModelScope.launch {
            when (command) {
                LoginCommand.Login -> {
                    loginUseCase()
                        .onSuccess {
                            _loginEvent.emit(LoginUiEvent.LoginSuccessEvent)
                        }
                        .onFailure {
                            _state.value = _state.value.copy(isError = true)
                        }
                }
            }
        }
    }
}