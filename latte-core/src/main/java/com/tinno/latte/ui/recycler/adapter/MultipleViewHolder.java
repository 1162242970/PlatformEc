package com.tinno.latte.ui.recycler.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by android on 17-12-14.
 */

public class MultipleViewHolder extends BaseViewHolder{

    public MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
