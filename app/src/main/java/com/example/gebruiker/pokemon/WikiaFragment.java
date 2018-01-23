package com.example.gebruiker.pokemon;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Gebruiker on 15-1-2018.
 */

public class WikiaFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wikia, container, false);

        Button button = (Button) view.findViewById(R.id.btnWikia);
        button.setVisibility(View.VISIBLE);

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

        // Create fragment and give it an argument specifying the article it should show
        SearchFragment newFragment = new SearchFragment();
/*
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        Log.i("test 1", "toSearch: ");

        // Replace whatever is in the container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.container_for_fragments, newFragment);
        transaction.addToBackStack(null);
        Button button = getView().findViewById(R.id.btnWikia);
        //button.setVisibility(View.INVISIBLE);
        //onDestroyView();
        Log.i("test 2", "toSearch: ");
        // Commit the transaction

        transaction.commit();*/
    }

    @Override
    public void onPause() {

        Log.i("do i get:", "onPause: ");

        super.onPause();
    }

    @Override
    public void onResume() {

        Log.i("do i get:", "onResume: ");
        Button button = (Button) getView().findViewById(R.id.btnWikia);
        button.setVisibility(View.VISIBLE);
        super.onResume();
    }
}
