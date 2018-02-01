package com.tinno.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tinno.latte.app.Latte;
import com.tinno.latte.net.RestClient;
import com.tinno.latte.net.callback.ISuccess;
import com.tinno.latte.ui.recycler.DataConverter;
import com.tinno.latte.ui.recycler.adapter.MultipleRecyclerAdapter;

/**
 * Created by android on 17-12-13.
 * 数据处理类
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;

    private RefreshHandler(SwipeRefreshLayout refreshLayout,
                          RecyclerView recyclerview,
                          DataConverter converter,
                          PagingBean bean) {
        this.REFRESH_LAYOUT = refreshLayout;
        this.BEAN = bean;
        this.RECYCLERVIEW = recyclerview;
        this.CONVERTER = converter;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    //工厂模式,创建RefreshHandler的接口
    public static RefreshHandler create(SwipeRefreshLayout refreshLayout,
                                        RecyclerView recyclerview,
                                        DataConverter converter) {
        return new RefreshHandler(refreshLayout, recyclerview, converter, new PagingBean());
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求,setRefreshing(false)一般放入success中
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url) {
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        //设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();

                    }
                })
                .build()
                .get();
    }

    /**
     * 分页的处理
     * @param url 请求分页的url
     */
    private void paging(final String url) {
        //当前页面能显示的商品数量大小
        final int pageSize = BEAN.getPageSize();
        //当前已经显示的商品数量
        final int currentCount = BEAN.getCurrentCount();
        //总共的商品数量
        final int total = BEAN.getTotal();
        //当前的页数
        final int index = BEAN.getPageIndex();
        if (mAdapter.getData().size() < pageSize || currentCount >= total) {
            //不需要分页
            mAdapter.loadMoreEnd(true);
        } else {
            //分页加载
            Latte.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    RestClient.builder()
                            //获取下一页的数据
                            .url(url + index)
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {
                                    //添加新的数据
                                    mAdapter.addData(CONVERTER.setJsonData(response).convert());
                                    //累加数量
                                    BEAN.setCurrentCount(mAdapter.getData().size());
                                    //刷新adapter
                                    mAdapter.loadMoreComplete();
                                    //页面+1,下次加载的时候就是获取下一页的数据
                                    BEAN.addIndex();
                                }
                            })
                            .build()
                            .get();
                }
            });
        }
    }


    @Override
    public void onRefresh() {
        refresh();
    }

    /**
     * 滑动到最后一个Item的时候会调用该方法
     */
    @Override
    public void onLoadMoreRequested() {
        paging("refresh.php?index=");
    }
}
