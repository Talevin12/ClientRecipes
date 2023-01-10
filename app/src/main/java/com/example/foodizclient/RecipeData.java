package com.example.foodizclient;

import android.content.Context;

import com.example.foodizclient.authentication.CommandManager;
import com.example.foodizclient.authentication.DataManager;
import com.example.foodizclient.authentication.UserManager;
import com.example.foodizclient.boundaries.CommandId;
import com.example.foodizclient.boundaries.Comment;
import com.example.foodizclient.boundaries.MiniAppCommandBoundary;
import com.example.foodizclient.boundaries.ObjectBoundary;
import com.example.foodizclient.boundaries.ObjectId;
import com.example.foodizclient.boundaries.Recipe;
import com.example.foodizclient.boundaries.UserId;
import com.example.foodizclient.callbacks.DataCallback;
import com.example.foodizclient.converters.CommentConverter;
import com.example.foodizclient.converters.RecipeConverter;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeData {
    private Context context;

    private UserId userId;

    private DataManager dataManager;
    private DataManager bindDataManager;
    private UserManager userManager;
    private CommandManager commandManager;

    private RecipeConverter recipeConverter = new RecipeConverter();
    private CommentConverter commentConverter = new CommentConverter();

    private DataCallback dataCallback;

    private DataCallback bindCallback = new DataCallback() {
        @Override
        public void setObjectBoundary(ObjectBoundary object) {
            Comment comment = commentConverter.toComment(object);
            bindCommentToRecipe(comment.getRecipeObjectId(), comment);
        }

        @Override
        public void setListOfObjects(ArrayList<ObjectBoundary> objects) {

        }

        @Override
        public void setObject(Object object) {

        }
    };

    public RecipeData(Context context, UserId userId, DataCallback dataCallback) {
        this.context = context;

        dataManager = new DataManager(context);
        userManager = new UserManager(context);
        commandManager = new CommandManager(context);
        bindDataManager = new DataManager(context);

        this.dataCallback = dataCallback;

        commandManager.setDataInterface(dataCallback);
        dataManager.setDataInterface(dataCallback);
        bindDataManager.setDataInterface(bindCallback);

        this.userId = userId;
    }

    public void CreateRecipe(Recipe recipe) {
        dataManager.createObject(recipeConverter.toObjectBoundary(recipe));
    }

    public void getAllRecipe() {
        dataManager.getObjectsByType("Recipe", userId.getSuperapp(), userId.getEmail());
    }

    public void likeRecipe(Recipe recipe, UserId userId) {
        MiniAppCommandBoundary miniAppCommand = new MiniAppCommandBoundary();

        miniAppCommand.setCommandId(new CommandId().setMiniapp("Recipes"));

        miniAppCommand.setCommand("LikeCommand");

        HashMap<String, ObjectId> objectIdMap = new HashMap<>();
        objectIdMap.put("objectId", recipe.getObjectId());
        miniAppCommand.setTargetObject(objectIdMap);

        HashMap<String, UserId> userIdMap = new HashMap<>();
        userIdMap.put("userId", userId);
        miniAppCommand.setInvokedBy(userIdMap);

        commandManager.invokeCommand(miniAppCommand);
    }

    public void unlikeRecipe(Recipe recipe) {
        MiniAppCommandBoundary miniAppCommand = new MiniAppCommandBoundary();

        miniAppCommand.setCommandId(new CommandId().setMiniapp("Recipes"));

        miniAppCommand.setCommand("LikeCommand");

        HashMap<String, ObjectId> objectIdMap = new HashMap<>();
        objectIdMap.put("objectId", recipe.getObjectId());
        miniAppCommand.setTargetObject(objectIdMap);

        HashMap<String, UserId> userIdMap = new HashMap<>();
        userIdMap.put("userId", recipe.getUser());
        miniAppCommand.setInvokedBy(userIdMap);

        commandManager.invokeCommand(miniAppCommand);
    }

    public void createComment(Comment comment, Recipe recipe) {
        recipe.getComments().add(comment);
        comment.setRecipeObjectId(recipe.getObjectId());

        bindDataManager.createObject(commentConverter.toObjectBoundary(comment, userId));
    }

    private void bindCommentToRecipe(ObjectId recipeObjectId, Comment comment) {
        dataManager.bindObjects(recipeObjectId.getSuperapp(),
                                    recipeObjectId.getInternalObjectId(),
                                    userId.getSuperapp(),
                                    userId.getEmail(),
                                    comment.getCommentObjectId());
    }

    public void getRecipeComments(Recipe recipe) {
        dataManager.getAllChildrenObjects(recipe.getObjectId().getSuperapp(),
                                            recipe.getObjectId().getInternalObjectId(),
                                            userId.getSuperapp(),
                                            userId.getEmail());
    }
}
