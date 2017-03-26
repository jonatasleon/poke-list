package com.jonatasleon.pokedex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private TextView tvName, tvTypes, tvAttack, tvDefense, tvSpeed;
    private ImageView ivPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = (TextView) findViewById(R.id.tv_detail_name);
        tvTypes = (TextView) findViewById(R.id.tv_detail_types);
        tvAttack = (TextView) findViewById(R.id.tv_detail_attack);
        tvDefense = (TextView) findViewById(R.id.tv_detail_defense);
        tvSpeed = (TextView) findViewById(R.id.tv_detail_speed);
        ivPokemon = (ImageView) findViewById(R.id.iv_detail_pokemon);

        int id = getIntent().getIntExtra("ID", 0);
        requestData(id);
    }

    private void requestData(int id) {
        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Pokemon> call = apiService.getPokemon(id);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokemon;

                if(response.isSuccessful()) {
                    pokemon = response.body();

                    tvName.setText(pokemon.getName());
                    tvTypes.setText(pokemon.pokeTypesToString());
                    tvAttack.setText("Attack: " + pokemon.getAttack().toString());
                    tvDefense.setText("Defense: " + pokemon.getDefense().toString());
                    tvSpeed.setText("Speed: " + pokemon.getSpeed().toString());

                    Call<SpriteResponse> callSprite;
                    Sprite sprite = pokemon.getSprites().get(0);
                    callSprite = apiService.getSprite(sprite.getResourceUri());
                    callSprite.enqueue(new Callback<SpriteResponse>() {
                        @Override
                        public void onResponse(Call<SpriteResponse> call, Response<SpriteResponse> response) {
                            SpriteResponse spriteResponse;
                            if(response.isSuccessful()) {
                                spriteResponse = response.body();
                                Picasso.with(DetailActivity.this)
                                        .load("http://pokeapi.co" + spriteResponse.getImage())
                                        .resize(128,128)
                                        .into(ivPokemon);
                            }
                        }

                        @Override
                        public void onFailure(Call<SpriteResponse> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });
    }

}
