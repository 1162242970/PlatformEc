package com.tinno.latte.ec.main;

import android.graphics.Color;

import com.tinno.latte.delegates.button.BaseBottomDelegate;
import com.tinno.latte.delegates.button.BottomItemDelegate;
import com.tinno.latte.delegates.button.BottomTabBean;
import com.tinno.latte.delegates.button.ItemBuilder;
import com.tinno.latte.ec.main.index.IndexDelegate;
import com.tinno.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by android on 17-12-12.
 *
 */

public class EcBottomDelegate extends BaseBottomDelegate{

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItem(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
