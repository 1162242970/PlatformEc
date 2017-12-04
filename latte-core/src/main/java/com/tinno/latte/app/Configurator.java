package com.tinno.latte.app;

import android.content.Context;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by android on 17-10-21.
 *
 * Configurator类:进行各种种配置文件的存储和获取
 * LATTE_CONFIGS:用来存储各种配置文件,WeakHashMap比较HashMap不容易造成泄露
 */

public class Configurator {

    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        //枚举name方法,以字符串的形式输出
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    /**
     * 静态内部类单例模式初始化,线程安全的懒汉单例模式
     * 1.静态内部类创建
     * 2.getInstance方法获取
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {

        return Holder.INSTANCE;
    }

    final HashMap<String, Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    private void initIcons(){
        if (ICONS.size() > 0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i =1;i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withApoiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    /**
     * 核实配置是否完成
     *
     */

    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration si not ready, call configure");
        }
    }
        final <T> T getConfiguration(Enum<ConfigType> key){
            checkConfiguration();
            return (T) LATTE_CONFIGS.get(key);
        }
    }

