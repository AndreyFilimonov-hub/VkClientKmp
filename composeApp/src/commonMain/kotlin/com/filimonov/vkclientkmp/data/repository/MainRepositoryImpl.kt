package com.filimonov.vkclientkmp.data.repository

import com.filimonov.vkclientkmp.domain.entity.Post
import com.filimonov.vkclientkmp.domain.repository.MainRepository

class MainRepositoryImpl : MainRepository {

    private val posts = mutableListOf<Post>()

    init {
        repeat(50) {
            posts.add(
                Post(
                    id = it,
                    author = "Author $it",
                    createdAt = "01.01.1970",
                    contentUrl = "https://avatars.mds.yandex.net/i?id=7bd9e586e29521ebc3bdeb71cee9ae10_l-5672852-images-thumbs&n=13"
                )
            )
        }
    }

    override suspend fun getPosts(): List<Post> {
        return posts
    }
}