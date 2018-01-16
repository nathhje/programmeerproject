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
 * Created by Gebruiker on 15-1-2018.
 */

public class WikiaFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wikia, container, false);

        Log.i("Do i get here", "onCreateView: ");
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

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        // Replace whatever is in the container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.wikiaLayout, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }


}
