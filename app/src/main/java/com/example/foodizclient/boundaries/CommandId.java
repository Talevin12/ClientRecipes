package com.example.foodizclient.boundaries;

import com.google.gson.annotations.SerializedName;

public class CommandId {
    @SerializedName("superapp")
    private String superapp;

    @SerializedName("miniapp")
    private String miniapp;

    @SerializedName("internalCommandId")
    private String internalCommandId;

    public String getSuperapp() {
        return superapp;
    }

    public CommandId setSuperapp(String superapp) {
        this.superapp = superapp;
        return this;
    }

    public String getMiniapp() {
        return miniapp;
    }

    public CommandId setMiniapp(String miniapp) {
        this.miniapp = miniapp;
        return this;
    }

    public String getInternalCommandId() {
        return internalCommandId;
    }

    public CommandId setInternalCommandId(String internalCommandId) {
        this.internalCommandId = internalCommandId;
        return this;
    }
}
