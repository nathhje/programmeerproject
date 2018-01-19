package com.example.gebruiker.pokemon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Gebruiker on 17-1-2018.
 */

public class TopicFragment extends Fragment{

    Button backToForum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic, container, false);

        Log.i("kom ik hier", "onCreateView: ");
        backToForum = (Button) view.findViewById(R.id.backForum);
        backToForum.setVisibility(View.VISIBLE);

        view.findViewById(R.id.backForum).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backToForum.setVisibility(View.INVISIBLE);

                getFragmentManager().popBackStack();
            }
        });

        return view;
    }
}
