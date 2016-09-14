package com.jonatasleon.pokedex;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jonatasleon on 14/09/16.
 */
public class Sprite {

    @SerializedName("name")
    private String name;

    public Sprite(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
