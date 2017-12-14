package com.tinno.latte.ui.banner;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.tinno.latte.R;

import java.util.ArrayList;

/**
 * Created by android on 17-12-14.
 */

public class BannerCreator {

    public static void setDefault(ConvenientBanner convenientBanner,
                                  ArrayList<String> banners,
                                  OnItemClickListener clickListener
                                    ){
        convenientBanner.setPages(new HolderCreator(), banners)
                //设置图片为指示器
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                //指示器的位置设为中间
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                //设置监听
                .setOnItemClickListener(clickListener)
                //定义翻页效果
                .setPageTransformer(new DefaultTransformer())
                //自动翻页的时间为3s
                .startTurning(3000)
                //允许循环
                .setCanLoop(true);

    }

}
