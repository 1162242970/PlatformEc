package com.tinno.latte.delegates.web.callback;

/**
 * Created by android on 17-12-25.
 *
 * Web加载开始和结束调用的方法
 */

public interface IPageLoadListener {

    void onLoadStart();

    void onLoadEnd();
}
