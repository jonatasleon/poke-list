package com.jonatasleon.pokedex;

/**
 * Created by jonatasleon on 12/09/16.
 */
public class Pokemon {

    private String name;
    private String type;

    public Pokemon() {
    }

    public Pokemon(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
