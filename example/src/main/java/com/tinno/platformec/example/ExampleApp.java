package com.tinno.platformec.example;

import android.app.Application;
import android.content.Context;
import android.graphics.LightingColorFilter;
import android.util.Log;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.tinno.latte.app.ConfigType;
import com.tinno.latte.app.Latte;
import com.tinno.latte.ec.icon.FontEcModule;

import java.util.HashMap;

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
                .configure();
    }
}
