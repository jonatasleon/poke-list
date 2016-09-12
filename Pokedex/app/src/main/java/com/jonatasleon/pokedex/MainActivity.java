package com.jonatasleon.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
        Pokemon poke;

        poke = new Pokemon("Bulbasaur", "Grama");
        pokeList.add(poke);

        poke = new Pokemon("Charmander", "Fogo");
        pokeList.add(poke);

        poke = new Pokemon("Squirtle", "Água");
        pokeList.add(poke);

        pokemonAdapter.notifyDataSetChanged();
    }
}
