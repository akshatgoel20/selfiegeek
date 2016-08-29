package com.starksky.selfiegeek.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * Created by NTQK0716 on 2016-08-29.
 */
public class Entity extends GenericJson {

    @Key("_id")
    private String title;

    public Entity() {}

    public Entity(String title) {
        super();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
