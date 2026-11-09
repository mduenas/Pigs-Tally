package com.markduenas.pigstally2.platform

import androidx.compose.runtime.Composable

/**
 * Installed application version, read at runtime.
 * Android uses PackageManager versionName and versionCode.
 * iOS uses CFBundleShortVersionString and CFBundleVersion.
 */
data class AppVersion(
    val versionName: String,
    val versionCode: String,
) {
    val label: String
        get() = formatAppVersionLabel(versionName, versionCode)
}

fun formatAppVersionLabel(versionName: String, versionCode: String): String {
    return "Version $versionName ($versionCode)"
}

@Composable
expect fun rememberAppVersion(): AppVersion
