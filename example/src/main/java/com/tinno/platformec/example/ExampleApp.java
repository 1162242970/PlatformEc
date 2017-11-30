package com.tinno.platformec.example;

import android.app.Application;
import android.graphics.LightingColorFilter;
import android.util.Log;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.tinno.latte.app.Latte;
import com.tinno.latte.ec.icon.FontEcModule;

/**
 * Created by android on 17-10-21.
 */

public class ExampleApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("ExampleDelegate","ExampleApp");
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();
    }
}
