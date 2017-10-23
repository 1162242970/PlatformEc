package com.tinno.platformec.example;

import android.app.Application;

import com.tinno.latte.app.Latte;

/**
 * Created by android on 17-10-21.
 */

public class ExampleApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .configure();
    }
}
