package com.jonatasleon.pokedex;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jonatasleon on 25/03/17.
 */

public class PokeType {

    @SerializedName("name")
    private String name;

    public PokeType(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
