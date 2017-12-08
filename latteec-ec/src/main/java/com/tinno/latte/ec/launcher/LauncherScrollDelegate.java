package com.tinno.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.tinno.latte.app.AccountManager;
import com.tinno.latte.app.IUserChecker;
import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latte.ui.launcher.ILauncherListener;
import com.tinno.latte.ui.launcher.LauncherHolderCreator;
import com.tinno.latte.ui.launcher.OnLauncherFinishTag;
import com.tinno.latte.util.storage.LattePreference;
import com.tinno.latteec.ec.R;

import java.util.ArrayList;

/**
 * Created by android on 17-12-7.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    //<T>用来存放使用的R资源文件
    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private static boolean AddTag = false;

    private ILauncherListener mILauncherListener;

    private void initBanner() {
        if (!AddTag) {
            INTEGERS.add(R.mipmap.launcher_01);
            INTEGERS.add(R.mipmap.launcher_02);
            INTEGERS.add(R.mipmap.launcher_03);
            INTEGERS.add(R.mipmap.launcher_04);
            INTEGERS.add(R.mipmap.launcher_05);
            AddTag = true;
        }
        
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
            initBanner();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否登录了App
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNoSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
