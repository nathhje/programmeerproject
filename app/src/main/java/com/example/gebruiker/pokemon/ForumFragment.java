package com.example.gebruiker.pokemon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by Gebruiker on 15-1-2018.
 */

public class ForumFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;

    Button toNewButton;
    ListView listTopic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);
        Log.i("dit is forum", "onCreateView: ");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        listTopic = view.findViewById(R.id.topicList);

        setUpTopics();

        view.findViewById(R.id.toNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toNew(view);
            }
        });

        return view;
    }

    public void toNew(View view) {

        startActivity(new Intent(getActivity(), NewActivity.class));

    }

    public void setUpTopics() {

        final ArrayList<TopicTitle> titleArray = new ArrayList<TopicTitle>();

        final TopicAdapter topicAdapter = new TopicAdapter(getActivity(), titleArray);
        listTopic.setAdapter(topicAdapter);

        listTopic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), TopicActivity.class);
                TopicTitle topicTitle = titleArray.get(i);
                intent.putExtra("ID", topicTitle.getId());
                intent.putExtra("title", topicTitle.getTitle());
                startActivity(intent);
            }
        });

        Log.i("let's debug again", "setUpTopics: ");

        ChildEventListener mChildEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Log.i("hier dus niet", "onChildAdded: ");
                TopicTitle aTitle = dataSnapshot.getValue(TopicTitle.class);
                topicAdapter.add(aTitle);
                Log.i(aTitle.getTitle(), "onChildAdded: ");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

        Query mQuery = mDatabase.child("forum").child("titles");
        mQuery.addChildEventListener(mChildEventListener);

    }

    public class TopicAdapter extends ArrayAdapter<TopicTitle> {
        public TopicAdapter(@NonNull Context context, ArrayList<TopicTitle> titles) {
            super(context, 0, titles);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TopicTitle title = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_forumlist, parent, false);
            }

            TextView theTitle = convertView.findViewById(R.id.titleItem);
            TextView theEmail = convertView.findViewById(R.id.emailItem);

            theTitle.setText(title.getTitle());
            theEmail.setText(title.getEmail());

            return convertView;
        }
    }
}
