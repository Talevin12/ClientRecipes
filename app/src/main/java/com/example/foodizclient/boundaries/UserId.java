package com.example.foodizclient.boundaries;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class UserId implements Serializable {
    private static final long serialVersionUID = 2L;

    @SerializedName("superapp")
    private String superapp;

    @SerializedName("email")
    private String email;

    public String getSuperapp() {
        return superapp;
    }

    public UserId setSuperapp(String superapp) {
        this.superapp = superapp;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserId setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId)) return false;
        UserId userId = (UserId) o;
        return getSuperapp().equals(userId.getSuperapp()) && getEmail().equals(userId.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSuperapp(), getEmail());
    }
}
