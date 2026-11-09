package com.markduenas.pigstally2.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.Foundation.NSBundle

@Composable
actual fun rememberAppVersion(): AppVersion {
    return remember {
        readBundleAppVersion()
    }
}

internal fun readBundleAppVersion(): AppVersion {
    val bundle = NSBundle.mainBundle
    val versionName = bundle.infoString("CFBundleShortVersionString").ifBlank { "unknown" }
    val versionCode = bundle.infoString("CFBundleVersion").ifBlank { "0" }
    return AppVersion(versionName = versionName, versionCode = versionCode)
}

private fun NSBundle.infoString(key: String): String {
    // NSString bridges to Kotlin as Any; description/toString is the plist value.
    return objectForInfoDictionaryKey(key)?.toString().orEmpty()
}
