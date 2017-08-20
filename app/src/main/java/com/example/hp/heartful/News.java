package com.example.hp.heartful;

/**
 * Created by HP on 25-05-2017.
 */

public class News {
    private String Title,Description,Image;
    public  News(){
    }
    public News(String title, String description, String image) {
        this.Title = title;
        this.Description = description;
        this.Image = image;

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }


}
