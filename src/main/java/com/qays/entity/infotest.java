package com.qays.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 03-26-2018 8:49
 */
@Entity
public class infotest {
    @Id
    @GeneratedValue
    private String id;

    private String contents;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
