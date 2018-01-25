package com.tinno.latte.ec.main.personal.address;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latte.net.RestClient;
import com.tinno.latte.net.callback.ISuccess;
import com.tinno.latte.ui.recycler.MultipleItemEntity;
import com.tinno.latte.ui.recycler.adapter.MultipleRecyclerAdapter;
import com.tinno.latte.util.log.LatteLogger;
import com.tinno.latteec.ec.R;
import com.tinno.latteec.ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by android on 18-1-22.
 */

public class AddressDelegate extends LatteDelegate implements ISuccess {

    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        RestClient.builder()
                .url("address.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        LatteLogger.d("AddressDelegate", response);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new AddressDataConverter().setJsonData(response).convert();
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }
}
