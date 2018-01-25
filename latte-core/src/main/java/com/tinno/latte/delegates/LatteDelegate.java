package com.tinno.latte.delegates;

/**
 * Created by android on 17-11-30.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate{

    public <T extends LatteDelegate> T getParentDelegate(){
        return (T)getParentFragment();
    }
}
