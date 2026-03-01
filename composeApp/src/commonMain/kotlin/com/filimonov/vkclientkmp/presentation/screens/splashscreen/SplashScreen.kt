package com.filimonov.vkclientkmp.presentation.screens.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.filimonov.vkclientkmp.presentation.navigation.Screen
import org.jetbrains.compose.resources.painterResource
import vkclientkmp.composeapp.generated.resources.Res
import vkclientkmp.composeapp.generated.resources.ic_launcher_foreground

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashScreenViewModel = viewModel { SplashScreenViewModel() },
    onNavigate: (Screen) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.startDestination.collect { startDestination ->
            onNavigate(startDestination.screen)
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(290.dp),
            painter = painterResource(Res.drawable.ic_launcher_foreground),
            contentDescription = null
        )
    }
}

@Composable
@Preview
private fun SplashScreenPreview() {
    SplashScreen(
        onNavigate = {}
    )
}