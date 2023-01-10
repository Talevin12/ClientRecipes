package com.example.foodizclient.authentication;

import android.content.Context;
import android.widget.Toast;

import com.example.foodizclient.authentication.apiAssets.APIClient;
import com.example.foodizclient.authentication.apiAssets.APIInterface;
import com.example.foodizclient.boundaries.ObjectBoundary;
import com.example.foodizclient.boundaries.ObjectId;
import com.example.foodizclient.callbacks.DataCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kotlin.io.TextStreamsKt;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager {
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
    DataCallback dataCallback;
    private Context context;

    public void setDataInterface(DataCallback dataCallback) {
        this.dataCallback = dataCallback;
    }

    public DataManager(Context context) {
        this.context = context;
    }

    public void createObject(ObjectBoundary newBoundary) {

        Call<ObjectBoundary> createObjectCall = apiInterface.createObject(newBoundary);

        Callback<ObjectBoundary> createCallback = new Callback<ObjectBoundary>() {

            @Override
            public void onResponse(Call<ObjectBoundary> call, Response<ObjectBoundary> response) {
                ObjectBoundary object = response.body();

                dataCallback.setObjectBoundary(object);
            }

            @Override
            public void onFailure(Call<ObjectBoundary> call, Throwable t) {
                Toast.makeText(context, "Something Went wrong", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        };

        createObjectCall.enqueue(createCallback);
    }

    public void getObjectsByType(String type, String superapp, String email) {
        Call<ArrayList<ObjectBoundary>> getObjectsByTypeCall = apiInterface.getObjectsByType(type, superapp, email);

        Callback<ArrayList<ObjectBoundary>> getObjectsCallback = new Callback<ArrayList<ObjectBoundary>>() {

            @Override
            public void onResponse(Call<ArrayList<ObjectBoundary>> call, Response<ArrayList<ObjectBoundary>> response) {
                ArrayList<ObjectBoundary> objects = response.body();

                dataCallback.setListOfObjects(objects);
            }

            @Override
            public void onFailure(Call<ArrayList<ObjectBoundary>> call, Throwable t) {
                Toast.makeText(context, "Something Went wrong", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        };

        getObjectsByTypeCall.enqueue(getObjectsCallback);
    }

    public void getObjectByTypeAndCreatedBy(String type, String superapp, String email) {
        Call<ObjectBoundary> getObjectByTypeAndCreatedbyCall = apiInterface.getObjectsByTypeAndCreatedBy(type, superapp, email);

        Callback<ObjectBoundary> getObjectCallback = new Callback<ObjectBoundary>() {

            @Override
            public void onResponse(Call<ObjectBoundary> call, Response<ObjectBoundary> response) {
                ObjectBoundary object = response.body();

                dataCallback.setObjectBoundary(object);
            }

            @Override
            public void onFailure(Call<ObjectBoundary> call, Throwable t) {
                Toast.makeText(context, "Something Went wrong", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        };

        getObjectByTypeAndCreatedbyCall.enqueue(getObjectCallback);
    }

    public void updateObject(ObjectBoundary object, String superapp, String email) {
        Call<ResponseBody> updateObjectCall = apiInterface.updateObject(object.getObjectId().getSuperapp(),
                                                                            object.getObjectId().getInternalObjectId(),
                                                                            superapp, email,
                                                                            object);

        Callback<ResponseBody> updateObjectCallback = new Callback<ResponseBody>() {
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
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Something Went wrong", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        };

        updateObjectCall.enqueue(updateObjectCallback);
    }

    public void bindObjects(String superapp, String internalObjectId, String userSuperapp, String userEmail, ObjectId superAppObjectId) {
        Call<ResponseBody> bindObjectsCall = apiInterface.bindObjects(superapp, internalObjectId,
                                                                        userSuperapp, userEmail,
                                                                        superAppObjectId);

        Callback<ResponseBody> bindObjectsCallback = new Callback<ResponseBody>() {
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
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Something Went wrong", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        };

        bindObjectsCall.enqueue(bindObjectsCallback);
    }

    public void getAllChildrenObjects(String superapp, String internalObjectId, String userSuperapp, String userEmail) {
        Call<ArrayList<ObjectBoundary>> getAllChildrenObjectsCall = apiInterface.getAllChildrenObjects(superapp, internalObjectId,
                                                                                                        userSuperapp, userEmail);

        Callback<ArrayList<ObjectBoundary>> getAllChildrenObjectsCallback = new Callback<ArrayList<ObjectBoundary>>() {

            @Override
            public void onResponse(Call<ArrayList<ObjectBoundary>> call, Response<ArrayList<ObjectBoundary>> response) {
                ArrayList<ObjectBoundary> objects = response.body();

                dataCallback.setListOfObjects(objects);
            }

            @Override
            public void onFailure(Call<ArrayList<ObjectBoundary>> call, Throwable t) {
                Toast.makeText(context, "Something Went wrong", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        };

        getAllChildrenObjectsCall.enqueue(getAllChildrenObjectsCallback);
    }
}
