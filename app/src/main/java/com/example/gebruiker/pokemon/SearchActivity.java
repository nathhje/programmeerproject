package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Nathalie van Sterkenburg on 11-1-2018.
 *
 * Used to search for Pokémon, types or abilities.
 */

public class SearchActivity extends AppCompatActivity {

    String typeOfSearch;
    Spinner spinner;
    ListView listOfSearches;

    ArrayList<String> allPokemon = new ArrayList<>();
    ArrayList<String> allTypes = new ArrayList<>();
    ArrayList<String> allAbilities = new ArrayList<>();
    ArrayList<String> allSearchResults= new ArrayList<>();

    ArrayAdapter<String> pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        typeOfSearch = "pokemon";

        listOfSearches = findViewById(R.id.searchResult);

        spinner = findViewById(R.id.spinner);
        setUpSpinner();

        getAllSearches();

        setPokemonAdapter();
    }

    public void setPokemonAdapter() {

        pokemonAdapter = new ArrayAdapter<>(this, R.layout.layout_basic, allSearchResults);

        listOfSearches.setAdapter(pokemonAdapter);

        listOfSearches.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                toInfo(i);
            }
        });
    }

    public void setUpSpinner(){

        final String[] typesOfSearches = {"pokemon", "type", "ability"};

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeOfSearch = typesOfSearches[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, typesOfSearches);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public void Enter(View view) {

        EditText theSearch = findViewById(R.id.searchEdit);

        allSearchResults.clear();
        Log.i(spinner.getSelectedItem().toString(), "Enter: ");

        if(spinner.getSelectedItem().toString().equals("pokemon")){

            Log.i("in pokemon", "Enter: ");
            for(int i = 0; i < allPokemon.size(); i++){

                if(allPokemon.get(i).contains(theSearch.getText().toString())){
                    allSearchResults.add(allPokemon.get(i));
                }
            }
        }

        if(spinner.getSelectedItem().toString().equals("type")){

            for(int i = 0; i < allTypes.size(); i++){

                if(allTypes.get(i).contains(theSearch.getText().toString())){
                    allSearchResults.add(allTypes.get(i));
                }
            }
        }

        if(spinner.getSelectedItem().toString().equals("ability")){

            for(int i = 0; i < allAbilities.size(); i++){

                if(allAbilities.get(i).contains(theSearch.getText().toString())){
                    allSearchResults.add(allAbilities.get(i));
                }
            }
        }
        pokemonAdapter.notifyDataSetChanged();
    }

    public void toInfo(int i) {

        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("typeOfSearch", typeOfSearch);
        intent.putExtra("urlsearch", typeOfSearch + "/" + allSearchResults.get(i));
        startActivity(intent);
    }

    public void getAllSearches(){
        try{
            BufferedReader pokemonReader = new BufferedReader(new InputStreamReader(this
                    .getAssets().open("pokemon.txt")));

            String aPokemon = pokemonReader.readLine();

            while(aPokemon != null){
                allPokemon.add(aPokemon);
                aPokemon = pokemonReader.readLine();
            }

            pokemonReader.close();

            BufferedReader typeReader = new BufferedReader(new InputStreamReader(this
                    .getAssets().open("type.txt")));

            String aType = typeReader.readLine();

            while(aType != null){
                allTypes.add(aType);
                aType = typeReader.readLine();
            }

            typeReader.close();

            BufferedReader abilityReader = new BufferedReader(new InputStreamReader(this
                    .getAssets().open("ability.txt")));

            String anAbility = abilityReader.readLine();

            while(anAbility != null){
                allAbilities.add(anAbility);
                anAbility = abilityReader.readLine();
            }

            abilityReader.close();

        } catch (IOException ignored){}
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
