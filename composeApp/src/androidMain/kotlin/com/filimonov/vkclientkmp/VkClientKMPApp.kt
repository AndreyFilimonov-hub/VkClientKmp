package com.filimonov.vkclientkmp

import android.app.Application
import com.vk.id.VKID

class VkClientKMPApp : Application() {

    override fun onCreate() {
        super.onCreate()
        VKID.init(this)
    }
}