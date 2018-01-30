package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Nathalie van Sterkenburg on 11-1-2018.
 *
 * Shows a list of all Pokémon, with links to info about Pokémon.
 */

public class ListActivity extends AppCompatActivity {

    ListView list;
    ArrayList<String> allPokemon = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        list = findViewById(R.id.pokemonByList);

        getPokemonList();

        setPokemonAdapter();
    }

    public void getPokemonList() {

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(this
                    .getAssets().open("all_pokemon.txt")));

            String aPokemon = reader.readLine();

            while(aPokemon != null){
                allPokemon.add(aPokemon.toLowerCase());
                aPokemon = reader.readLine();
            }

            reader.close();

        } catch (IOException ignored){}
    }

    public void setPokemonAdapter() {

        ArrayAdapter<String> allPokemonAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, allPokemon);

        list.setAdapter(allPokemonAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListActivity.this, InfoActivity.class);
                intent.putExtra("typeOfSearch", "pokemon");
                intent.putExtra("urlsearch", "pokemon/" + allPokemon.get(i));
                startActivity(intent);
            }
        });
    }

    public void toWikia(View view) { startActivity(new Intent(this, TabActivity.class)); }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
