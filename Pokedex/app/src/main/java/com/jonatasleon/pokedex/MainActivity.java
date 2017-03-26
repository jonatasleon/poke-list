package com.jonatasleon.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Pokemon> pokemonList = new ArrayList<>();
    RecyclerView recyclerView;
    PokemonAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_pokemons);

        pokemonAdapter = new PokemonAdapter(pokemonList);

        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pokemonAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("ID", pokemonList.get(position).getPokedexId());
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        addData();
    }

    private void addData() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        for(int i = 1; i <= 30; i++) {
            Call<Pokemon> call = apiService.getPokemon(i);
            call.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    if(response.isSuccessful()) {
                        Pokemon pokemon = response.body();

                        pokemonList.add(pokemon);
                        pokemonAdapter.notifyDataSetChanged();

                        Log.i("POKEMON", "Name: " + pokemon.getName());
                        Log.i("POKEMON", "Attack: " + pokemon.getAttack());
                        Log.i("POKEMON", "Defense: " + pokemon.getDefense());
                        Log.i("POKEMON", "Health: " + pokemon.getHealth());
                        Log.i("POKEMON", "Height: " + pokemon.getHeight());
                        Log.i("POKEMON", "Weight: " + pokemon.getWeight());

                    }
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                }
            });
        }
    }
}
