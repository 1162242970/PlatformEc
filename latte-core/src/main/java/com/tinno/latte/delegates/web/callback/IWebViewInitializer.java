package com.tinno.latte.delegates.web.callback;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by android on 17-12-21.
 *
 * WebView初始化的接口,初始化WebView,WebClient,WebChromeClient
 * WebDelegate设置,最后在WebDelegateImpl类中实现
 *
 */

public interface IWebViewInitializer {

    WebView initWebView(WebView webView);

    //帮助WebView处理各种通知、请求事件
    WebViewClient initWebClient();

    //主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    WebChromeClient initWebChromeClient();
}
