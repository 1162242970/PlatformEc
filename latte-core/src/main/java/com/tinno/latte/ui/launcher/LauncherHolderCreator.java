package com.tinno.latte.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;


/**
 * Created by android on 17-12-7.
 */

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {
    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
