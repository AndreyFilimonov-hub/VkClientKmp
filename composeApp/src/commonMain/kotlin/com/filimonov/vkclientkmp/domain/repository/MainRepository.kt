package com.filimonov.vkclientkmp.domain.repository

import com.filimonov.vkclientkmp.domain.entity.Post

interface MainRepository {

    suspend fun getPosts(): List<Post>
}