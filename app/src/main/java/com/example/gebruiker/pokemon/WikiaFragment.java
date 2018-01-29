package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Gebruiker on 15-1-2018.
 */

public class WikiaFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wikia, container, false);


        final ListView quickLink = view.findViewById(R.id.firstQuickLink);

        final String[] quickLinks = {"insomnia", "mummy", "intimidate"};

        ArrayAdapter<String> linkAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
                android.R.id.text1, quickLinks);
        quickLink.setAdapter(linkAdapter);

        quickLink.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("typeOfSearch", "ability");
                intent.putExtra("urlsearch", "ability/" + quickLinks[i]);
                startActivity(intent);


            }
        });


        view.findViewById(R.id.toPokemonList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ListActivity.class));
            }
        });

        view.findViewById(R.id.goToSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });


        return view;
    }




}
