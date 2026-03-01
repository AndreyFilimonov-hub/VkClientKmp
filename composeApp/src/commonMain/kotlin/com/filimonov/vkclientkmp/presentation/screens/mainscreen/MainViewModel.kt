package com.filimonov.vkclientkmp.presentation.screens.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filimonov.vkclientkmp.data.repository.MainRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow(MainUiState(listOf()))
    val state = _state.asStateFlow()

    private val repository = MainRepositoryImpl()

    init {
        viewModelScope.launch {
            val posts = repository.getPosts()
            _state.value = MainUiState(posts)
        }
    }
}