package com.example.foodizclient.activities;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.example.foodizclient.Helper;
import com.example.foodizclient.R;
import com.example.foodizclient.boundaries.Recipe;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeInformationActivity extends AppCompatActivity {

    private AppCompatImageView  recipeInfo_IMG_image;
    private TextView            recipeInfo_LBL_name;
    private TextView            recipeInfo_LBL_description;
    private ListView            recipeInfo_LST_ingredients;
    private ListView            recipeInfo_LST_instructions;

    private Recipe recipe;
    private List<String> ingredientsString;

    ArrayAdapter<String> ingredientsAdapter;
    ArrayAdapter<String> instructionsAdapter;

    public static String KEY_RECIPE = "KEY_RECIPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_information);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            recipe = getIntent().getSerializableExtra(KEY_RECIPE, Recipe.class);
        } else {
            recipe = (Recipe) getIntent().getSerializableExtra(KEY_RECIPE);
        }

        findViews();
        initViews();
    }

    private void findViews() {
        recipeInfo_IMG_image        = findViewById(R.id.recipeInfo_IMG_image);
        recipeInfo_LBL_name         = findViewById(R.id.recipeInfo_LBL_name);
        recipeInfo_LBL_description  = findViewById(R.id.recipeInfo_LBL_description);
        recipeInfo_LST_ingredients  = findViewById(R.id.recipeInfo_LST_ingredients);
        recipeInfo_LST_instructions = findViewById(R.id.recipeInfo_LST_instructions);
    }

    private void initViews() {
        Glide.with(this)
                .load(Uri.parse(recipe.getImage()))
                .centerCrop()
                .into(recipeInfo_IMG_image);

        recipeInfo_LBL_name.setText(recipe.getName());

        recipeInfo_LBL_description.setText(recipe.getDescription());

        ingredientsString = recipe.getIngredients().stream()
                .map(s -> s.toString())
                .collect(Collectors.toList());
        ingredientsAdapter = new ArrayAdapter<>(
                this,
                R.layout.list_black_text,
                ingredientsString);
        recipeInfo_LST_ingredients.setAdapter(ingredientsAdapter);
        Helper.getListViewSize(recipeInfo_LST_ingredients);

        instructionsAdapter = new ArrayAdapter<>(
                this,
                R.layout.list_black_text,
                recipe.getInstructions());
        recipeInfo_LST_instructions.setAdapter(instructionsAdapter);
//        Helper.getListViewSize(recipeInfo_LST_instructions);



//        View v = instructionsAdapter.getView(instructionsAdapter.getCount()-1, null, recipeInfo_LST_instructions);
////        recipeInfo_LST_instructions.setMinimumHeight((int)y + 10);
//        recipeInfo_LST_instructions.getLayoutParams().height = (int)y + 10;
    }
}