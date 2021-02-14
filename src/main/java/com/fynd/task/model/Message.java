package com.fynd.task.model;

import java.io.Serializable;

public class Message implements Serializable {


    private static final long serialVersionUID = 1L;
    private String eventType;
    private String version;
    private String data;

    public Message(String eventType, String version, String data) {
        this.eventType = eventType;
        this.version = version;
        this.data = data;
    }

    public Message() {
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
            "eventType='" + eventType + '\'' +
            ", version='" + version + '\'' +
            ", data='" + data + '\'' +
            '}';
    }
}
