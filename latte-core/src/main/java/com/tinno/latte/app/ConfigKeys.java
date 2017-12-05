package com.tinno.latte.app;

/**
 * Created by android on 17-10-21.
 *
 * 枚举类
 * 在整个应用程序是唯一的单例,只能被初始化一次.
 *
 * 1.网络请求域名
 * 2.全局上下文
 * 3.确认初始化是否完成
 * 4.字体初始化
 * 5.拦截器
 */

public enum ConfigKeys {
    API_HOST,
    APPLICATION_CONTEXT,
    CONFIG_READY,
    ICON,
    INTERCEPTOR
}
