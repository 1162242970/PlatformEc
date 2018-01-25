package com.tinno.latte.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latte.ec.main.sort.adapter.SortRecyclerAdapter;
import com.tinno.latte.net.RestClient;
import com.tinno.latte.net.callback.ISuccess;
import com.tinno.latte.ui.recycler.MultipleItemEntity;
import com.tinno.latteec.ec.R;
import com.tinno.latteec.ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by android on 17-12-18.
 */

public class VerticalListDelegate extends LatteDelegate{

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("sort_list.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //获取处理后的List
                       final List<MultipleItemEntity> data =
                               new VerticalListDataConverter().setJsonData(response).convert();
                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();

    }
}
