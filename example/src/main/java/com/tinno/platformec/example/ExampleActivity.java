package com.tinno.platformec.example;


import com.tinno.latte.activities.ProxyActivity;
import com.tinno.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {

        return new ExampleDelegate();
    }
}