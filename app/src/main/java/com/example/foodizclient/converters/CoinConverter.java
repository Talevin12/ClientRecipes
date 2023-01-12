package com.example.foodizclient.converters;

import android.content.Intent;

import com.example.foodizclient.boundaries.ObjectBoundary;
import com.example.foodizclient.boundaries.UserId;

import java.util.HashMap;

public class CoinConverter {
    public ObjectBoundary toObjectBoundary(UserId userId, int coinsAmount) {
        ObjectBoundary objectBoundary = new ObjectBoundary();

        objectBoundary.setActive(true);

        HashMap<String, UserId> userIdMap = new HashMap<>();
        userIdMap.put("userId", userId);
        objectBoundary.setCreatedBy(userIdMap);

        objectBoundary.setType("coins");

        HashMap<String, Object> details = new HashMap<>();
        details.put("amount", coinsAmount);
        objectBoundary.setObjectDetails(details);

        objectBoundary.setAlias("Mimir");

        return objectBoundary;
    }

    public int toCoins(ObjectBoundary objectBoundary) {
        int coins = (int) (double) objectBoundary.getObjectDetails().get("amount");
        return coins;
    }

}
