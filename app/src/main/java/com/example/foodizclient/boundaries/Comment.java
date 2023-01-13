package com.example.foodizclient.boundaries;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private String username;
    private String text;
    private ObjectId commentObjectId;
    private ObjectId RecipeObjectId;
    private Date publishDate;

    public String getUsername() {
        return username;
    }

    public Comment setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getText() {
        return text;
    }

    public Comment setText(String text) {
        this.text = text;
        return this;
    }

    public ObjectId getCommentObjectId() {
        return commentObjectId;
    }

    public Comment setCommentObjectId(ObjectId commentObjectId) {
        this.commentObjectId = commentObjectId;
        return this;
    }

    public ObjectId getRecipeObjectId() {
        return RecipeObjectId;
    }

    public Comment setRecipeObjectId(ObjectId recipeObjectId) {
        RecipeObjectId = recipeObjectId;
        return this;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public Comment setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    @Override
    public String toString() {
        return username +":\n"+ text;
    }
}
