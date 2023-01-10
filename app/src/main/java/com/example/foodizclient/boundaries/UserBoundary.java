package com.example.foodizclient.boundaries;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserBoundary implements Serializable {
    @SerializedName("userId")
    private UserId userId;

    @SerializedName("role")
    private UserRole role;

    @SerializedName("username")
    private String username;

    @SerializedName("avatar")
    private String avatar;

    public UserId getUserId() {
        return userId;
    }

    public UserBoundary setUserId(UserId userId) {
        this.userId = userId;
        return this;
    }

    public UserRole getRole() {
        return role;
    }

    public UserBoundary setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserBoundary setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserBoundary setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

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
