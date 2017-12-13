package com.tinno.platformec.example;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.tinno.latte.activities.ProxyActivity;
import com.tinno.latte.app.ConfigKeys;
import com.tinno.latte.app.Latte;
import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latte.ec.launcher.LauncherDelegate;
import com.tinno.latte.ec.launcher.LauncherScrollDelegate;
import com.tinno.latte.ec.main.EcBottomDelegate;
import com.tinno.latte.ec.sign.ISignListener;
import com.tinno.latte.ec.sign.SignInDelegate;
import com.tinno.latte.ec.sign.SignUpDelegate;
import com.tinno.latte.ui.launcher.ILauncherListener;
import com.tinno.latte.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity implements ISignListener,
        ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏ActionBar
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //配置Activity
        Latte.getConfigurator().withActivity(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {

        return new LauncherDelegate();
    }

    /**
     * 登录成功的回调接口
     */
    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 注册成功的回调接口
     */
    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 初始化界面之后的登录回调接口,
     * 根据tag判断之前是否成功登录过
     */
    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            //已经登录,直接进入主页面
            case SIGNED:
                Toast.makeText(this, "启动结束,用户登录了", Toast.LENGTH_SHORT).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            //未登录,进入登录页面
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束,用户没登录", Toast.LENGTH_SHORT).show();
                //进入后,移除之前的所有界面
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}