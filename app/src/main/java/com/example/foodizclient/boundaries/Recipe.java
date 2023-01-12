package com.example.foodizclient.boundaries;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Recipe implements Serializable {
    private String name;
    private UserId user;
    private String username;
    private String description;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private ArrayList<String> instructions = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();
    private String image;
    private int likes = 0;
    private Date uploadDate;
    private ObjectId objectId;

    public String getName() {
        return name;
    }

    public Recipe setName(String name) {
        this.name = name;
        return this;
    }

    public UserId getUser() {
        return user;
    }

    public Recipe setUser(UserId user) {
        this.user = user;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Recipe setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Recipe setDescription(String description) {
        this.description = description;
        return this;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Recipe setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public Recipe setInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
        return this;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public Recipe setComments(ArrayList<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Recipe setImage(String image) {
        this.image = image;
        return this;
    }

    public int getLikes() {
        return likes;
    }

    public Recipe setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public Recipe setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
        return this;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public Recipe setObjectId(ObjectId objectId) {
        this.objectId = objectId;
        return this;
    }
}
