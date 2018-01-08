package com.tinno.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tinno.latte.app.AccountManager;
import com.tinno.latte.ec.database.DatabaseManager;
import com.tinno.latte.ec.database.UserProfile;

/**
 * Created by android on 17-12-8.
 */

public class SignHandler {

    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
//        DatabaseManager.getInstance().getDao().insert(profile);

        //让ExampleActivity回调onSignUpSuccess方法
        signListener.onSignUpSuccess();
    }

    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        //向表中插入数据
        DatabaseManager.getInstance().getDao().insert(profile);

        //保存登录成功状态
        AccountManager.setSignState(true);

        //让ExampleActivity回调onSignInSuccess方法
        signListener.onSignInSuccess();
    }
}
