package com.tinno.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.tinno.latte.delegates.button.BottomItemDelegate;
import com.tinno.latte.ec.main.EcBottomDelegate;
import com.tinno.latte.ec.main.index.search.SearchDelegate;
import com.tinno.latte.ui.recycler.Divider.BaseDecoration;
import com.tinno.latte.ui.refresh.RefreshHandler;
import com.tinno.latte.util.callback.CallbackManager;
import com.tinno.latte.util.callback.CallbackType;
import com.tinno.latte.util.callback.IGlobalCallback;
import com.tinno.latteec.ec.R;
import com.tinno.latteec.ec.R2;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by android on 17-12-12.
 */

public class IndexDelegate extends BottomItemDelegate implements View.OnFocusChangeListener {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mSwipeRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mReFreshHandler = null;

    //二维码点击按钮
    @OnClick(R2.id.icon_index_scan)
    void onClickScanQrCode() {
        startScanWithCheck(this.getParentDelegate());

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        //加载页面的数据,实现分页加载,下拉刷新
        mReFreshHandler = RefreshHandler.create(mSwipeRefreshLayout, mRecyclerView, new IndexDataConverter());
        //处理扫描的二维码扫描
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_SCAN, new IGlobalCallback<String>() {
                    @Override
                    public void executeCallback(@Nullable String args) {
                        Toast.makeText(getContext(), "得到的二维码是" + args, Toast.LENGTH_LONG).show();
                    }
                });
        mSearchView.setOnFocusChangeListener(this);
    }

    /**
     * 初始化下滑加载
     */
    private void initRefreshLayout() {
        //设置颜色
        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getContext(), android.R.color.holo_blue_light),
                ContextCompat.getColor(getContext(), android.R.color.holo_orange_light),
                ContextCompat.getColor(getContext(), android.R.color.holo_red_light)
        );
        //true:下拉时候,球由小变大,然后分别是初始高度和最终高度
        mSwipeRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        //为RecyclerView设置分割线
        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(
                getContext(), R.color.app_background), 5));

        //设置渐变Toolbar
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        //主页面点击跳转商品页面的时候，必须获取父Fragment完成跳转,这样可以消除下面的标题栏
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        //加载首页数据
        mReFreshHandler.firstPage("index.php");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;

    }

    /**
     * SearchView聚焦的时候跳转到搜索页面
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            getParentDelegate().getSupportDelegate().start(new SearchDelegate());
            mSearchView.clearFocus();
        }
    }
}
