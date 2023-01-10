package com.example.foodizclient.boundaries;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Map;

public class MiniAppCommandBoundary {
    @SerializedName("commandId")
    private CommandId commandId;

    @SerializedName("command")
    private String command;

    @SerializedName("targetObject")
    private Map<String, ObjectId> targetObject;

    @SerializedName("invocationTimestamp")
    private Date invocationTimestamp;

    @SerializedName("invokedBy")
    private Map<String, UserId> invokedBy;

    @SerializedName("commandAttributes")
    private Map<String,Object> commandAttributes;

    public CommandId getCommandId() {
        return commandId;
    }

    public MiniAppCommandBoundary setCommandId(CommandId commandId) {
        this.commandId = commandId;
        return this;
    }

    public String getCommand() {
        return command;
    }

    public MiniAppCommandBoundary setCommand(String command) {
        this.command = command;
        return this;
    }

    public Map<String, ObjectId> getTargetObject() {
        return targetObject;
    }

    public MiniAppCommandBoundary setTargetObject(Map<String, ObjectId> targetObject) {
        this.targetObject = targetObject;
        return this;
    }

    public Date getInvocationTimestamp() {
        return invocationTimestamp;
    }

    public MiniAppCommandBoundary setInvocationTimestamp(Date invocationTimestamp) {
        this.invocationTimestamp = invocationTimestamp;
        return this;
    }

    public Map<String, UserId> getInvokedBy() {
        return invokedBy;
    }

    public MiniAppCommandBoundary setInvokedBy(Map<String, UserId> invokedBy) {
        this.invokedBy = invokedBy;
        return this;
    }

    public Map<String, Object> getCommandAttributes() {
        return commandAttributes;
    }

    public MiniAppCommandBoundary setCommandAttributes(Map<String, Object> commandAttributes) {
        this.commandAttributes = commandAttributes;
        return this;
    }

    @Override
    public String toString() {
        return "MiniAppCommandBoundary{" +
                "commandId=" + commandId +
                ", command='" + command + '\'' +
                ", targetObject=" + targetObject +
                ", invocationTimestamp=" + invocationTimestamp +
                ", invokedBy=" + invokedBy +
                ", commandAttributes=" + commandAttributes +
                '}';
    }
}
