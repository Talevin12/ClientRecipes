package com.example.foodizclient.converters;

import com.example.foodizclient.boundaries.Comment;
import com.example.foodizclient.boundaries.ObjectBoundary;
import com.example.foodizclient.boundaries.ObjectId;
import com.example.foodizclient.boundaries.UserId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class CommentConverter {
    public ObjectBoundary toObjectBoundary(Comment comment, UserId userId) {
        ObjectBoundary objectBoundary = new ObjectBoundary();

        objectBoundary.setActive(true);

        HashMap<String, UserId> userIdMap = new HashMap<>();
        userIdMap.put("userId", userId);
        objectBoundary.setCreatedBy(userIdMap);

        objectBoundary.setType("Comment");

        HashMap<String, Object> details = new HashMap<>();
        details.put("username", comment.getUsername());
        details.put("text", comment.getText());
        details.put("recipeObjectId", comment.getRecipeObjectId());
        objectBoundary.setObjectDetails(details);

        objectBoundary.setAlias("Atreus");

        return objectBoundary;
    }

    public Comment toComment(ObjectBoundary objectBoundary) {
        Comment comment = new Comment();

        comment.setUsername((String) objectBoundary.getObjectDetails().get("username"));

        comment.setText((String) objectBoundary.getObjectDetails().get("text"));

        ObjectId recipeObjectId = new Gson().fromJson(objectBoundary.getObjectDetails().get("recipeObjectId").toString(),
                new TypeToken<ObjectId>() {}.getType());

        comment.setRecipeObjectId(recipeObjectId);

        comment.setCommentObjectId(objectBoundary.getObjectId());

        return comment;
    }
}
