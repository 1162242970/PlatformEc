package com.tinno.latte.ec.pay;

/**
 * Created by android on 18-1-5.
 */

public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
