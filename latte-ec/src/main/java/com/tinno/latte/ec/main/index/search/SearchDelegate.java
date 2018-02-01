package com.tinno.latte.ec.main.index.search;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;
import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latte.net.RestClient;
import com.tinno.latte.net.callback.ISuccess;
import com.tinno.latte.ui.recycler.MultipleItemEntity;
import com.tinno.latte.util.storage.LattePreference;
import com.tinno.latteec.ec.R;
import com.tinno.latteec.ec.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by android on 18-1-31.
 */

public class SearchDelegate extends LatteDelegate {

    @BindView(R2.id.rv_search)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchEdit = null;

    @OnClick(R2.id.tv_top_search)
    void onClickSearch() {
        //向服务器发送搜索请求
        RestClient.builder()
                .url("search.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        final String searchItemText = mSearchEdit.getText().toString();
                        saveItem(searchItemText);
                        mSearchEdit.setText("");

                        //展示一些东西
                        //弹出一段话
                    }
                })
                .build()
                .get();

        final String searchItemText = mSearchEdit.getText().toString();
        saveItem(searchItemText);
        mSearchEdit.setText("");

    }

    @OnClick(R2.id.icon_top_search_back)
    void onClickBack() {

        getSupportDelegate().pop();
    }

    /**
     * 将搜索过的字符串以Json的形势保存到SharedPreferences中
     */
    @SuppressWarnings("unchecked")
    private void saveItem(String item) {
        /*
         * StringUtils.isEmpty:判断字符串是否为空
         * StringUtils.isSpace:判断字符串是否为null或全为空白字符
         */
        if (!StringUtils.isEmpty(item) && !StringUtils.isSpace(item)) {
            List<String> history;

            //获取已经存入SharedPreferences的Json字符串
            final String historyStr =
                    LattePreference.getCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY);
            if (StringUtils.isEmpty(historyStr)) {
                history = new ArrayList<>();
            } else {
                //将json字符串转化成数组
                history = JSON.parseObject(historyStr, ArrayList.class);
            }
            //添加搜索过的字符串
            history.add(item);
            //将数组再转化成Json字符串
            final String json = JSON.toJSONString(history);

            //将json字符串加入SharedPreferences中
            LattePreference.addCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY, json);
        }
    }

    /**
     * 进入搜索界面自动对焦,然后弹出键盘
     */
    public void showSoftInput() {
        //让SearchEdit自动对焦
        mSearchEdit.setFocusable(true);
        mSearchEdit.setFocusableInTouchMode(true);
        mSearchEdit.requestFocus();
        //弹出键盘
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {


        showSoftInput();

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);

        final List<MultipleItemEntity> data = new SearchDataConverter().convert();
        final SearchAdapter adapter = new SearchAdapter(data);
        mRecyclerView.setAdapter(adapter);

        //设置Item的分割线
        final DividerItemDecoration itemDecoration = new DividerItemDecoration();
        itemDecoration.setDividerLookup(new DividerItemDecoration.DividerLookup() {
            @Override
            public Divider getVerticalDivider(int position) {
                return null;
            }

            @Override
            public Divider getHorizontalDivider(int position) {
                return new Divider.Builder()
                        .size(2)
                        .margin(20, 20)
                        .color(Color.GRAY)
                        .build();
            }
        });

        mRecyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_search;
    }

}
