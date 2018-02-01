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
 * Used to create a new post in a topic.
 */

public class PostActivity extends AppCompatActivity {

    int counter = 0;
    EditText thePost;
    String email;
    long ID;
    String title;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final Intent intent = getIntent();
        ID = (long) intent.getSerializableExtra("ID");
        title = intent.getStringExtra("title");

        thePost = findViewById(R.id.newPost);

        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        keepTrackOfPosts();
    }

    // checks number of posts to determine next ID
    public void keepTrackOfPosts() {

        ChildEventListener mChildEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                counter += 1;
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

        Query mQuery = mDatabase.child("forum").child("posts").child(String.valueOf(ID));
        mQuery.addChildEventListener(mChildEventListener);
    }

    // creates new post
    public void toTopic(View view) {

        long currentTime = Calendar.getInstance().getTime().getTime();

        ForumPost newPost = new ForumPost(thePost.getText().toString(), email, currentTime);
        mDatabase.child("forum").child("posts").child(String.valueOf(ID)).child(String.valueOf(counter)).setValue(newPost);

        Intent intent = new Intent(this, TopicActivity.class);
        intent.putExtra("ID", ID);
        intent.putExtra("title", title);

        startActivity(intent);
    }

    //  ID is needed to retrieve posts from database and title to display
    public void backToTopic(View view) {

        Intent intent = new Intent(this, TopicActivity.class);
        intent.putExtra("ID", ID);
        intent.putExtra("title", title);

        startActivity(intent);
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
