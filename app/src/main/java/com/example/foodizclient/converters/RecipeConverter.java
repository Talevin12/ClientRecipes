package com.example.foodizclient.converters;

import com.example.foodizclient.boundaries.Ingredient;
import com.example.foodizclient.boundaries.ObjectBoundary;
import com.example.foodizclient.boundaries.Recipe;
import com.example.foodizclient.boundaries.UserId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecipeConverter {
    public ObjectBoundary toObjectBoundary(Recipe recipe) {
        ObjectBoundary objectBoundary = new ObjectBoundary();

        objectBoundary.setActive(true);

        HashMap<String, UserId> userIdMap = new HashMap<>();
        userIdMap.put("userId", recipe.getUser());
        objectBoundary.setCreatedBy(userIdMap);

        objectBoundary.setType("Recipe");

        HashMap<String, Object> details = new HashMap<>();
        details.put("name", recipe.getName());
        details.put("username", recipe.getUsername());
        details.put("description", recipe.getDescription());

        ArrayList<HashMap<String, String>> ingredientsList = new ArrayList<>();
        for(Ingredient ing : recipe.getIngredients()) {
            HashMap<String, String> ingredientMap = new HashMap<>();

            ingredientMap.put("name", ing.getName());
            ingredientMap.put("unit", ing.getUnit());
            ingredientMap.put("amount", String.valueOf(ing.getAmount()));

            ingredientsList.add(ingredientMap);
        }

        details.put("ingredients", ingredientsList);
        details.put("instructions", recipe.getInstructions());
        details.put("image", recipe.getImage());
        details.put("likes", new ArrayList<UserId>());
        details.put("maxLikes", 3);
        objectBoundary.setObjectDetails(details);

        objectBoundary.setAlias("Kratos");

        objectBoundary.setCreationTimestamp(recipe.getUploadDate());

        return objectBoundary;
    }

    public Recipe toRecipe(ObjectBoundary objectBoundary) {
        Recipe recipe = new Recipe();

        recipe.setUser(objectBoundary.getCreatedBy().get("userId"));

        recipe.setName((String)objectBoundary.getObjectDetails().get("name"));

        recipe.setUsername((String) objectBoundary.getObjectDetails().get("username"));

        recipe.setDescription((String)objectBoundary.getObjectDetails().get("description"));

        ArrayList<Map<String, String>> ingredientsListMaps = (ArrayList<Map<String, String>>)objectBoundary.getObjectDetails().get("ingredients");
        ArrayList<Ingredient> ingredientsList = new ArrayList<>();
        for(Map<String, String> ing : ingredientsListMaps) {
            ingredientsList.add(new Ingredient()
                    .setName(ing.get("name"))
                    .setUnit(ing.get("unit"))
                    .setAmount(Float.valueOf(ing.get("amount"))));
        }
        recipe.setIngredients(ingredientsList);

        recipe.setInstructions((ArrayList<String>) objectBoundary.getObjectDetails().get("instructions"));

        recipe.setImage((String) objectBoundary.getObjectDetails().get("image"));

        recipe.setLikes(((ArrayList<UserId>) objectBoundary.getObjectDetails().get("likes")).size());

        recipe.setObjectId(objectBoundary.getObjectId());

        recipe.setUploadDate(objectBoundary.getCreationTimestamp());

        return recipe;
    }
}
