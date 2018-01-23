package com.example.gebruiker.pokemon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Gebruiker on 16-1-2018.
 */

public class SearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("test 3", "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Log.i("Dit is search", "onCreateView: ");

        view.findViewById(R.id.toWikia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                toWikia(view);
            }
        });

        return view;
    }

    public void toWikia(View view) {

        getFragmentManager().popBackStack();
    }
/*
        // Create fragment and give it an argument specifying the article it should show
        WikiaFragment newFragment = new WikiaFragment();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        // Replace whatever is in the container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.searchLayout, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
*/
}
