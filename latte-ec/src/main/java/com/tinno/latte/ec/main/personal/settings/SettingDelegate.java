package com.tinno.latte.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latte.ec.main.personal.address.AddressDelegate;
import com.tinno.latte.ec.main.personal.list.ListAdapter;
import com.tinno.latte.ec.main.personal.list.ListBean;
import com.tinno.latte.ec.main.personal.list.ListItemType;
import com.tinno.latte.util.callback.CallbackManager;
import com.tinno.latte.util.callback.CallbackType;
import com.tinno.latteec.ec.R;
import com.tinno.latteec.ec.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by android on 18-1-25.
 */

public class SettingDelegate extends LatteDelegate{

    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_settings;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        ListBean push = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_SWITCH)
                .setId(1)
                .setDelegate(new AddressDelegate())
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_OPEN_PUSH).executeCallback(null);
                            Toast.makeText(getContext(), "打开推送", Toast.LENGTH_SHORT).show();
                        } else {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_STOP_PUSH).executeCallback(null);
                            Toast.makeText(getContext(), "关闭推送", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setText("消息推送")
                .build();

        ListBean about = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new AboutDelegate())
                .setText("关于")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(push);
        data.add(about);

        //设置RecyclerView
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new SettingClickListener(this));

    }
}
