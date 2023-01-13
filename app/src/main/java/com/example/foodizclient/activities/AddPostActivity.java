package com.example.foodizclient.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodizclient.R;
import com.example.foodizclient.RecipeData;
import com.example.foodizclient.adapters.Adapter_AddIngredients;
import com.example.foodizclient.adapters.Adapter_AddInstructions;
import com.example.foodizclient.authentication.UserManager;
import com.example.foodizclient.boundaries.Ingredient;
import com.example.foodizclient.boundaries.ObjectBoundary;
import com.example.foodizclient.boundaries.Recipe;
import com.example.foodizclient.boundaries.UserBoundary;
import com.example.foodizclient.boundaries.UserRole;
import com.example.foodizclient.callbacks.DataCallback;
import com.example.foodizclient.callbacks.UserCallback;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class AddPostActivity extends AppCompatActivity {

    public static String KEY_USER = "KEY_USER";

    private AppCompatEditText   addRecipe_EDITTEXT_name;
    private AppCompatEditText   addRecipe_EDITTEXT_description;
    private ImageView           addRecipe_IMG_image;
    private RecyclerView        addRecipe_LST_ingredients;
    private RecyclerView        addRecipe_LST_instructions;
    private MaterialButton      addRecipe_BTN_submit;

    private UserBoundary user;
    private UserManager userManager = new UserManager(this);

    Recipe newRecipe = new Recipe();

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;

    Adapter_AddIngredients adapter_addIngredients;
    Adapter_AddInstructions adapter_addInstructions;

    DataCallback dataCallback = new DataCallback() {
        @Override
        public void setObjectBoundary(ObjectBoundary object) {
            finish();
        }

        @Override
        public void setListOfObjects(ArrayList<ObjectBoundary> objects) {

        }

        @Override
        public void setObject(Object object) {

        }
    };

    UserCallback userCallback = this::initViews;

    RecipeData recipeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            user = getIntent().getSerializableExtra(KEY_USER, UserBoundary.class);
        } else {
            user = (UserBoundary) getIntent().getSerializableExtra(KEY_USER);
        }

        recipeData = new RecipeData(this, user.getUserId(), dataCallback);

        findViews();

        userManager.setUserCallback(userCallback);
        userManager.updateUser(user.setRole(UserRole.SUPERAPP_USER));
    }

    private void findViews() {
        addRecipe_EDITTEXT_name = findViewById(R.id.addRecipe_EDITTEXT_name);
        addRecipe_EDITTEXT_description = findViewById(R.id.addRecipe_EDITTEXT_description);
        addRecipe_IMG_image = findViewById(R.id.addRecipe_IMG_image);
        addRecipe_LST_ingredients = findViewById(R.id.addRecipe_LST_ingredients);
        addRecipe_LST_instructions = findViewById(R.id.addRecipe_LST_instructions);
        addRecipe_BTN_submit = findViewById(R.id.addRecipe_BTN_submit);
    }

    private void initViews() {
        addRecipe_IMG_image.setOnClickListener(v -> imageChooser());

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        adapter_addIngredients = new Adapter_AddIngredients(this, ingredients);
        adapter_addIngredients.addNewIngredient();
        addRecipe_LST_ingredients.setLayoutManager(new LinearLayoutManager(this));
        addRecipe_LST_ingredients.setAdapter(adapter_addIngredients);

        ArrayList<String> instructions = new ArrayList<>();
        adapter_addInstructions = new Adapter_AddInstructions(this, instructions);
        adapter_addInstructions.addNewInstruction();
        addRecipe_LST_instructions.setLayoutManager(new LinearLayoutManager(this));
        addRecipe_LST_instructions.setAdapter(adapter_addInstructions);

        addRecipe_BTN_submit.setOnClickListener(    v -> {
            if(!addRecipe_EDITTEXT_name.getText().toString().equals("") &&
            !addRecipe_EDITTEXT_description.getText().toString().equals("") &&
            newRecipe.getImage() != null &&
            adapter_addIngredients.getItemCount() != 1 &&
            adapter_addInstructions.getItemCount() != 1) {

                newRecipe
                        .setName(addRecipe_EDITTEXT_name.getText().toString())
                        .setUsername(user.getUsername())
                        .setDescription(addRecipe_EDITTEXT_description.getText().toString())
                        .setIngredients(adapter_addIngredients.getIngredients())
                        .setInstructions(adapter_addInstructions.getInstructions())
                        .setUser(user.getUserId());

                recipeData.CreateRecipe(newRecipe);
            }
        });
    }

    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();

//                FirebaseApp.initializeApp(this);
                StorageReference storageRef = FirebaseStorage.getInstance().getReference();

                StorageReference riversRef = storageRef.child("images/"+selectedImageUri.getLastPathSegment());
                UploadTask uploadTask = riversRef.putFile(selectedImageUri);

                uploadTask.addOnFailureListener(exception ->
                        addRecipe_IMG_image.setImageResource(R.drawable.choose_img)).addOnSuccessListener(taskSnapshot ->
                        storageRef.child("images/"+selectedImageUri.getLastPathSegment()).getDownloadUrl().addOnSuccessListener(uri -> {
                            // Got the download URL for 'users/me/profile.png'
                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(uri1 -> {
                                newRecipe.setImage(uri1.toString());
                                System.err.println(uri1);
                            });
                        }).addOnFailureListener(exception -> {
                            // Handle any errors
                        }));


                // update the preview image in the layout
                addRecipe_IMG_image.setImageURI(selectedImageUri);
            }
        }
    }
}