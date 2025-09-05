package com.pirataram.marveldemo.data.remote.marvel

import com.pirataram.marveldemo.BuildConfig
import java.security.MessageDigest

object MarvelAuth {

    fun authParams(): Map<String, String> {
        val ts = System.currentTimeMillis().toString()
        val hash = md5("$ts${BuildConfig.MARVEL_PRIVATE_API_KEY}${BuildConfig.MARVEL_PUBLIC_API_KEY}")
        return mapOf(
            "ts" to ts,
            "apikey" to BuildConfig.MARVEL_PUBLIC_API_KEY,
            "hash" to hash
        )
    }

    private fun md5(input: String): String {
        val bytes = MessageDigest.getInstance("MD5")
            .digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}