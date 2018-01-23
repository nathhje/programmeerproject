package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class PostActivity extends AppCompatActivity {

    long numberOfPosts;
    EditText thePost;
    String email;
    long ID;
    String title;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final Intent intent = getIntent();
        ID = (long) intent.getSerializableExtra("ID");
        title = intent.getStringExtra("title");

        thePost = findViewById(R.id.newPost);

        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        keepTrackOfPosts();
    }

    public void keepTrackOfPosts() {

        ChildEventListener mChildEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                numberOfPosts = dataSnapshot.getChildrenCount();
                Log.i("the number of children", String.valueOf(numberOfPosts));
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

        Query mQuery = mDatabase.child("forum").child("posts");
        mQuery.addChildEventListener(mChildEventListener);

    }

    public void toTopic(View view) {

        long currentTime = Calendar.getInstance().getTime().getTime();
        Log.i(String.valueOf(currentTime), "toTopic: ");
        ForumPost newPost = new ForumPost(thePost.getText().toString(), email, currentTime);
        mDatabase.child("forum").child("posts").child(String.valueOf(ID)).child(String.valueOf(numberOfPosts)).setValue(newPost);

        Intent intent = new Intent(this, TopicActivity.class);
        intent.putExtra("ID", ID);
        intent.putExtra("title", title);

        startActivity(intent);
    }

    public void backToTopic(View view) {

        Intent intent = new Intent(this, TopicActivity.class);
        intent.putExtra("ID", ID);
        intent.putExtra("title", title);

        startActivity(intent);
    }
}
