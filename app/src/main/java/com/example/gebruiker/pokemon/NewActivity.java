package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Date;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class NewActivity extends AppCompatActivity {

    EditText theTitle;
    EditText thePost;
    String email;
    long numberOfTopics;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        theTitle = findViewById(R.id.newTitle);
        thePost = findViewById(R.id.firstPost);


        Log.i("Dit is search", "onCreateView: ");

        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        keepTrackOfTopics();
    }

    public void keepTrackOfTopics() {

        ChildEventListener mChildEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                numberOfTopics = dataSnapshot.getChildrenCount();
                Log.i("the number of children", String.valueOf(numberOfTopics));
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

        Query mQuery = mDatabase.child("forum");
        mQuery.addChildEventListener(mChildEventListener);

    }

    public void toTopic(View view) {

        TopicTitle newTopic = new TopicTitle(theTitle.getText().toString(), email, numberOfTopics);
        mDatabase.child("forum").child("titles").child(String.valueOf(numberOfTopics)).setValue(newTopic);

        long currentTime = Calendar.getInstance().getTime().getTime();
        Log.i(String.valueOf(currentTime), "toTopic: ");
        ForumPost firstPost = new ForumPost(thePost.getText().toString(), email, currentTime);
        mDatabase.child("forum").child("posts").child(String.valueOf(numberOfTopics)).child("0").setValue(firstPost);

        Intent intent = new Intent(this, TopicActivity.class);
        intent.putExtra("ID", numberOfTopics);

        startActivity(intent);
    }

    public void toForum(View view) {
        onBackPressed();
    }
}
