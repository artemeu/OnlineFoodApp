package com.bilgeadam.mobilefoodapp.data;

public class Meal {
    private String image;
    private String mealName;
    private String description;

    public Meal(String image, String mealName, String description) {
        this.image = image;
        this.mealName = mealName;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
