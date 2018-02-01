package com.tinno.latte.ec.main.index.search;

import com.alibaba.fastjson.JSONArray;
import com.tinno.latte.ui.recycler.DataConverter;
import com.tinno.latte.ui.recycler.MultipleFields;
import com.tinno.latte.ui.recycler.MultipleItemEntity;
import com.tinno.latte.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * Created by android on 18-1-31.
 */

public class SearchDataConverter extends DataConverter {

    public static final String TAG_SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        //获取之前存储在SharedPreferences中Json字符串
        final String jsonStr = LattePreference.getCustomAppProfile("search_history");
        if (!jsonStr.equals("")) {
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++) {
                final String historyItemText = array.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build();
                ENTITIES.add(entity);
            }
        }
        return ENTITIES;
    }


}
