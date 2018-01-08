package com.tinno.latte.ec.pay;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tinno.latte.app.ConfigKeys;
import com.tinno.latte.app.Latte;
import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latte.net.RestClient;
import com.tinno.latte.net.callback.ISuccess;
import com.tinno.latte.ui.loader.LatteLoader;
import com.tinno.latte.util.log.LatteLogger;
import com.tinno.latte.wechat.LatteWeChat;
import com.tinno.latteec.ec.R;

/**
 * Created by android on 18-1-5.
 */

public class FastPay implements View.OnClickListener {

    //设置支付回调监听
    private IAlPayResultListener iAlPayResultListener = null;
    private Activity activity = null;

    private AlertDialog dialog = null;
    private int orderId = -1;

    private FastPay(LatteDelegate delegate) {
        this.activity = delegate.getProxyActivity();
        this.dialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static FastPay create(LatteDelegate delegate) {
        return new FastPay(delegate);
    }

    /**
     * 弹出框初始化设置
     */
    public void beginPayDialog() {
        dialog.show();
        final Window window = dialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);

            window.findViewById(R.id.itv_dialog_pay_aliPay).setOnClickListener(this);
            window.findViewById(R.id.itv_dialog_pay_weChat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);
        }
    }

    public FastPay setPayResultListener(IAlPayResultListener listener) {
        this.iAlPayResultListener = listener;
        return this;
    }

    public FastPay setOrderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    /**
     * 支付宝支付
     */
    private void alPay(int orderId) {
        final String singUrl = "";
        //获取签名字符串
        RestClient.builder()
                .url(singUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String paySign = JSON.parseObject(response).getString("result");
                        //必须是异步的调用客户端支付接口
                        final PayAsyncTask payAsyncTask = new PayAsyncTask(activity, iAlPayResultListener);
                        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paySign);
                    }
                })
                .build()
                .post();
    }

    /**
     * 微信支付
     */
    private void weChatPay(int orderId) {
        LatteLoader.stopLoading();
        final String weChatPrePayUrl = "你的服务端微信预支付地址" + orderId;
        LatteLogger.d("WX_PAY", weChatPrePayUrl);

        final IWXAPI iwxapi = LatteWeChat.getInstance().getWXAPI();
        final String appId = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
        iwxapi.registerApp(appId);
        RestClient.builder()
                .url(weChatPrePayUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject result =
                                JSON.parseObject(response).getJSONObject("result");
                        final String prepayId = result.getString("prepayid");
                        final String partnerId = result.getString("partnerid");
                        final String packageValue = result.getString("package");
                        final String timestamp = result.getString("timestamp");
                        final String nonceStr = result.getString("noncestr");
                        final String paySign = result.getString("sign");

                        final PayReq payReq = new PayReq();
                        payReq.appId = appId;
                        payReq.prepayId = prepayId;
                        payReq.partnerId = partnerId;
                        payReq.packageValue = packageValue;
                        payReq.timeStamp = timestamp;
                        payReq.nonceStr = nonceStr;
                        payReq.sign = paySign;

                        iwxapi.sendReq(payReq);
                    }
                })
                .build()
                .post();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.itv_dialog_pay_aliPay) {
            alPay(orderId);
            dialog.cancel();
        } else if (id == R.id.itv_dialog_pay_weChat) {
            weChatPay(orderId);
            dialog.cancel();
        } else if (id == R.id.btn_dialog_pay_cancel) {
            dialog.cancel();
        }
    }
}
