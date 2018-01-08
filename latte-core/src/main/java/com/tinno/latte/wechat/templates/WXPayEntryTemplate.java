package com.tinno.latte.wechat.templates;

import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tinno.latte.activities.ProxyActivity;
import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latte.wechat.BaseWXPayEntryActivity;

/**
 * Created by android on 17-12-11.
 */

public class WXPayEntryTemplate extends BaseWXPayEntryActivity{

    @Override
    protected void onPaySuccess() {
        Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
        //悄悄取消界面
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onPayFail() {
        Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
        //悄悄取消界面
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onPayCancel() {
        Toast.makeText(this, "支付取消", Toast.LENGTH_SHORT).show();
        //悄悄取消界面
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
}
