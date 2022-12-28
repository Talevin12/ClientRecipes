package com.example.foodizclient.boundaries;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserId implements Serializable {
    private static final long serialVersionUID = 2L;

    @SerializedName("superapp")
    private String superapp;

    @SerializedName("email")
    private String email;
}
