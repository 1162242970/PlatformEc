package com.tinno.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tinno.latte.delegates.button.BottomItemDelegate;
import com.tinno.latteec.ec.R;

/**
 * Created by android on 17-12-12.
 */

public class IndexDelegate extends BottomItemDelegate{

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
