package com.pku.pkuapp.model;

/**
 * Created by admin on 15/12/30.
 */
public class LectureData {
    private String id;
    private String detailUrl;
    private String title;
    private String time;
    private String where;
    private String price;
    private String imageUrl;

    public LectureData(String id, String detailUrl, String title, String time, String where, String price, String imageUrl) {
        this.id = id;
        this.detailUrl = detailUrl;
        this.title = title;
        this.time = time;
        this.where = where;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
