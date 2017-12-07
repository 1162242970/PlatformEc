package com.tinno.latte.util.timer;

import java.util.TimerTask;

/**
 * Created by android on 17-12-6.
 */

public class BaseTimerTask extends TimerTask{

    private ITimerListener iTimerListener = null;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.iTimerListener = iTimerListener;
    }
    @Override
    public void run() {
        if (iTimerListener != null) {
            iTimerListener.onTimer();
        }
    }
}
