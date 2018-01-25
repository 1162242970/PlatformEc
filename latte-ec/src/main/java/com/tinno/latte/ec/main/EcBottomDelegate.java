package com.tinno.latte.ec.main;

import android.graphics.Color;

import com.tinno.latte.delegates.button.BaseBottomDelegate;
import com.tinno.latte.delegates.button.BottomItemDelegate;
import com.tinno.latte.delegates.button.BottomTabBean;
import com.tinno.latte.delegates.button.ItemBuilder;
import com.tinno.latte.ec.main.cart.ShopCartDelegate;
import com.tinno.latte.ec.main.discover.DiscoverDelegate;
import com.tinno.latte.ec.main.index.IndexDelegate;
import com.tinno.latte.ec.main.personal.PersonalDelegate;
import com.tinno.latte.ec.main.sort.list.SortDelegate;


import java.util.LinkedHashMap;

/**
 * Created by android on 17-12-12.
 *
 */

public class EcBottomDelegate extends BaseBottomDelegate{

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
        ITEMS.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        ITEMS.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        ITEMS.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        ITEMS.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        ITEMS.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItem(ITEMS).build();
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
