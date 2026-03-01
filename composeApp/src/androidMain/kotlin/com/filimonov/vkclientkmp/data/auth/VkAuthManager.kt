package com.filimonov.vkclientkmp.data.auth

import com.vk.id.AccessToken
import com.vk.id.VKID
import com.vk.id.VKIDAuthFail
import com.vk.id.auth.VKIDAuthCallback
import com.vk.id.auth.VKIDAuthParams
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.coroutineScope

actual class VkAuthManager {

    actual suspend fun authorize(): String = coroutineScope {
        val deferred = CompletableDeferred<String>()

        VKID.instance.authorize(
            callback = object : VKIDAuthCallback {
                override fun onAuth(accessToken: AccessToken) {
                    deferred.complete(accessToken.token)
                }

                override fun onFail(fail: VKIDAuthFail) {
                    deferred.completeExceptionally(Exception(fail.description))
                }
            },
            params = VKIDAuthParams {
                scopes = setOf("friends", "wall", "groups")
            })

        deferred.await()
    }
}