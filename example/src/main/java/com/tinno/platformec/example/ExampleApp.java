package com.tinno.platformec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.tinno.latte.app.Latte;
import com.tinno.latte.ec.database.DatabaseManager;
import com.tinno.latte.ec.icon.FontEcModule;
import com.tinno.latte.net.interceptor.DebugInterceptor;

import okhttp3.Interceptor;

/**
 * Created by android on 17-10-21.
 */

public class ExampleApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApoiHost("http://localhost/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withInterceptor(new DebugInterceptor("sign_up",R.raw.test))
                .configure();
        DatabaseManager.getInstance().init(this);
    }
}
