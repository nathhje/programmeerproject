package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    TextView name;
    TextView description;
    TextView effect;
    TextView generation;
    ImageView sprite;
    TextView header1;
    TextView header2;
    TextView header3;
    TextView header4;
    TextView header5;
    TextView header6;
    TextView header7;
    TextView header8;
    NonScrollListView list1;
    NonScrollListView list2;
    NonScrollListView list3;
    NonScrollListView list4;
    NonScrollListView list5;
    NonScrollListView list6;
    NonScrollListView list7;
    NonScrollListView list8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        effect = findViewById(R.id.effect);
        generation = findViewById(R.id.generation);
        sprite = findViewById(R.id.sprite);
        header1 = findViewById(R.id.header1);
        list1 = findViewById(R.id.list1);
        header2 = findViewById(R.id.header2);
        list2 = findViewById(R.id.list2);
        header3 = findViewById(R.id.header3);
        list3 = findViewById(R.id.list3);
        header4 = findViewById(R.id.header4);
        list4 = findViewById(R.id.list4);
        header5 = findViewById(R.id.header5);
        list5 = findViewById(R.id.list5);
        header6 = findViewById(R.id.header6);
        list6 = findViewById(R.id.list6);
        header7 = findViewById(R.id.header7);
        list7 = findViewById(R.id.list7);
        header8 = findViewById(R.id.header8);
        list8 = findViewById(R.id.list8);

        Intent intent = getIntent();

        InfoAsyncTask infoTask = new InfoAsyncTask(this, intent.getStringExtra("typeOfSearch"));
        infoTask.execute(intent.getStringExtra("urlsearch"));

    }

    public void afterPokemonTask(final Pokemon pokemon) {
        name.setText(pokemon.getName());

        Log.i(pokemon.baseStats.get(0), "afterPokemonTask: ");

        String theSprite = pokemon.getSprite();
        DownloadImageTask imageTask = new DownloadImageTask(sprite);
        imageTask.execute(theSprite);

        header1.setText("Base Stats:");
        ArrayAdapter<String> statsAdapter;
        statsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, pokemon.baseStats);

        list1.setAdapter(statsAdapter);

        header2.setText("Types:");
        ArrayAdapter<String> typeAdapter;
        typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, pokemon.types);

        list2.setAdapter(typeAdapter);

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeIntent(i, pokemon.types);
            }
        });

        header3.setText("Abilities:");
        ArrayAdapter<String> abilityAdapter;
        abilityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, pokemon.abilities);

        list3.setAdapter(abilityAdapter);

        list3.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                abilityIntent(i, pokemon.abilities);
            }
        });


    }

    public void afterTypeTask(final Type type) {

        name.setText(type.getName());

        header1.setText("Half damage to:");
        ArrayAdapter<String> halfToAdapter;
        halfToAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, type.halfTo);

        list1.setAdapter(halfToAdapter);

        list1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeIntent(i, type.halfTo);
            }
        });

        header2.setText("No damage to:");
        ArrayAdapter<String> noneToAdapter;
        noneToAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, type.noTo);

        list2.setAdapter(noneToAdapter);

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeIntent(i, type.noTo);
            }
        });

        header3.setText("Double damage to:");
        ArrayAdapter<String> doubleToAdapter;
        doubleToAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, type.doubleTo);

        list3.setAdapter(doubleToAdapter);

        list3.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeIntent(i, type.doubleTo);
            }
        });

        header5.setText("Half damage from:");
        ArrayAdapter<String> halfFromAdapter;
        halfFromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, type.halfFrom);

        list5.setAdapter(halfFromAdapter);

        list5.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeIntent(i, type.halfFrom);
            }
        });

        header6.setText("No damage from:");
        ArrayAdapter<String> noneFromAdapter;
        noneFromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, type.noFrom);

        list6.setAdapter(noneFromAdapter);

        list6.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeIntent(i, type.noFrom);
            }
        });

        header7.setText("Double damage from:");
        ArrayAdapter<String> pokemonAdapter;
        pokemonAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, type.doubleFrom);

        list7.setAdapter(pokemonAdapter);

        list7.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeIntent(i, type.doubleFrom);
            }
        });

        header4.setText("Pokémon with this type:");
        ArrayAdapter<String> doubleFromAdapter;
        doubleFromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, type.pokemon);

        list4.setAdapter(doubleFromAdapter);

        list4.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pokemonIntent(i, type.pokemon);
            }
        });

    }

    public void afterAbilityTask(final Ability ability) {

        name.setText(ability.getName());
        description.setText("Description: " + ability.getDescription());
        effect.setText("Effect: " + ability.getEffect());
        generation.setText("Introduced in " + ability.getGeneration());


        header8.setText("Pokémon with this ability:");
        ArrayAdapter<String> pokemonAdapter;
        pokemonAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, ability.pokemon);

        list8.setAdapter(pokemonAdapter);

        list8.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pokemonIntent(i, ability.pokemon);
            }
        });
    }

    public void abilityIntent(int i, ArrayList<String> abilities) {
        Intent intent = new Intent(InfoActivity.this, InfoActivity.class);
        intent.putExtra("typeOfSearch", "ability");
        intent.putExtra("urlsearch", "ability/" + abilities.get(i));
        startActivity(intent);
    }

    public void typeIntent(int i, ArrayList<String> types) {
        Intent intent = new Intent(InfoActivity.this, InfoActivity.class);
        intent.putExtra("typeOfSearch", "type");
        intent.putExtra("urlsearch", "type/" + types.get(i));
        startActivity(intent);
    }

    public void pokemonIntent(int i, ArrayList<String> pokemon) {
        Intent intent = new Intent(InfoActivity.this, InfoActivity.class);
        intent.putExtra("typeOfSearch", "pokemon");
        intent.putExtra("urlsearch", "pokemon/" + pokemon.get(i));
        startActivity(intent);
    }

    public void toSearch(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    public void toWikia(View view) {
        startActivity(new Intent(this, TabActivity.class));
    }
}
