package com.example.shpocktask.model.entity;

/**
 * Created by vinod on 3/21/2018.
 */

public class Item {
    private int id;
    private String description;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description==null?"":description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getTitle() {
        return title==null?"":title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;
}
