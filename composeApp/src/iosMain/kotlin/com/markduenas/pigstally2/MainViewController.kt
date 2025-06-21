package com.markduenas.pigstally2

import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIViewController

@OptIn(ExperimentalForeignApi::class)
fun MainViewController(): UIViewController = ComposeUIViewController { 
    App() 
}