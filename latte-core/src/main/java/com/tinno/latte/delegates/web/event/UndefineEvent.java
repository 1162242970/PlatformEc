package com.tinno.latte.delegates.web.event;

import android.widget.Toast;

/**
 * Created by android on 17-12-22.
 */

public class UndefineEvent extends Event{


    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), params, Toast.LENGTH_SHORT).show();
        return null;
    }
}
