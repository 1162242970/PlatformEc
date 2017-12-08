package com.tinno.platformec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latte.net.RestClient;
import com.tinno.latte.net.callback.IError;
import com.tinno.latte.net.callback.IFailure;
import com.tinno.latte.net.callback.ISuccess;

/**
 * Created by android on 17-11-30.
 */

public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .loader(getContext())
                .url("http://localhost/index")
                .params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}


