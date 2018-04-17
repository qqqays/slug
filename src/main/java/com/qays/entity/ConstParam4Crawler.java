package com.qays.entity;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 04-17-2018 9:57
 */
public class ConstParam4Crawler {

    private String[] urls;
    private String option;
    private String seed;

    public ConstParam4Crawler() {
    }

    public ConstParam4Crawler(String[] urls, String option, String seed) {
        this.urls = urls;
        this.option = option;
        this.seed = seed;
    }

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }
}
