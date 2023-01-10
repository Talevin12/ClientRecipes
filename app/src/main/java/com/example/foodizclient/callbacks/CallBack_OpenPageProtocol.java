package com.example.foodizclient.callbacks;

import com.example.foodizclient.boundaries.Recipe;
import com.example.foodizclient.boundaries.UserBoundary;

public interface CallBack_OpenPageProtocol {
    void openPage(Recipe recipe, UserBoundary user);
}
