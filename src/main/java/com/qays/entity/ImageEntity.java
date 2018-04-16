package com.qays.entity;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 04-16-2018 10:12
 */
public class ImageEntity {
    private Integer imgNum;
    private Integer altNum;
    private Integer matchAltNum;

    public ImageEntity() {
        this.imgNum = 0;
        this.altNum = 0;
        this.matchAltNum = 0;
    }

    public Integer getImgNum() {
        return imgNum;
    }

    public void setImgNum(Integer imgNum) {
        this.imgNum = imgNum;
    }

    public Integer getAltNum() {
        return altNum;
    }

    public void setAltNum(Integer altNum) {
        this.altNum = altNum;
    }

    public Integer getMatchAltNum() {
        return matchAltNum;
    }

    public void setMatchAltNum(Integer matchAltNum) {
        this.matchAltNum = matchAltNum;
    }
}
