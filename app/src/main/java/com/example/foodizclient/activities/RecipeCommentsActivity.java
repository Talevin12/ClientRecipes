package com.example.foodizclient.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.foodizclient.R;
import com.example.foodizclient.RecipeData;
import com.example.foodizclient.authentication.UserManager;
import com.example.foodizclient.boundaries.Comment;
import com.example.foodizclient.boundaries.ObjectBoundary;
import com.example.foodizclient.boundaries.Recipe;
import com.example.foodizclient.boundaries.UserBoundary;
import com.example.foodizclient.boundaries.UserRole;
import com.example.foodizclient.callbacks.DataCallback;
import com.example.foodizclient.callbacks.UserCallback;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class RecipeCommentsActivity extends AppCompatActivity {

    private Recipe recipe;
    private UserBoundary user;
    private List<String> commentsString;
    private ArrayAdapter<String> adapter;

    private ListView comments_LST_comments;
    private AppCompatEditText comments_EDITTEXT_newComment;
    private ExtendedFloatingActionButton comments_BTN_send;

    public static String KEY_RECIPE = "KEY_RECIPE";
    public static String KEY_USER = "KEY_USER";

    UserManager userManager = new UserManager(this);

    UserCallback userCallback = () -> initViews();

    DataCallback dataCallback = new DataCallback() {
        @Override
        public void setObjectBoundary(ObjectBoundary object) {}

        @Override
        public void setListOfObjects(ArrayList<ObjectBoundary> objects) {}

        @Override
        public void setObject(Object object) {}
    };

    RecipeData recipeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_comments);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            user = getIntent().getSerializableExtra(KEY_USER, UserBoundary.class);
            recipe = getIntent().getSerializableExtra(KEY_RECIPE, Recipe.class);
        } else {
            user = (UserBoundary) getIntent().getSerializableExtra(KEY_USER);
            recipe = (Recipe) getIntent().getSerializableExtra(KEY_RECIPE);
        }

        userManager.setUserCallback(userCallback);
        recipeData = new RecipeData(this, user.getUserId(), dataCallback);

        findViews();

        userManager.updateUser(user.setRole(UserRole.SUPERAPP_USER));
    }

    private void findViews() {
        comments_LST_comments = findViewById(R.id.comments_LST_comments);
        comments_EDITTEXT_newComment = findViewById(R.id.comments_EDITTEXT_newComment);
        comments_BTN_send = findViewById(R.id.comments_BTN_send);
    }

    private void initViews() {
        Collections.sort(recipe.getComments(), Comparator.comparing(Comment::getPublishDate));

        commentsString = recipe.getComments().stream()
                .map(s-> s.toString())
                .collect(Collectors.toList());

        adapter = new ArrayAdapter<>(
                this,
                R.layout.list_black_text,
                commentsString);

        comments_LST_comments.setAdapter(adapter);

        comments_BTN_send.setOnClickListener(v -> {
            if(comments_EDITTEXT_newComment.getText().length() > 0) {
                Comment newComment = new Comment()
                        .setText(comments_EDITTEXT_newComment.getText().toString())
                        .setUsername(user.getUsername())
                        .setRecipeObjectId(recipe.getObjectId());

                commentsString.add(newComment.toString());

                adapter.notifyDataSetChanged();

                recipeData.createComment(newComment, recipe);

                comments_EDITTEXT_newComment.setText("");
            }
        });
    }
}