package com.example.gebruiker.pokemon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Gebruiker on 15-1-2018.
 */

public class GameFragment extends Fragment {

    ArrayList<String> allPokemon = new ArrayList<>();
    ListView listOfGuessed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        listOfGuessed = view.findViewById(R.id.guessedAlready);

        Log.i("leuk dit joh", "onCreateView: ");
        view.findViewById(R.id.giveUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startGame();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        return view;
    }

    public void startGame() throws JSONException {

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(getActivity()
                    .getAssets().open("all_pokemon.txt")));

            String aPokemon = reader.readLine();

            while(aPokemon != null){
                allPokemon.add(aPokemon);
                aPokemon = reader.readLine();
            }

            reader.close();

            ArrayAdapter<String> allPokemonAdapter = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_list_item_1, android.R.id.text1, allPokemon);

            listOfGuessed.setAdapter(allPokemonAdapter);


        } catch (IOException e){

        }
    }
}
