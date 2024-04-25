package dev.lantt.wordsfactory.video.presentation

import android.annotation.SuppressLint
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import dev.lantt.wordsfactory.core.Constants.LEARN_ENGLISH_URL

@Composable
fun VideoScreen() {
    AndroidView(
        factory = { context ->
            return@AndroidView WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        return !request?.url.toString().startsWith(LEARN_ENGLISH_URL)
                    }
                }

                loadUrl(LEARN_ENGLISH_URL)

                @SuppressLint("SetJavaScriptEnabled")
                settings.javaScriptEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(false)
            }
        }
    )
}