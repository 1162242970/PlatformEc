package com.tinno.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.tinno.latte.app.Latte;

/**
 * 测量工具类
 */

public class DimenUtil {


    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
