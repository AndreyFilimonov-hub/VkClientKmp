package com.filimonov.vkclientkmp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.filimonov.vkclientkmp.data.auth.TokenStorage
import com.filimonov.vkclientkmp.data.auth.VkAuthManager
import com.filimonov.vkclientkmp.data.repository.OnboardingRepositoryImpl
import com.filimonov.vkclientkmp.di.AppComponentTemp
import com.filimonov.vkclientkmp.presentation.ui.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        AppComponentTemp.init(VkAuthManager(), TokenStorage(), OnboardingRepositoryImpl(this@MainActivity))
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}