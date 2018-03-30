package com.qays.entity;

import javax.persistence.*;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 03-30-2018 9:09
 */
@Entity
@Table(name="page_info")
public class PageEntity {

    @Id
    private String id;

    @Basic
    @Column(name="title")
    private String title;

    @Basic
    @Column(name="keywords")
    private String keywords;

    @Basic
    private String description;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "longtext")
    private String htmlContent;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "text")
    private String textContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
