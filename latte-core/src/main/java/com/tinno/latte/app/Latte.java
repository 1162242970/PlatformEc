package com.tinno.latte.app;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by android on 17-10-21.
 *
 * Latte是一个对外工具类,提供静态方法给Application调用
 */

public final class Latte {

    /**
     *
     *返回一个Configurator的单例对象,完成各种配置
     */
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),
                context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
