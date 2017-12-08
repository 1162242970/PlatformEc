package com.tinno.latte.app;

import com.tinno.latte.util.storage.LattePreference;

/**
 * Created by android on 17-12-8.
 *
 * AccountManager类
 */

public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    //保存用户登录状态,登陆后调用
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    /**
     * 根据SignTag.SIGN_TAG枚举类判断是否已经登录,true为登录,false未登录
     * 用户点击登录按钮以后,调用setSignState()方法将SignTag.SIGN_TAG的值设为true
     *
     */
    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNoSignIn();
        }
    }
}
