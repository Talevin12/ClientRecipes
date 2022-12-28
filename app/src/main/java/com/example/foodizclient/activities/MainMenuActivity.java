package com.example.foodizclient.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.foodizclient.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class MainMenuActivity extends AppCompatActivity {
    public static final String KEY_USERSUPERAPP = "KEY_USERSUPERAPP";

    private ShapeableImageView menu_IMG_background;
    private MaterialTextView menu_LBL_hiuser;
    private MaterialButton menu_BTN_comapring;
    private MaterialButton menu_BTN_recipesandforum;
    private MaterialButton menu_BTN_cookingandbakingcourses;
    private MaterialButton menu_BTN_orderhomemademeals;
    private MaterialButton menu_BTN_privatechefevents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        findViews();

        Glide
                .with(MainMenuActivity.this)
                .load("https://t4.ftcdn.net/jpg/03/78/97/59/360_F_378975954_G39M4ptXAjxKy80gbBIEo0wqBkk89gBF.jpg")
                .into(menu_IMG_background);

        Intent previousIntent = getIntent();
        String user = previousIntent.getExtras().getString(KEY_USERSUPERAPP);

        menu_LBL_hiuser.setText("Hi " +user);
    }

    private void findViews() {
        menu_IMG_background = findViewById(R.id.menu_IMG_background);
        menu_LBL_hiuser = findViewById(R.id.menu_LBL_hiuser);
        menu_BTN_comapring = findViewById(R.id.menu_BTN_comapring);
        menu_BTN_recipesandforum = findViewById(R.id.menu_BTN_recipesandforum);
        menu_BTN_cookingandbakingcourses = findViewById(R.id.menu_BTN_cookingandbakingcourses);
        menu_BTN_orderhomemademeals = findViewById(R.id.menu_BTN_orderhomemademeals);
        menu_BTN_privatechefevents = findViewById(R.id.menu_BTN_privatechefevents);
    }
}