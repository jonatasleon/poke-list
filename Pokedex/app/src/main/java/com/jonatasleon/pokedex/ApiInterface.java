package com.jonatasleon.pokedex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jonatasleon on 14/09/16.
 */
public interface ApiInterface {

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") int id);
}
