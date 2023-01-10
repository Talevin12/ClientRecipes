package com.example.foodizclient.boundaries;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class ObjectId implements Serializable {
    @SerializedName("superapp")
    private String superapp;

    @SerializedName("internalObjectId")
    private String internalObjectId;

    public String getSuperapp() {
        return superapp;
    }

    public ObjectId setSuperapp(String superapp) {
        this.superapp = superapp;
        return this;
    }

    public String getInternalObjectId() {
        return internalObjectId;
    }

    public ObjectId setInternalObjectId(String internalObjectId) {
        this.internalObjectId = internalObjectId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObjectId)) return false;
        ObjectId objectId = (ObjectId) o;
        return getSuperapp().equals(objectId.getSuperapp()) && getInternalObjectId().equals(objectId.getInternalObjectId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSuperapp(), getInternalObjectId());
    }
}
