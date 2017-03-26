package com.jonatasleon.pokedex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jonatasleon on 25/03/17.
 */

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokeViewHolder> {

    private List<Pokemon> pokemonList;

    public class PokeViewHolder extends RecyclerView.ViewHolder {
        public TextView name, type;
        public ImageView ivPokemon;

        public PokeViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            type = (TextView) itemView.findViewById(R.id.tv_type);
            ivPokemon = (ImageView) itemView.findViewById(R.id.iv_pokemon);
        }
    }

    public PokemonAdapter(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @Override
    public PokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.pokemon_row, parent, false);

        return new PokeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PokeViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
        holder.name.setText(pokemon.getName());
        holder.type.setText(pokemon.pokeTypesToString());

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Sprite sprite = pokemon.getSprites().get(0);
        String spriteUrl = sprite.getResourceUri();

        Call<SpriteResponse> call = apiService.getSprite(spriteUrl);
        call.enqueue(new Callback<SpriteResponse>() {
            @Override
            public void onResponse(Call<SpriteResponse> call, Response<SpriteResponse> response) {
                if(response.isSuccessful()){
                    SpriteResponse spriteResponse = response.body();
                    String image = "http://pokeapi.co" + spriteResponse.getImage();

                    Picasso.with(holder.ivPokemon.getContext())
                            .load(image)
                            .resize(64, 64)
                            .into(holder.ivPokemon);
                }
            }

            @Override
            public void onFailure(Call<SpriteResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }
}
