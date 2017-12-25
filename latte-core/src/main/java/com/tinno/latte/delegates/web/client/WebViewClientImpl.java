package com.tinno.latte.delegates.web.client;

import android.graphics.Bitmap;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tinno.latte.app.ConfigKeys;
import com.tinno.latte.app.Latte;
import com.tinno.latte.delegates.web.WebDelegate;
import com.tinno.latte.delegates.web.callback.IPageLoadListener;
import com.tinno.latte.delegates.web.route.Router;
import com.tinno.latte.ui.loader.LatteLoader;
import com.tinno.latte.util.log.LatteLogger;
import com.tinno.latte.util.storage.LattePreference;

/**
 * Created by android on 17-12-21.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        DELEGATE = delegate;
    }

    /**
     * 返回false,WebView内部处理
     * 返回true,自己处理
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        //进入Web的时候调用进度条
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        //完成的时候关闭进度条
        LatteLoader.stopLoading();

    }

    /**
     * 获取浏览器Cookie
     */
    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        //注意,这里的Cookie和API请求的Cookie是不一样的,这个在网页不可见
        final String webHost = Latte.getConfiguration(ConfigKeys.WEB_HOST);
        if (webHost != null) {
            final String cookieStr = manager.getCookie(webHost);
            if (manager.hasCookies()) {
                if (cookieStr != null && !cookieStr.equals("")) {
                    LattePreference.addCustomAppProfile("cookie", cookieStr);
                }
            }
        }
    }
}
