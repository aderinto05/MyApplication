package com.myapplication.model;

import java.util.List;

public class Item {
    private String thumbnailUrl;
    private List<ItemChild> itemChild;

    public Item(String thumbnailUrl, List<ItemChild> itemChild) {
        this.thumbnailUrl = thumbnailUrl;
        this.itemChild = itemChild;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public List<ItemChild> getItemChild() {
        return itemChild;
    }

    public void setItemChild(List<ItemChild> itemChild) {
        this.itemChild = itemChild;
    }
}

