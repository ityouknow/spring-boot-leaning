package com.neo.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;

    private long userId;
    private String name;
    private String address;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
