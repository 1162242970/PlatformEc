package com.tinno.platformec.example.event;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebView;
import android.widget.Toast;

import com.tinno.latte.delegates.web.event.Event;

/**
 * Created by android on 17-12-22.
 */

public class TestEvent extends Event {

    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), params, Toast.LENGTH_SHORT).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void run() {
                    //android端异步调用Javascript中的方法,需要放在UI线程中
                   webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }

        return null;
    }
}
