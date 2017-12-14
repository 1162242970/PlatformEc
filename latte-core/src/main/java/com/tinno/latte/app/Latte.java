package com.tinno.latte.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by android on 17-10-21.
 * <p>
 * Latte是一个对外工具类,提供静态方法给Application调用
 */

public final class Latte {

    /**
     * 返回一个Configurator的单例对象,完成各种配置
     * 获取ConfigKeys实例的时候,运用了泛型,使得返回的对象不用转换
     *
     */
    public static Configurator init(Context context) {
        getConfigurator().getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }
}
