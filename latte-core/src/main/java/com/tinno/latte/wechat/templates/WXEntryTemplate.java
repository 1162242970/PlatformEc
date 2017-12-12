package com.tinno.latte.wechat.templates;


import com.tinno.latte.wechat.BaseWXEntryActivity;
import com.tinno.latte.wechat.LatteWeChat;

/**
 * Created by android on 17-12-11.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getiWeChatSignInCallback().onSignInSuccess(userInfo);
    }
}
