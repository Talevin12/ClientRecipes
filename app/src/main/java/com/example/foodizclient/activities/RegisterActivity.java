package com.example.foodizclient.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.foodizclient.R;
import com.example.foodizclient.authentication.AuthenticationManager;
import com.example.foodizclient.boundaries.UserRole;
import com.example.foodizclient.callbacks.CallBack_OpenPageProtocol;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private ShapeableImageView register_IMG_background;
    private TextInputEditText register_EDIT_email;
    private TextInputEditText register_EDIT_Username;
    private TextInputEditText register_EDIT_avatar;
    private MaterialButton register_BTN_submit;

    AuthenticationManager authenticationManager;

    CallBack_OpenPageProtocol callBack_userProtocol = (recipe, user) -> openLogin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        authenticationManager = new AuthenticationManager(this);
        authenticationManager.setCallBack_userProtocol(callBack_userProtocol);

        findViews();

        Glide
                .with(RegisterActivity.this)
                .load("https://img.freepik.com/free-vector/illustration-hands-holding-junk-food_53876-26715.jpg?w=740&t=st=1669735291~exp=1669735891~hmac=12260f0e29ab396c29a11d25b0ca5efb8700a1dc7f96e69261923afbf1245226.jpg")
                .into(register_IMG_background);

        initViews();
    }

    private void findViews() {
        register_IMG_background = findViewById(R.id.register_IMG_background);
        register_EDIT_email = findViewById(R.id.register_EDIT_email);
        register_EDIT_Username = findViewById(R.id.register_EDIT_Username);
//        register_SPINNER_role = findViewById(R.id.register_SPINNER_role);
        register_EDIT_avatar = findViewById(R.id.register_EDIT_avatar);
        register_BTN_submit = findViewById(R.id.register_BTN_submit);
    }

    private void initViews() {
        register_BTN_submit.setOnClickListener(view -> submitClicked(register_EDIT_email.getText().toString(),
                                                                    register_EDIT_Username.getText().toString(),
                                                                    register_EDIT_avatar.getText().toString()));
    }

    private void submitClicked(String email, String username, String avatar) {
        authenticationManager.checkRegisterUserInput(email, UserRole.MINIAPP_USER.name(), username, avatar, this);
    }

    private void openLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}