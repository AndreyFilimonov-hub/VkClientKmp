package com.filimonov.vkclientkmp.presentation.screens.onboardingscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.filimonov.vkclientkmp.presentation.ui.theme.AppTheme
import com.filimonov.vkclientkmp.presentation.ui.utils.MediumVerticalSpacer
import vkclientkmp.composeapp.generated.resources.Res
import vkclientkmp.composeapp.generated.resources.onboard_friends
import vkclientkmp.composeapp.generated.resources.onboard_profile
import vkclientkmp.composeapp.generated.resources.onboard_vk
import vkclientkmp.composeapp.generated.resources.onboarding_friends_description
import vkclientkmp.composeapp.generated.resources.onboarding_friends_title
import vkclientkmp.composeapp.generated.resources.onboarding_profile_description
import vkclientkmp.composeapp.generated.resources.onboarding_profile_title
import vkclientkmp.composeapp.generated.resources.onboarding_vk_description
import vkclientkmp.composeapp.generated.resources.onboarding_vk_title
import vkclientkmp.composeapp.generated.resources.start
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = viewModel { OnboardingViewModel() },
    onFinish: () -> Unit
) {
    val pages = listOf(
        OnboardingPage(
            Res.drawable.onboard_profile,
            stringResource(Res.string.onboarding_profile_title),
            stringResource(Res.string.onboarding_profile_description)
        ),
        OnboardingPage(
            Res.drawable.onboard_friends,
            stringResource(Res.string.onboarding_friends_title),
            stringResource(Res.string.onboarding_friends_description)
        ),
        OnboardingPage(
            Res.drawable.onboard_vk,
            stringResource(Res.string.onboarding_vk_title),
            stringResource(Res.string.onboarding_vk_description)
        )
    )
    val pagerState = rememberPagerState { pages.size }

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { position ->
                OnboardingPageContent(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    page = pages[position]
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                StartButton(
                    pagerState = pagerState,
                    onClick = {
                        viewModel.setOnboardingCompleted()
                        onFinish()
                    }
                )
            }
            MediumVerticalSpacer()
            AnimatedIndicators(
                total = pages.size,
                selectedIndex = pagerState.currentPage
            )
            MediumVerticalSpacer()
        }
    }
}

@Composable
private fun AnimatedIndicators(
    modifier: Modifier = Modifier,
    total: Int,
    selectedIndex: Int
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(total) { index ->
            val isSelected = selectedIndex == index

            val width by animateDpAsState(
                targetValue = if (isSelected) 24.dp else 8.dp
            )

            val color by animateColorAsState(
                targetValue = if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                }
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .heightIn(8.dp)
                    .width(width)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

@Composable
private fun OnboardingPageContent(
    modifier: Modifier = Modifier,
    page: OnboardingPage
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(page.imageRes),
            contentDescription = null
        )
        MediumVerticalSpacer()
        Text(
            text = page.title,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        MediumVerticalSpacer()
        Text(
            text = page.description,
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun StartButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Button(
                modifier = modifier.width(260.dp)
                    .heightIn(min = 48.dp),
                onClick = onClick
            ) {
                Text(
                    text = stringResource(Res.string.start),
                    fontSize = 19.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    AppTheme {
        OnboardingScreen(
            onFinish = {}
        )
    }
}