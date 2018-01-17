package com.example.gebruiker.pokemon;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Gebruiker on 15-1-2018.
 */

public class ForumFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;

    ListView listTopic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        listTopic = (ListView) view.findViewById(R.id.topicList);
        ArrayList<TopicTitle> titleArray = new ArrayList<TopicTitle>();

        TopicAdapter topicAdapter = new TopicAdapter(this, titleArray);
        listTopic.setAdapter(topicAdapter);

        view.findViewById(R.id.toTopic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toTopic(view);
            }
        });

        setUpTopics();

        return view;
    }

    public void toTopic(View view) {

        // Create fragment and give it an argument specifying the article it should show
        TopicFragment newFragment = new TopicFragment();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        // Replace whatever is in the container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.wikiaLayout, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }

    public void setUpTopics() {

        ValueEventListener mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

    }

    public class TopicAdapter extends ArrayAdapter<TopicTitle> {
        public TopicAdapter(@NonNull ForumFragment forumFragment, ArrayList<TopicTitle> titles) {
            super(forumFragment, 0, titles);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TopicTitle title = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_forumlist, parent, false);
            }

            TextView theTitle = (TextView) convertView.findViewById(R.id.titleItem);
            TextView theEmail = (TextView) convertView.findViewById(R.id.emailItem);

            theTitle.setText(title.getTitle());
            theEmail.setText(title.getEmail());

            return convertView;
        }
    }
}
