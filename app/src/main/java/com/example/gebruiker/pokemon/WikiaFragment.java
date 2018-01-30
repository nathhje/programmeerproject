package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Nathalie van Sterkenburg on 15-1-2018.
 *
 * Introduces to the wikia, has quick links and navigation.
 */

public class WikiaFragment extends Fragment {

    ListView quickLink;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wikia, container, false);

        quickLink = view.findViewById(R.id.firstQuickLink);

        setUpQuickLinks();

        setNavigationListeners(view);

        return view;
    }

    public void setUpQuickLinks() {

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
    }

    public void setNavigationListeners(View view) {

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
    }
}
