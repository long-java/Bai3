package com.example.thecoffeehouse;

public class item {
    String title,text,bt;
    Integer image;

    public item(String title, String text, String bt, Integer image) {
        this.title = title;
        this.text = text;
        this.image = image;
        this.bt=bt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
