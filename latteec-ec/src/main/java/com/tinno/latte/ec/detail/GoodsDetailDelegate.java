package com.tinno.latte.ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latteec.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by android on 17-12-18.
 * 商品界面
 */

public class GoodsDetailDelegate extends LatteDelegate{

    GoodsDetailDelegate(){

    }

    public static GoodsDetailDelegate create() {
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delagate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    /**
     *
     * 跳转到GoodsDetailDelegate时,默认为水平动画
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
