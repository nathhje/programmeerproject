package com.example.gebruiker.pokemon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Gebruiker on 18-1-2018.
 */

public class NewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);

        Log.i("Dit is search", "onCreateView: ");
/*
        view.findViewById(R.id.toTopic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                toTopic(view);
            }
        });
*/
        return view;
    }
}