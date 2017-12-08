package com.tinno.latte.app;

/**
 * Created by android on 17-12-8.
 *
 * 检查用户是否已经登录
 */

public interface IUserChecker {

    void onSignIn();

    void onNoSignIn();
}
