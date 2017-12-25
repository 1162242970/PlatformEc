package com.tinno.latte.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.tinno.latte.app.ConfigKeys;
import com.tinno.latte.app.Latte;
import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latte.delegates.web.callback.IWebViewInitializer;
import com.tinno.latte.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by android on 17-12-21.
 */

public abstract class WebDelegate extends LatteDelegate implements IWebViewInitializer {

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAvailable = false;
    private LatteDelegate mTopDelegate = null;

    public WebDelegate() {

    }

    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取传入的url,传值给mUrl
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }

    /**
     * 初始化WebView
     */
    @SuppressLint("AddJavascriptInterface")
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                //将WebView设置为弱引用,避免内存溢出
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                //配置WebView的各种属性
                mWebView = initializer.initWebView(mWebView);
                //配置WebClient
                mWebView.setWebViewClient(initializer.initWebClient());
                //配置WebChromeClient
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                //设置绑定到Javascript的类实例和JavaScript中的实例的名称
                final String name = Latte.getConfiguration(ConfigKeys.JAVASCRIPT_INTERFACE);
                mWebView.addJavascriptInterface(LatteWebInterface.create(this), name);
                //设置完成以后,mIsWebViewAvailable设为true
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("Initializer is null");
            }
        }
    }

    /**
     * 设置父类的delegate
     */
    public void setTopDelegate(LatteDelegate delegate) {
        mTopDelegate = delegate;
    }

    /**
     * 获取父类Delegate的实例
     */
    public LatteDelegate getTopDelegate() {
        if (mTopDelegate == null) {
            mTopDelegate = this;
        }
        return mTopDelegate;
    }

    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WebView is null");
        }
        return mIsWebViewAvailable ? mWebView : null;
    }

    /**
     * 获取url
     */
    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("URL is null");
        }
        return mIsWebViewAvailable ? mUrl : null;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
