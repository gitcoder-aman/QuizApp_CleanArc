package com.tech.quiz_app_mvvm.presentation

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun PrivacyScreen() {
    WebViewComponent(url = "file:///android_asset/privacy.html")
}

@Composable
fun WebViewComponent(url: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true // Enable JavaScript if needed
            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })
}