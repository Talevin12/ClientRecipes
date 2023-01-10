package com.example.foodizclient.authentication;

import android.content.Context;
import android.widget.Toast;

import com.example.foodizclient.authentication.apiAssets.APIClient;
import com.example.foodizclient.authentication.apiAssets.APIInterface;
import com.example.foodizclient.boundaries.MiniAppCommandBoundary;
import com.example.foodizclient.callbacks.DataCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommandManager {
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

    DataCallback dataCallback;
    public void setDataInterface(DataCallback dataCallback) {
        this.dataCallback = dataCallback;
    }

    private Context context;

    public CommandManager(Context context) {
        this.context = context;
    }

    public void invokeCommand(MiniAppCommandBoundary command) {
        Call<Object> invokeCommandCall = apiInterface.invokeCommand(command.getCommandId().getMiniapp(), command);

        Callback<Object> invokeCommandCallback = new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object object = response.body();

                dataCallback.setObject(object);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(context, "Something Went wrong", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        };

        invokeCommandCall.enqueue(invokeCommandCallback);
    }
}
