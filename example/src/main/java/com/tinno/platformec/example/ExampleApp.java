package com.tinno.platformec.example;

import android.app.Application;
import android.support.annotation.Nullable;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.tinno.latte.app.Latte;
import com.tinno.latte.net.rx.AddCookieInterceptor;
import com.tinno.latte.util.callback.CallbackManager;
import com.tinno.latte.util.callback.CallbackType;
import com.tinno.latte.util.callback.IGlobalCallback;
import com.tinno.platformec.example.event.TestEvent;
import com.tinno.latte.ec.database.DatabaseManager;
import com.tinno.latte.ec.icon.FontEcModule;
import com.tinno.latte.net.interceptor.DebugInterceptor;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by android on 17-10-21.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withInterceptor(new DebugInterceptor("sign_up", R.raw.test))
                //添加Cookie同步拦截器
                .withWebHost("https://www.baidu.com/")
                .withInterceptor(new AddCookieInterceptor())
                .withWeChatAppId("wxfcdcecd9df8e0faa")
                .withWeChatAppSecret("a0560f75335b06e3ebea70f29ff219bf")
                .withJavaScriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                .configure();
        DatabaseManager.getInstance().init(this);

        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Latte.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            JPushInterface.stopPush(Latte.getApplicationContext());
                        }
                    }
                });
    }

}
