package com.tinno.platformec.example;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.tinno.latte.activities.ProxyActivity;
import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latte.ec.launcher.LauncherDelegate;
import com.tinno.latte.ec.launcher.LauncherScrollDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏ActionBar
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {

        return new LauncherScrollDelegate();
    }
}