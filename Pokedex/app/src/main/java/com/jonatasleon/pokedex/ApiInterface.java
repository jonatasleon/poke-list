package com.jonatasleon.pokedex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jonatasleon on 25/03/17.
 */

public interface ApiInterface {

    @GET("api/v1/pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") int id);

    @GET("{resource_uri}")
    Call<SpriteResponse> getSprite(@Path("resource_uri") String resourceUri);
}
