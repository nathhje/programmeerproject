package com.example.gebruiker.pokemon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Created by Nathalie van Sterkenburg on 11-1-2018.
 *
 * Shows information about Pokémon, type or ability, with links to other Pokémon, types or abilities.
 */

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
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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

    // sets up info on Pokémon
    @SuppressLint("SetTextI18n")
    public void afterPokemonTask(final Pokemon pokemon) {
        name.setText(pokemon.getName());

        String theSprite = pokemon.getSprite();
        DownloadImageTask imageTask = new DownloadImageTask(sprite);
        imageTask.execute(theSprite);

        header1.setText("Base Stats:");
        list1.setAdapter(makeAdapter(pokemon.baseStats));

        header2.setText("Types:");
        list2.setAdapter(makeAdapter(pokemon.types));

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeIntent(i, pokemon.types);
            }
        });

        header3.setText("Abilities:");
        list3.setAdapter(makeAdapter(pokemon.abilities));

        list3.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                abilityIntent(i, pokemon.abilities);
            }
        });

        setButtonsVisible();
    }

    // sets up info on type
    @SuppressLint("SetTextI18n")
    public void afterTypeTask(final Type type) {

        name.setText(type.getName());

        header1.setText("Half damage to:");
        list1.setAdapter(makeAdapter(type.halfTo));

        list1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeIntent(i, type.halfTo);
            }
        });

        header2.setText("No damage to:");
        list2.setAdapter(makeAdapter(type.noTo));

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeIntent(i, type.noTo);
            }
        });

        header3.setText("Double damage to:");
        list3.setAdapter(makeAdapter(type.doubleTo));

        list3.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeIntent(i, type.doubleTo);
            }
        });

        header5.setText("Half damage from:");
        list5.setAdapter(makeAdapter(type.halfFrom));

        list5.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeIntent(i, type.halfFrom);
            }
        });

        header6.setText("No damage from:");
        list6.setAdapter(makeAdapter(type.noFrom));

        list6.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeIntent(i, type.noFrom);
            }
        });

        header7.setText("Double damage from:");
        list7.setAdapter(makeAdapter(type.doubleFrom));

        list7.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeIntent(i, type.doubleFrom);
            }
        });

        header4.setText("Pokémon with this type:");
        list4.setAdapter(makeAdapter(type.pokemon));

        list4.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pokemonIntent(i, type.pokemon);
            }
        });

        setButtonsVisible();

    }

    // sets up info on ability
    @SuppressLint("SetTextI18n")
    public void afterAbilityTask(final Ability ability) {

        name.setText(ability.getName());
        description.setText("Description: " + ability.getDescription());
        effect.setText("Effect: " + ability.getEffect());
        generation.setText("Introduced in " + ability.getGeneration());

        header8.setText("Pokémon with this ability:");
        list8.setAdapter(makeAdapter(ability.pokemon));

        list8.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pokemonIntent(i, ability.pokemon);
            }
        });

        setButtonsVisible();
    }

    public ArrayAdapter<String> makeAdapter(ArrayList<String> theList) {

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, R.layout.layout_basic, theList);

        return adapter;
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

    public void setButtonsVisible() {
        Button toList = findViewById(R.id.toListOfPokemon);
        Button toSearch = findViewById(R.id.backToNewSearch);
        Button toWikia = findViewById(R.id.backToHomescreen);
        toList.setVisibility(View.VISIBLE);
        toSearch.setVisibility(View.VISIBLE);
        toWikia.setVisibility(View.VISIBLE);
    }

    public void toPokemonList(View view) {
        startActivity(new Intent(this, ListActivity.class));
    }

    public void toWikia(View view) {
        startActivity(new Intent(this, TabActivity.class));
    }

    public void toSearch(View view) { startActivity(new Intent(this, SearchActivity.class)); }

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
