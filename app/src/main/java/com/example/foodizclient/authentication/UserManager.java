package com.example.foodizclient.authentication;

import android.content.Context;
import android.widget.Toast;

import com.example.foodizclient.authentication.apiAssets.APIClient;
import com.example.foodizclient.authentication.apiAssets.APIInterface;
import com.example.foodizclient.boundaries.UserBoundary;
import com.example.foodizclient.callbacks.UserCallback;

import org.json.JSONException;
import org.json.JSONObject;

import kotlin.io.TextStreamsKt;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserManager {
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

    private Context context;

    UserCallback userCallback;

    public void setUserCallback(UserCallback userCallback) {
        this.userCallback = userCallback;
    }

    public UserManager(Context context) {
        this.context = context;
    }

    public void updateUser(UserBoundary user) {
        Call<ResponseBody> udpateUserCall = apiInterface.updateUser(user.getUserId().getSuperapp(), user.getUserId().getEmail(), user);

        Callback<ResponseBody> updateUserCallback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response == null) {
                    try {
                        JSONObject jsonObj = new JSONObject(TextStreamsKt.readText(response.errorBody().charStream()));
                        String message = jsonObj.getString("message");
                        System.err.println(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    userCallback.updateUserFlag();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Something Went wrong", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        };

        udpateUserCall.enqueue(updateUserCallback);
    }
}
