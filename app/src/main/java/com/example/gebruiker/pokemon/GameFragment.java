package com.example.gebruiker.pokemon;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Nathalie van Sterkenburg on 15-1-2018.
 *
 * Implements the Pokémon naming challenge.
 */

public class GameFragment extends Fragment {

    private DatabaseReference mDatabase;

    int numberOfPokemon = 807;
    int numberOfSavedGuesses = 0;
    ArrayList<String> allPokemon = new ArrayList<>();
    String userUID;
    ArrayList<String> guessedPokemon = new ArrayList<>();
    ArrayAdapter<String> allPokemonAdapter;

    TextView instruction;
    Button startTheGame;
    EditText newGuess;
    Button enter;
    TextView numberGuessed;
    ListView listOfGuessed;
    Button restart;
    Button giveUp;
    TextView winner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        instruction = view.findViewById(R.id.instruction);
        startTheGame = view.findViewById(R.id.toGame);
        newGuess = view.findViewById(R.id.guess);
        enter = view.findViewById(R.id.gameEnter);
        numberGuessed = view.findViewById(R.id.numberGuessed);
        listOfGuessed = view.findViewById(R.id.guessedAlready);
        restart = view.findViewById(R.id.restart);
        giveUp = view.findViewById(R.id.giveUp);
        winner = view.findViewById(R.id.winner);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        setPokemonAdapter();

        startTheGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onEnter(); }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRestart();
            }
        });

        giveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGiveUp();
            }
        });


        return view;
    }

    public void setPokemonAdapter() {

        allPokemonAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, guessedPokemon);

        listOfGuessed.setAdapter(allPokemonAdapter);

        getGuessedPokemon();
    }

    public void onEnter() {
        String theGuess = newGuess.getText().toString();

        String lowerGuess = theGuess.toLowerCase();

        if(allPokemon.remove(lowerGuess)) {

            mDatabase.child("users").child(userUID).child("pokemon").child
                    (String.valueOf(numberOfSavedGuesses)).setValue(lowerGuess);
            newGuess.setText("");
        }
        if(guessedPokemon.size() == numberOfPokemon){

            winner.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("SetTextI18n")
    public void startGame() {

        instruction.setVisibility(View.INVISIBLE);
        startTheGame.setVisibility(View.INVISIBLE);
        newGuess.setVisibility(View.VISIBLE);
        enter.setVisibility(View.VISIBLE);
        numberGuessed.setVisibility(View.VISIBLE);
        listOfGuessed.setVisibility(View.VISIBLE);
        restart.setVisibility(View.VISIBLE);
        giveUp.setVisibility(View.VISIBLE);

        numberOfSavedGuesses = 0;

        getAllPokemon();
    }

    public void onRestart() {

        instruction.setVisibility(View.VISIBLE);
        startTheGame.setVisibility(View.VISIBLE);
        newGuess.setVisibility(View.INVISIBLE);
        enter.setVisibility(View.INVISIBLE);
        numberGuessed.setVisibility(View.INVISIBLE);
        listOfGuessed.setVisibility(View.INVISIBLE);
        restart.setVisibility(View.INVISIBLE);
        giveUp.setVisibility(View.INVISIBLE);
        winner.setVisibility(View.INVISIBLE);

        mDatabase.child("users").child(userUID).child("pokemon").removeValue();
        guessedPokemon.clear();

        numberGuessed.setText("Progress: 0/807 guessed");
    }

    public void onGiveUp() {

        enter.setVisibility(View.INVISIBLE);
        for(int i = 0; i < allPokemon.size(); i++) {

            mDatabase.child("users").child(userUID).child("pokemon").child
                    (String.valueOf(numberOfSavedGuesses)).setValue(allPokemon.get(i));
            numberOfSavedGuesses += 1;
        }
        allPokemon.clear();
    }

    public void getAllPokemon(){

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(getActivity()
                    .getAssets().open("all_pokemon.txt")));

            String aPokemon = reader.readLine();

            while(aPokemon != null){
                allPokemon.add(aPokemon.toLowerCase());
                aPokemon = reader.readLine();
            }

            reader.close();

        } catch (IOException ignored){}
    }

    public void getGuessedPokemon() {

        ChildEventListener mChildEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                guessedPokemon.add(dataSnapshot.getValue().toString());
                allPokemonAdapter.notifyDataSetChanged();
                numberOfSavedGuesses = guessedPokemon.size();

                String progressReport = "Progress: " + numberOfSavedGuesses + "/" + numberOfPokemon +
                        " guessed";
                numberGuessed.setText(progressReport);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

        Query mQuery = mDatabase.child("users").child(userUID).child("pokemon");
        mQuery.addChildEventListener(mChildEventListener);
    }
}
