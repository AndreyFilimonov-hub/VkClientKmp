package com.filimonov.vkclientkmp.presentation.screens.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.filimonov.vkclientkmp.domain.entity.Post
import com.filimonov.vkclientkmp.presentation.ui.theme.AppTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel { MainViewModel() }
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        val state by viewModel.state.collectAsStateWithLifecycle()

        LazyColumn(
            modifier = Modifier.padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = innerPadding
        ) {
            items(
                items = state.posts,
                key = { it.id }
                ) {
                Post(post = it)
            }
        }
    }
}

@Composable
private fun Post(
    modifier: Modifier = Modifier,
    post: Post
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = post.author
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = post.createdAt,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        )
        AsyncImage(
            model = post.contentUrl,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    AppTheme {
        MainScreen()
    }
}