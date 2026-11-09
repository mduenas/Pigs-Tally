package com.markduenas.pigstally2.platform

import kotlin.test.Test
import kotlin.test.assertEquals

class AppVersionTest {

    @Test
    fun labelUsesVersionNameAndVersionCode() {
        assertEquals("Version 1.0.2 (5)", formatAppVersionLabel("1.0.2", "5"))
        assertEquals("Version 1.0.2 (5)", AppVersion("1.0.2", "5").label)
    }

    @Test
    fun labelPreservesPlatformVersionStrings() {
        assertEquals("Version 1.1 (1)", AppVersion(versionName = "1.1", versionCode = "1").label)
    }
}
