package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final ArrayList<String> allPokemon = new ArrayList<>();
        ListView list = findViewById(R.id.pokemonByList);


        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(this
                    .getAssets().open("all_pokemon.txt")));

            String aPokemon = reader.readLine();

            while(aPokemon != null){
                allPokemon.add(aPokemon.toLowerCase());
                aPokemon = reader.readLine();
            }

            reader.close();


        } catch (IOException e){

        }


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



    public void goBack(View view) {
        onBackPressed();
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
