package com.tinno.latte.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * Created by android on 18-1-31.
 */

public class LatteViewFinderVIew extends ViewFinderView {
    public LatteViewFinderVIew(Context context) {
        this(context, null);
    }

    public LatteViewFinderVIew(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        //设置边框为正方形
        mSquareViewFinder = true;
        //设置颜色为黄色
        mBorderPaint.setColor(Color.YELLOW);
        mLaserPaint.setColor(Color.YELLOW);
    }
}
