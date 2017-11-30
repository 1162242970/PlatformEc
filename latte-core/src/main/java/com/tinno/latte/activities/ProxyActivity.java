package com.tinno.latte.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;

import com.tinno.latte.R;
import com.tinno.latte.delegates.LatteDelegate;


import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by android on 17-11-29.
 */

public abstract class ProxyActivity extends SupportActivity{

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ExampleDelegate","ProxyActivity");
        initContainer(savedInstanceState);

    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        //绑定Fragment
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
            Log.d("ExampleDelegate","ProxyActivity");
        }
    }


    /**
     * 代码优化,回收一些垃圾文件
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
