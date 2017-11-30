package com.tinno.platformec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.tinno.latte.delegates.LatteDelegate;

/**
 * Created by android on 17-11-30.
 */

public class ExampleDelegate extends LatteDelegate{

    @Override
    public Object setLayout() {
        Log.d("ExampleDelegate","ExampleDelegate");
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
