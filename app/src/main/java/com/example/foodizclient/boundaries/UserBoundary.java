package com.example.foodizclient.boundaries;

import com.google.gson.annotations.SerializedName;

public class UserBoundary {
    @SerializedName("userId")
    private UserId userId;

    @SerializedName("role")
    private UserRole role;

    @SerializedName("username")
    private String username;

    @SerializedName("avatar")
    private String avatar;

    @Override
    public String toString() {
        return "UserBoundary{" +
                "userId=" + userId +
                ", role=" + role +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
