package com.jonatasleon.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Pokemon> pokeList = new ArrayList<>();
    RecyclerView recyclerView;
    PokemonAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_pokemons);

        pokemonAdapter = new PokemonAdapter(pokeList);

        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pokemonAdapter);

        addData();
    }

    private void addData() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        for(int i = 1; i <= 30; i++) {
            Call<Pokemon> call = apiService.getPokemon(i);

            Log.i("TAG", "ID: " + i);
            call.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    Pokemon pokemon = response.body();
                    pokeList.add(pokemon);
                    pokemonAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {

                }
            });
        }
    }
}
