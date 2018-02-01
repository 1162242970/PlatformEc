package com.tinno.latte.ui.scanner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latte.ui.camera.RequestCodes;
import com.tinno.latte.util.callback.CallbackManager;
import com.tinno.latte.util.callback.CallbackType;
import com.tinno.latte.util.callback.IGlobalCallback;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by android on 18-1-31.
 */

public class ScannerDelegate extends LatteDelegate implements ZBarScannerView.ResultHandler{

    private ScanView mScanView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mScanView == null) {
            mScanView = new ScanView(getContext());
        }
        //自动对焦
        mScanView.setAutoFocus(true);
        //设置监听
        mScanView.setResultHandler(this);
    }

    @Override
    public Object setLayout() {
        return mScanView;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    /**
     * 开启相机进行二维码扫描
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mScanView != null) {
            mScanView.startCamera();
        }
    }

    /**
     * 关闭相机二维码扫描
     */
    @Override
    public void onPause() {
        super.onPause();
        if (mScanView != null) {
            mScanView.stopCameraPreview();
            mScanView.stopCamera();
        }
    }

    /**
     * 处理获得的二维码信息
     */
    @Override
    public void handleResult(Result result) {
        //设置全局的回调,然后在IndexDelegate里面处理
        @SuppressWarnings("uncheck")
        final IGlobalCallback<String> callback = CallbackManager
                .getInstance()
                .getCallback(CallbackType.ON_SCAN);
        if (callback != null) {
            callback.executeCallback(result.getContents());
        }
        getSupportDelegate().pop();
    }
}
