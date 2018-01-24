package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
    NonScrollListView list1;
    NonScrollListView list2;
    NonScrollListView list3;
    NonScrollListView list4;
    NonScrollListView list5;
    NonScrollListView list6;
    NonScrollListView list7;

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

        Intent intent = getIntent();

        InfoAsyncTask infoTask = new InfoAsyncTask(this, intent.getStringExtra("typeOfSearch"));
        infoTask.execute(intent.getStringExtra("urlsearch"));

    }

    public void afterPokemonTask(Pokemon pokemon) {
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

        header3.setText("Abilities:");
        ArrayAdapter<String> abilityAdapter;
        abilityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, pokemon.abilities);

        list3.setAdapter(abilityAdapter);


    }

    public void afterTypeTask(Type type) {

        name.setText(type.getName());

        header1.setText("Half damage to:");
        ArrayAdapter<String> halfToAdapter;
        halfToAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, type.halfTo);

        list1.setAdapter(halfToAdapter);

        header2.setText("No damage to:");
        ArrayAdapter<String> noneToAdapter;
        noneToAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, type.noTo);

        list2.setAdapter(noneToAdapter);

        header3.setText("Double damage to:");
        ArrayAdapter<String> doubleToAdapter;
        doubleToAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, type.doubleTo);

        list3.setAdapter(doubleToAdapter);

        header5.setText("Half damage from:");
        ArrayAdapter<String> halfFromAdapter;
        halfFromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, type.halfFrom);

        list5.setAdapter(halfFromAdapter);

        header6.setText("No damage from:");
        ArrayAdapter<String> noneFromAdapter;
        noneFromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, type.noFrom);

        list6.setAdapter(noneFromAdapter);

        header7.setText("Double damage to:");
        ArrayAdapter<String> pokemonAdapter;
        pokemonAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, type.pokemon);

        list7.setAdapter(pokemonAdapter);

        header4.setText("Pokémon with this type:");
        ArrayAdapter<String> doubleFromAdapter;
        doubleFromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, type.doubleFrom);

        list4.setAdapter(doubleFromAdapter);

    }

    public void afterAbilityTask(Ability ability) {

        name.setText(ability.getName());
        description.setText("Description: " + ability.getDescription());
        effect.setText("Effect: " + ability.getEffect());
        generation.setText("Introduced in " + ability.getGeneration());


        header4.setText("Pokémon with this ability:");
        ArrayAdapter<String> pokemonAdapter;
        pokemonAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, ability.pokemon);

        list4.setAdapter(pokemonAdapter);
    }

    public void toInfo(View view) {startActivity(new Intent(this, InfoActivity.class));
    }

    public void toSearch(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    public void toForum(View view) { startActivity(new Intent(this, ForumActivity.class)); }

    public void toWikia(View view) {
        startActivity(new Intent(this, WikiaActivity.class));
    }
}
