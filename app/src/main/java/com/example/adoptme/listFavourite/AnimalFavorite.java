package com.example.adoptme.listFavourite;

public class AnimalFavorite {
    public String title, description, location;
    public int img;

    public AnimalFavorite(int img, String title, String description, String location) {
        this.img = img;
        this.title = title;
        this.description = description;
        this.location = location;
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
