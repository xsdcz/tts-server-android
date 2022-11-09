package com.github.jing332.tts_server_android

import android.app.Application
import android.content.Context
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlin.properties.Delegates

class App : Application() {
    companion object {
        const val TAG = "App"
        var instance: App by Delegates.notNull()
        val context: Context by lazy { instance.applicationContext }
        val localBroadcast by lazy { LocalBroadcastManager.getInstance(context) }

        @OptIn(ExperimentalSerializationApi::class)
        val jsonBuilder by lazy {
            Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                explicitNulls = false //忽略为null的字段
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        CrashHandler(this)
    }
}