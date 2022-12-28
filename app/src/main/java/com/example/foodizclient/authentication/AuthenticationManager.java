package com.example.foodizclient.authentication;

import android.content.Context;
import android.widget.Toast;

import com.example.foodizclient.CallBack_OpenPageProtocol;
import com.example.foodizclient.authentication.apiAssets.APIClient;
import com.example.foodizclient.authentication.apiAssets.APIInterface;
import com.example.foodizclient.boundaries.UserBoundary;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationManager {
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

    private CallBack_OpenPageProtocol callBack_userProtocol;

    public void setCallBack_userProtocol(CallBack_OpenPageProtocol callBack_userProtocol) {
        this.callBack_userProtocol = callBack_userProtocol;
    }

    public void checkUserInput(String userSuperApp, String userEmail, Context context) {
        Call<UserBoundary> loginCall = apiInterface.login(userSuperApp, userEmail);

        Callback<UserBoundary> loginCallback = new Callback<UserBoundary>() {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                UserBoundary user = response.body();

                if(user == null) {
                    Toast.makeText(context, "Wrong Credentials! Try again", Toast.LENGTH_LONG).show();
                }
                else {
                    callBack_userProtocol.openPage(userSuperApp);
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
}
