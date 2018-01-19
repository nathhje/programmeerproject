package com.example.gebruiker.pokemon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static android.content.ContentValues.TAG;

/**
 * Created by Gebruiker on 18-1-2018.
 */

public class NewFragment extends Fragment {

    EditText theTitle;
    EditText thePost;
    String email;
    long numberOfTopics;
    Button forumButton;
    Button topicButton;

    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);


        theTitle = (EditText) view.findViewById(R.id.newTitle);
        thePost = (EditText) view.findViewById(R.id.firstPost);

        forumButton = (Button) view.findViewById(R.id.toForum);
        forumButton.setVisibility(View.VISIBLE);
        topicButton = (Button) view.findViewById(R.id.toTopic);
        topicButton.setVisibility(View.VISIBLE);

        Log.i("Dit is search", "onCreateView: ");

        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        view.findViewById(R.id.toTopic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                toTopic(view);
            }
        });

        view.findViewById(R.id.toForum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                forumButton.setVisibility(View.INVISIBLE);
                topicButton.setVisibility(View.INVISIBLE);

                getFragmentManager().popBackStack();
            }
        });

        keepTrackOfTopics();

        return view;
    }

    public void toTopic(View view) {

        Log.i("tussen hier", "toTopic: ");
        TopicTitle newTopic = new TopicTitle(theTitle.getText().toString(), email);
        Log.i("en hier", "toTopic: ");

        mDatabase.child("forum").child("titles").child(String.valueOf(numberOfTopics)).setValue(newTopic);
        Log.i("wat gek", "toTopic: ");

        forumButton.setVisibility(View.INVISIBLE);
        topicButton.setVisibility(View.INVISIBLE);

        // Create fragment and give it an argument specifying the article it should show
        TopicFragment newFragment = new TopicFragment();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        // Replace whatever is in the container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.newLayout, newFragment);

        // Commit the transaction
        transaction.commit();

    }

    public void keepTrackOfTopics() {

        ChildEventListener mChildEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                numberOfTopics = dataSnapshot.getChildrenCount();
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
}