package com.filimonov.vkclientkmp.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.filimonov.vkclientkmp.presentation.navigation.NavGraph
import com.filimonov.vkclientkmp.presentation.ui.theme.AppTheme

@Composable
@Preview
fun App() {
    AppTheme {
        NavGraph()
    }
}