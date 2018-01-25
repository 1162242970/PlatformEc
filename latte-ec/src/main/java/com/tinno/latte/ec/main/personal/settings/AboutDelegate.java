package com.tinno.latte.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latteec.ec.R;
import com.tinno.latteec.ec.R2;

import butterknife.BindView;

/**
 * Created by android on 18-1-25.
 */

public class AboutDelegate extends LatteDelegate {

    @BindView(R2.id.tv_info)
    AppCompatTextView mTextView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mTextView.setText("这是一段简介");

       /* RestClient.builder()
                .url("about.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String info = JSON.parseObject(response).getString("data");
                        mTextView.setText(info);
                    }
                })
                .build()
                .get();
                */
    }
}
