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
import android.widget.ListView;

/**
 * Created by Gebruiker on 15-1-2018.
 */

public class WikiaFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wikia, container, false);

        final ListView quickLink = view.findViewById(R.id.firstQuickLink);

        final String[] quickLinks = {"lucario", "pikachu", "lilligant"};

        ArrayAdapter<String> linkAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
                android.R.id.text1, quickLinks);
        quickLink.setAdapter(linkAdapter);

        quickLink.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("typeOfSearch", "pokemon");
                intent.putExtra("urlsearch", "pokemon/" + quickLinks[i]);
                startActivity(intent);
            }
        });

        Log.i("Dit is Wikia", "onCreateView: ");
        view.findViewById(R.id.btnWikia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                toSearch(view);
            }
        });

        return view;
    }

    public void toSearch(View view) {
    }

}
