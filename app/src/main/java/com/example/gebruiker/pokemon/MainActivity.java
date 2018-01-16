package com.example.gebruiker.pokemon;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    String email;
    String password;
    String repeatPassword;
    TextView signupError;
    TextView loginError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signupError = (TextView) findViewById(R.id.signuperror);
        loginError = (TextView) findViewById(R.id.loginerror);

        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();
    }

    public void createUser(View view) {

        EditText emailBox = (EditText) findViewById(R.id.email);
        EditText passwordBox = (EditText) findViewById(R.id.password);
        EditText repeatBox = (EditText) findViewById(R.id.repeatpassword);

        email = emailBox.getText().toString();
        password = passwordBox.getText().toString();
        repeatPassword = repeatBox.getText().toString();

        if(TextUtils.isEmpty(email)) {
            email = " ";
        }
        if(TextUtils.isEmpty(password)) {
            password = " ";
        }
        if(TextUtils.isEmpty(repeatPassword)) {
            repeatPassword = " ";
        }

        if(password.equals(repeatPassword)) {
            putUserInDatabase(email, password);
        }
        else {
            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
            signupError.setText("Repeat password doesn't match password.");
        }

    }

    public void putUserInDatabase(final String email, final String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("creating user", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // informs user creation was unsuccesful
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            // message to user varies depending on error made
                            if(password.length()<6){
                                signupError.setText("Password must consist of at least 6 characters.");
                            }
                            if(!email.contains("@") || !email.contains(".")){
                                signupError.setText("Sign in with a valid email.");
                            }
                            else {
                                signupError.setText("Email of password is already in use.");
                            }
                        }

                        // informs user that creation was succesful
                        else {
                            Toast.makeText(MainActivity.this, "created: " + email,
                                    Toast.LENGTH_SHORT).show();
                            logUserIn(email, password);
                        }

                        // ...
                    }
                });
    }

    public void logIn(View view) {

        EditText loginEmail = (EditText) findViewById(R.id.loginemail);
        EditText loginPassword = (EditText) findViewById(R.id.loginpassword);

        // user data is retrieved from EditTexts
        email = loginEmail.getText().toString();
        password = loginPassword.getText().toString();

        // handles lack of user input
        if(TextUtils.isEmpty(email)) {
            email = " ";
        }
        if(TextUtils.isEmpty(password)) {
            password = " ";
        }

        // signs in user
        logUserIn(email, password);
    }

    public void logUserIn(final String email, String password) {
        // attempts to sign in user

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("logging in", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // informs user sign in was unsuccesful
                        if (!task.isSuccessful()) {

                            Log.w("failed to log in", "signInWithEmail", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            // informs user what went wrong
                            loginError.setText("Wrong email or password.");
                        }

                        // informs user sign in was succesful
                        else {
                            Toast.makeText(MainActivity.this, "signed in: " + email,
                                    Toast.LENGTH_SHORT).show();

                            // continues to search screen
                            toTab();
                        }

                        // ...
                    }
                });
    }


    public void toTab() { startActivity(new Intent(this, TabActivity.class));    }
}
