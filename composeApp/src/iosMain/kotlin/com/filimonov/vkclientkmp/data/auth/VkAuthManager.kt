package com.filimonov.vkclientkmp.data.auth

import kotlinx.coroutines.suspendCancellableCoroutine
import platform.AuthenticationServices.ASPresentationAnchor
import platform.AuthenticationServices.ASWebAuthenticationPresentationContextProvidingProtocol
import platform.AuthenticationServices.ASWebAuthenticationSession
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.darwin.NSObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

actual class VkAuthManager {

    actual suspend fun authorize(): String {
        return suspendCancellableCoroutine { cont ->

            val authUrl = NSURL.URLWithString(
                "https://oauth.vk.com/authorize?client_id=54464632&redirect_uri=vk54464632://callback&response_type=token&scope=email"
            )!!

            val session = ASWebAuthenticationSession(
                uRL = authUrl,
                callbackURLScheme = "vk54464632"
            ) { callbackUrl, error ->

                if (error != null) {
                    cont.resumeWithException(Exception(error.localizedDescription))
                    return@ASWebAuthenticationSession
                }

                if (callbackUrl != null) {
                    val token = extractAccessToken(callbackUrl)
                    cont.resume(token)
                }
            }

            session.presentationContextProvider =
                object : NSObject(), ASWebAuthenticationPresentationContextProvidingProtocol {
                    override fun presentationAnchorForWebAuthenticationSession(session: ASWebAuthenticationSession): ASPresentationAnchor {
                        return UIApplication.sharedApplication.keyWindow!!
                    }
                }

            session.start()
        }
    }

    private fun extractAccessToken(url: NSURL): String {
        val fragment = url.fragment ?: ""

        val params = fragment.split("&")
        val tokenParam = params.first { it.startsWith("access_token=") }

        return tokenParam.substringAfter("=")
    }
}