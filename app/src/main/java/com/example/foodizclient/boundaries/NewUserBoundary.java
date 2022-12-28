package com.example.foodizclient.boundaries;

import com.google.gson.annotations.SerializedName;

public class NewUserBoundary {
    @SerializedName("email")
    private String email;

    @SerializedName("role")
    private UserRole role;

    @SerializedName("username")
    private String username;

    @SerializedName("avatar")
    private String avatar;

    public NewUserBoundary setEmail(String email) {
        this.email = email;
        return this;
    }

    public NewUserBoundary setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public NewUserBoundary setUsername(String username) {
        this.username = username;
        return this;
    }

    public NewUserBoundary setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    @Override
    public String toString() {
        return "UserBoundary{" +
                "email=" + email +
                ", role=" + role +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
