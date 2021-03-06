package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by Nathalie van Sterkenburg on 11-1-2018.
 *
 * Used to create a new topic in the forum.
 */

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
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        theTitle = findViewById(R.id.newTitle);
        thePost = findViewById(R.id.firstPost);

        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        keepTrackOfTopics();
    }

    // checks number of topics to determine next ID
    public void keepTrackOfTopics() {

        ChildEventListener mChildEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                numberOfTopics = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

        Query mQuery = mDatabase.child("forum");
        mQuery.addChildEventListener(mChildEventListener);
    }

    // creates new topic
    public void toTopic(View view) {

        TopicTitle newTopic = new TopicTitle(theTitle.getText().toString(), email, numberOfTopics);
        mDatabase.child("forum").child("titles").child(String.valueOf(numberOfTopics)).setValue(newTopic);

        long currentTime = Calendar.getInstance().getTime().getTime();

        ForumPost firstPost = new ForumPost(thePost.getText().toString(), email, currentTime);
        mDatabase.child("forum").child("posts").child(String.valueOf(numberOfTopics)).child("0").setValue(firstPost);

        // ID is needed to retrieve posts from database and title to display
        Intent intent = new Intent(this, TopicActivity.class);
        intent.putExtra("ID", numberOfTopics);
        intent.putExtra("title", theTitle.getText().toString());

        startActivity(intent);
    }

    public void toForum(View view) {
        onBackPressed();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
