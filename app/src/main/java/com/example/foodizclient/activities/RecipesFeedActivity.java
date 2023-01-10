package com.example.foodizclient.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodizclient.R;
import com.example.foodizclient.RecipeData;
import com.example.foodizclient.adapters.Adapter_RecipesFeed;
import com.example.foodizclient.authentication.UserManager;
import com.example.foodizclient.boundaries.Comment;
import com.example.foodizclient.boundaries.ObjectBoundary;
import com.example.foodizclient.boundaries.ObjectId;
import com.example.foodizclient.boundaries.Recipe;
import com.example.foodizclient.boundaries.UserBoundary;
import com.example.foodizclient.boundaries.UserId;
import com.example.foodizclient.boundaries.UserRole;
import com.example.foodizclient.callbacks.CallBack_OpenPageProtocol;
import com.example.foodizclient.callbacks.DataCallback;
import com.example.foodizclient.callbacks.UserCallback;
import com.example.foodizclient.converters.CommentConverter;
import com.example.foodizclient.converters.RecipeConverter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class RecipesFeedActivity extends AppCompatActivity {

    public static final String KEY_USER = "KEY_USER";
    private RecyclerView recipesFeed_LST_feed;
    private ExtendedFloatingActionButton recipesFeed_BTN_addRecipe;

    private Adapter_RecipesFeed adapter_recipesFeed;

    private ArrayList<Recipe> recipes = new ArrayList<>();
    private ArrayList<Boolean> recipesIsLike = new ArrayList<>();

    private UserBoundary user;

    CallBack_OpenPageProtocol callBack_openCommentsPageProtocol = (recipe, user) -> openCommentsPage(recipe, user);

    CallBack_OpenPageProtocol setCallBack_openInfoPageProtocol = (recipe, user) -> openInfoPage(recipe);

    private RecipeConverter recipeConverter = new RecipeConverter();
    private CommentConverter commentConverter = new CommentConverter();

    UserManager userManager = new UserManager(this);

    DataCallback dataCallback = new DataCallback() {
        @Override
        public void setObjectBoundary(ObjectBoundary object) {}

        @Override
        public void setListOfObjects(ArrayList<ObjectBoundary> objects) {
            recipes.clear();
            recipesIsLike.clear();
            if(objects.size() > 0) {
                for (ObjectBoundary objectBoundary : objects) {
                    Recipe recipe = recipeConverter.toRecipe(objectBoundary);

                    recipes.add(recipe);

                    ArrayList<UserId> lst = new Gson().fromJson(objectBoundary.getObjectDetails().get("likes").toString(),
                            new TypeToken<ArrayList<UserId>>() {}.getType());

                    recipesIsLike.add(lst.contains(user.getUserId()));

                    recipeCommentsData.getRecipeComments(recipe);
                }
            } else {
                initViews();
                initRecyclerView();
            }
        }

        @Override
        public void setObject(Object object) {}
    };

    DataCallback dataCommetsCallback = new DataCallback() {
        @Override
        public void setObjectBoundary(ObjectBoundary object) {}

        @Override
        public void setListOfObjects(ArrayList<ObjectBoundary> objects) {
            if(objects.size() > 0) {
                ObjectId recipeObjId = new Gson().fromJson(objects.get(0).getObjectDetails().get("recipeObjectId").toString(), new TypeToken<ObjectId>() {
                }.getType());

                int i;
                for (i = 0; i < recipes.size(); i++) {
                    if (recipes.get(i).getObjectId().equals(recipeObjId)) {
                        break;
                    }
                }

                for (ObjectBoundary objectBoundary : objects) {
                    Comment comment = commentConverter.toComment(objectBoundary);

                    recipes.get(i).getComments().add(comment);
                }
            }

            initViews();
            initRecyclerView();
        }

        @Override
        public void setObject(Object object) {}
    };

    UserCallback userCallback = new UserCallback() {
        @Override
        public void updateUserFlag() {
            recipeData.getAllRecipe();
        }
    };

    private RecipeData recipeData;
    private RecipeData recipeCommentsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_feed);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            user = getIntent().getSerializableExtra(KEY_USER, UserBoundary.class);
        } else {
            user = (UserBoundary) getIntent().getSerializableExtra(KEY_USER);
        }

        userManager.setUserCallback(userCallback);

        recipeData = new RecipeData(this, user.getUserId(), dataCallback);
        recipeCommentsData = new RecipeData(this, user.getUserId(), dataCommetsCallback);

        findViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        userManager.updateUser(user.setRole(UserRole.MINIAPP_USER));
    }

    private void findViews() {
        recipesFeed_LST_feed = findViewById(R.id.recipesFeed_LST_feed);
        recipesFeed_BTN_addRecipe = findViewById(R.id.recipesFeed_BTN_addRecipe);
    }

    private void initViews() {
        recipesFeed_BTN_addRecipe.setOnClickListener(v -> openAddPostPage());
    }

    private void initRecyclerView() {
        adapter_recipesFeed = new Adapter_RecipesFeed(this, user, recipes, recipesIsLike, recipeData);
        adapter_recipesFeed.setCallBack_openCommentsPageProtocol(callBack_openCommentsPageProtocol);
        adapter_recipesFeed.setCallBack_openInfoPageProtocol(setCallBack_openInfoPageProtocol);
        recipesFeed_LST_feed.setLayoutManager(new LinearLayoutManager(this));

        if(recipesFeed_LST_feed.getAdapter() == null)
            recipesFeed_LST_feed.setAdapter(adapter_recipesFeed);
        else
            recipesFeed_LST_feed.swapAdapter(adapter_recipesFeed, true);

    }

    private void openCommentsPage(Recipe recipe, UserBoundary user) {
        Intent intent = new Intent(this, RecipeCommentsActivity.class);
        intent.putExtra(RecipeCommentsActivity.KEY_RECIPE, recipe);
        intent.putExtra(RecipeCommentsActivity.KEY_USER, user);
        startActivity(intent);
    }

    private void openInfoPage(Recipe recipe) {
        Intent intent = new Intent(this, RecipeInformationActivity.class);
        intent.putExtra(RecipeInformationActivity.KEY_RECIPE, recipe);
        startActivity(intent);
    }

    private void openAddPostPage() {
        Intent intent = new Intent(this, AddPostActivity.class);
        intent.putExtra(AddPostActivity.KEY_USER, user);
        startActivity(intent);
    }
}
