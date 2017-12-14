package com.tinno.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by android on 17-11-29.
 */

public enum  EcIcons implements Icon{

    icon_scan('\ue602'),
    icon_ali_pay('\ue606');

    private char character;

    EcIcons(char c) {
        this.character = c;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
