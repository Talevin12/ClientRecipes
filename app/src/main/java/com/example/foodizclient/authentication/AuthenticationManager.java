package com.example.foodizclient.authentication;

import android.content.Context;
import android.widget.Toast;

import com.example.foodizclient.authentication.apiAssets.APIClient;
import com.example.foodizclient.authentication.apiAssets.APIInterface;
import com.example.foodizclient.boundaries.NewUserBoundary;
import com.example.foodizclient.boundaries.UserBoundary;
import com.example.foodizclient.boundaries.UserRole;
import com.example.foodizclient.callbacks.CallBack_OpenPageProtocol;

import org.json.JSONException;
import org.json.JSONObject;

import kotlin.io.TextStreamsKt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationManager {
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

    private CallBack_OpenPageProtocol callBack_userProtocol;

    private Context context;

    public AuthenticationManager(Context context) {
        this.context = context;
    }

    public void setCallBack_userProtocol(CallBack_OpenPageProtocol callBack_userProtocol) {
        this.callBack_userProtocol = callBack_userProtocol;
    }

    public void checkLoginUserInput(String userSuperApp, String userEmail, Context context) {
        Call<UserBoundary> loginCall = apiInterface.login(userSuperApp, userEmail);

        Callback<UserBoundary> loginCallback = new Callback<UserBoundary>() {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                UserBoundary user = response.body();

                if(user == null) {
                    Toast.makeText(context, "Wrong Credentials! Try again", Toast.LENGTH_LONG).show();
                }
                else {
                    callBack_userProtocol.openPage(null, user);
                }
            }

            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {
                Toast.makeText(context, "Something Went wrong", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        };

        loginCall.enqueue(loginCallback);
    }

    public void checkRegisterUserInput(String email, String role, String username, String avatar, Context context) {
        NewUserBoundary newUser = new NewUserBoundary()
                .setEmail(email)
                .setRole(UserRole.valueOf(role))
                .setUsername(username)
                .setAvatar(avatar);

        Call<UserBoundary> loginCall = apiInterface.register(newUser);

        Callback<UserBoundary> registerCallback = new Callback<UserBoundary>() {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                UserBoundary user = response.body();

                if(user == null) {
                    try {
                        JSONObject jsonObj = new JSONObject(TextStreamsKt.readText(response.errorBody().charStream()));
                        String message = jsonObj.getString("message");
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    callBack_userProtocol.openPage(null, user);
                }
            }

            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {
                Toast.makeText(context, "Something Went wrong", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        };

        loginCall.enqueue(registerCallback);
    }
}
