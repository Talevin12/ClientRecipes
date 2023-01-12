package com.example.foodizclient;

import android.content.Context;

import com.example.foodizclient.authentication.CommandManager;
import com.example.foodizclient.authentication.DataManager;
import com.example.foodizclient.authentication.UserManager;
import com.example.foodizclient.boundaries.ObjectBoundary;
import com.example.foodizclient.boundaries.UserId;
import com.example.foodizclient.callbacks.DataCallback;
import com.example.foodizclient.converters.CoinConverter;

public class UserData {
    private Context context;

    private UserId userId;
    private DataManager dataManager;
    private CoinConverter coinConverter;

    private DataCallback dataCallback;

    public UserData(Context context, UserId userId, DataCallback dataCallback) {
        this.context = context;

        dataManager = new DataManager(context);

        this.dataCallback = dataCallback;

        dataManager.setDataInterface(dataCallback);

        coinConverter = new CoinConverter();

        this.userId = userId;
    }

    public void createCoinz() {
        dataManager.createObject(coinConverter.toObjectBoundary(userId, 0));
    }

    public void getCoinz() {
        dataManager.getObjectsByTypeAndCreatedBy("coins", userId.getSuperapp(), userId.getEmail());
    }

    public CoinConverter getCoinConverter() {
        return coinConverter;
    }
}
