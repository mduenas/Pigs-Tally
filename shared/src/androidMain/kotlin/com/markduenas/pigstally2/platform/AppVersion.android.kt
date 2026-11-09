package com.markduenas.pigstally2.platform

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun rememberAppVersion(): AppVersion {
    val context = LocalContext.current
    return remember(context.packageName) {
        readInstalledAppVersion(context.packageManager, context.packageName)
    }
}

internal fun readInstalledAppVersion(
    packageManager: PackageManager,
    packageName: String,
): AppVersion {
    return try {
        val info = packageManager.ownPackageInfo(packageName)
        val versionName = info.versionName?.takeIf { it.isNotBlank() } ?: "unknown"
        AppVersion(
            versionName = versionName,
            versionCode = info.versionCodeCompat().toString(),
        )
    } catch (_: PackageManager.NameNotFoundException) {
        AppVersion(versionName = "unknown", versionCode = "0")
    }
}

private fun PackageManager.ownPackageInfo(packageName: String): PackageInfo {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
    } else {
        @Suppress("DEPRECATION")
        getPackageInfo(packageName, 0)
    }
}

private fun PackageInfo.versionCodeCompat(): Long {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        longVersionCode
    } else {
        @Suppress("DEPRECATION")
        versionCode.toLong()
    }
}
