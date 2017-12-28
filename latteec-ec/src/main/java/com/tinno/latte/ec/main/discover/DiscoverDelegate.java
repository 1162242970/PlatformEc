package com.tinno.latte.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;


import com.tinno.latte.delegates.button.BottomItemDelegate;
import com.tinno.latte.delegates.web.WebDelegateImpl;
import com.tinno.latteec.ec.R;

/**
 * Created by android on 17-12-21.
 */

public class DiscoverDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //创建Web页面
        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
        //设置父类的delegate,之后在父类完成跳转
        delegate.setTopDelegate(this.getParentDelagate());
        //加载Web页面
        getSupportDelegate().loadRootFragment(R.id.web_discovery_container, delegate);
    }
}
