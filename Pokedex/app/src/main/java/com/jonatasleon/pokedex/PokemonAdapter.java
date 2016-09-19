package com.jonatasleon.pokedex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jonatasleon on 12/09/16.
 */
public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokeViewHolder> {

    private List<Pokemon> pokeList;

    public class PokeViewHolder extends RecyclerView.ViewHolder {
        public TextView name, type;

        public PokeViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            type = (TextView) itemView.findViewById(R.id.tv_type);
        }
    }

    public PokemonAdapter(List<Pokemon> pokeList) {
        this.pokeList = pokeList;
    }

    @Override
    public PokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_row, parent, false);

        return new PokeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PokeViewHolder holder, int position) {
        Pokemon pokemon = pokeList.get(position);
        holder.name.setText(pokemon.getName());
        holder.type.setText(pokemon.pokeTypesToString());
    }

    @Override
    public int getItemCount() {
        return pokeList.size();
    }
}
