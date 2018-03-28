package com.qays.util;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 03-28-2018 21:53
 */

public class MyThreadPool implements Runnable {

    GainLinks gainLinks;

    public MyThreadPool(GainLinks gainLinks) {
        this.gainLinks = gainLinks;
    }


    @Override
    public void run() {
        while (gainLinks.getQueue().peek() != null) {
            gainLinks.operateAlpha();
            System.out.println(Thread.currentThread().getName() + "\n");
        }
    }

}
