package com.filimonov.vkclientkmp.presentation.ui.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MediumVerticalSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(16.dp))
}

@Composable
fun LargeVerticalSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(32.dp))
}