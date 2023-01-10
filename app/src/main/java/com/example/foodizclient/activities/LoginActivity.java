package com.example.foodizclient.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.foodizclient.R;
import com.example.foodizclient.authentication.AuthenticationManager;
import com.example.foodizclient.boundaries.UserBoundary;
import com.example.foodizclient.callbacks.CallBack_OpenPageProtocol;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private ShapeableImageView login_IMG_grocery;
    private TextInputEditText login_LBL_usersuperapp;
    private TextInputEditText login_TEXT_useremail;
    private MaterialButton login_BTN_gobutton;
    private MaterialTextView login_LBL_newToFoodiz;


    AuthenticationManager authenticationManager;

    CallBack_OpenPageProtocol callBack_userProtocol = (recipe, user) -> openRecipesFeed(user);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authenticationManager = new AuthenticationManager(this);
        authenticationManager.setCallBack_userProtocol(callBack_userProtocol);

        findViews();
        initViews();

        Glide
                .with(LoginActivity.this)
                .load("https://img.freepik.com/free-vector/illustration-hands-holding-junk-food_53876-26715.jpg?w=740&t=st=1669735291~exp=1669735891~hmac=12260f0e29ab396c29a11d25b0ca5efb8700a1dc7f96e69261923afbf1245226.jpg")
                .into(login_IMG_grocery);
    }

    private void findViews() {
        login_IMG_grocery = findViewById(R.id.login_IMG_grocery);
        login_LBL_usersuperapp = findViewById(R.id.login_LBL_usersuperapp);
        login_TEXT_useremail = findViewById(R.id.login_TEXT_useremail);
        login_BTN_gobutton = findViewById(R.id.login_BTN_submit);
        login_LBL_newToFoodiz = findViewById(R.id.login_LBL_newToFoodiz);
    }

    private void initViews() {
        login_BTN_gobutton.setOnClickListener(view -> {
            try {
                submitClicked(login_LBL_usersuperapp.getText().toString(),
                        login_TEXT_useremail.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        login_LBL_newToFoodiz.setOnClickListener(view -> newToFoodizClicked());
    }

    private void submitClicked(String userSuperApp, String userEmail) throws IOException {
        authenticationManager.checkLoginUserInput(userSuperApp, userEmail, this);
    }

    private void newToFoodizClicked() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void openRecipesFeed(UserBoundary user) {
        Intent intent = new Intent(this, RecipesFeedActivity.class);
        intent.putExtra(RecipesFeedActivity.KEY_USER, user);
        startActivity(intent);
        finish();
    }
}