package com.example.foodizclient.boundaries;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Map;

public class ObjectBoundary {
    @SerializedName("objectId")
    private ObjectId objectId;

    @SerializedName("type")
    private String type;

    @SerializedName("alias")
    private String alias;

    @SerializedName("active")
    private Boolean active;

    @SerializedName("creationTimestamp")
    private Date creationTimestamp;

    @SerializedName("createdBy")
    private Map<String, UserId> createdBy;

    @SerializedName("objectDetails")
    private Map<String, Object> objectDetails;

    public ObjectId getObjectId() {
        return objectId;
    }

    public ObjectBoundary setObjectId(ObjectId objectId) {
        this.objectId = objectId;
        return this;
    }

    public String getType() {
        return type;
    }

    public ObjectBoundary setType(String type) {
        this.type = type;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public ObjectBoundary setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public ObjectBoundary setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public ObjectBoundary setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
        return this;
    }

    public Map<String, UserId> getCreatedBy() {
        return createdBy;
    }

    public ObjectBoundary setCreatedBy(Map<String, UserId> createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Map<String, Object> getObjectDetails() {
        return objectDetails;
    }

    public ObjectBoundary setObjectDetails(Map<String, Object> objectDetails) {
        this.objectDetails = objectDetails;
        return this;
    }

    @Override
    public String toString() {
        return "ObjectBoundary{" +
                "objectId=" + objectId +
                ", type='" + type + '\'' +
                ", alias='" + alias + '\'' +
                ", active=" + active +
                ", creationTimestamp=" + creationTimestamp +
                ", createdBy=" + createdBy +
                ", objectDetails=" + objectDetails +
                '}';
    }

    public ObjectBoundary fromJson(String s) {
        return new Gson().fromJson(s, ObjectBoundary.class);
    }
}
