package com.jonatasleon.pokedex;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jonatasleon on 26/03/17.
 */

public class SpriteResponse {

    @SerializedName("id")
    private Integer id;

    @SerializedName("image")
    private String image;

    @SerializedName("name")
    private String name;

    public SpriteResponse(Integer id, String image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
