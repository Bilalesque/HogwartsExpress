package com.example.bilalsalman.hogwartsexpress;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webviewclass extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}