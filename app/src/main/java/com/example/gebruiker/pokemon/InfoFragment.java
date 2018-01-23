package com.example.gebruiker.pokemon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Gebruiker on 21-1-2018.
 */

public class InfoFragment extends Fragment{

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
    ListView list1;
    ListView list2;
    ListView list3;
    ListView list4;
    ListView list5;
    ListView list6;
    ListView list7;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);


        name = view.findViewById(R.id.name);
        description = view.findViewById(R.id.description);
        effect = view.findViewById(R.id.effect);
        generation = view.findViewById(R.id.generation);
        sprite = view.findViewById(R.id.sprite);
        header1 = view.findViewById(R.id.header1);
        list1 = view.findViewById(R.id.list1);
        header2 = view.findViewById(R.id.header2);
        list2 = view.findViewById(R.id.list2);
        header3 = view.findViewById(R.id.header3);
        list3 = view.findViewById(R.id.list3);
        header4 = view.findViewById(R.id.header4);
        list4 = view.findViewById(R.id.list4);
        header5 = view.findViewById(R.id.header5);
        list5 = view.findViewById(R.id.list5);
        header6 = view.findViewById(R.id.header6);
        list6 = view.findViewById(R.id.list6);
        header7 = view.findViewById(R.id.header7);
        list7 = view.findViewById(R.id.list7);

        return view;
    }

    public void afterPokemonTask(Pokemon pokemon) {

        name.setText(pokemon.getName());

        String theSprite = pokemon.getSprite();
        DownloadImageTask imageTask = new DownloadImageTask(sprite);
        imageTask.execute(theSprite);


    }

    public void afterTypeTask(Type type) {

        name.setText(type.getName());
    }

    public void afterAbilityTask(Ability ability) {

        name.setText(ability.getName());
        description.setText(ability.getDescription());
        effect.setText(ability.getEffect());
        generation.setText(ability.getGeneration());
    }
}
