package dev.lantt.wordsfactory.video.presentation

import android.annotation.SuppressLint
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.lantt.wordsfactory.core.Constants.LEARN_ENGLISH_URL
import org.koin.androidx.compose.koinViewModel

@Composable
fun VideoScreen(
    viewModel: VideoViewModel = koinViewModel()
) {
    val lastSavedUrl by viewModel.lastSavedUrl.collectAsStateWithLifecycle()

    AndroidView(
        factory = { context ->
            return@AndroidView WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        val currentUrl = request?.url?.toString()
                        viewModel.saveUrl(currentUrl ?: LEARN_ENGLISH_URL)
                        return super.shouldOverrideUrlLoading(view, request)
                    }
                }

                @SuppressLint("SetJavaScriptEnabled")
                settings.javaScriptEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(false)
            }
        },
        update = { webView ->
            viewModel.fetchLastSavedUrl()
            webView.loadUrl(lastSavedUrl)
        }
    )
}