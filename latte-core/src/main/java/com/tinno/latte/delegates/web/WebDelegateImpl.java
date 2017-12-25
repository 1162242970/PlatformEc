package com.tinno.latte.delegates.web;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tinno.latte.delegates.web.callback.IPageLoadListener;
import com.tinno.latte.delegates.web.callback.IWebViewInitializer;
import com.tinno.latte.delegates.web.chormeclient.WebChromeClientImpl;
import com.tinno.latte.delegates.web.client.WebViewClientImpl;
import com.tinno.latte.delegates.web.route.RouteKeys;
import com.tinno.latte.delegates.web.route.Router;

/**
 * Created by android on 17-12-21.
 */

public class WebDelegateImpl extends WebDelegate{

    private IPageLoadListener mIPageLoadListener = null;

    /**
     * 创建delegate的实例,把url传递给父类处理
     *
     */
    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    /**
     * 设置界面为一个WebView
     */
    @Override
    public Object setLayout() {
        return getWebView();
    }

    /**
     * 页面加载
     */
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) {
            //用原生的方式模拟Web跳转并且进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageLoadListener(new IPageLoadListener() {
            @Override
            public void onLoadStart() {

            }

            @Override
            public void onLoadEnd() {

            }
        });
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
