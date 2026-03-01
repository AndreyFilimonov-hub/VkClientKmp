package com.filimonov.vkclientkmp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.filimonov.vkclientkmp.di.AppComponentTemp
import com.filimonov.vkclientkmp.presentation.ui.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        AppComponentTemp.init(VkAuthManager(), TokenStorage(), OnboardingRepositoryImpl(this@MainActivity))
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}