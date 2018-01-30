package com.example.gebruiker.pokemon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Nathalie van Sterkenburg on 11-1-2018.
 *
 * Launch activity with login and register
 */

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    String email;
    String password;
    String repeatPassword;
    TextView signupError;
    TextView loginError;

    EditText emailBox;
    EditText passwordBox;
    EditText repeatBox;
    EditText loginEmail;
    EditText loginPassword;

    Button login;
    Button toRegister;
    Button register;
    Button toLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        signupError = findViewById(R.id.signuperror);
        loginError = findViewById(R.id.loginerror);

        emailBox = findViewById(R.id.email);
        passwordBox = findViewById(R.id.password);
        repeatBox = findViewById(R.id.repeatpassword);
        loginEmail = findViewById(R.id.loginemail);
        loginPassword = findViewById(R.id.loginpassword);

        login = findViewById(R.id.login);
        toRegister = findViewById(R.id.toRegister);
        register = findViewById(R.id.create);
        toLogin = findViewById(R.id.toLogin);

        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();
    }

    public void toRegister(View view) {

        loginError.setText("");
        loginEmail.setVisibility(View.INVISIBLE);
        loginPassword.setVisibility(View.INVISIBLE);
        login.setVisibility(View.INVISIBLE);
        toRegister.setVisibility(View.INVISIBLE);

        emailBox.setVisibility(View.VISIBLE);
        passwordBox.setVisibility(View.VISIBLE);
        repeatBox.setVisibility(View.VISIBLE);
        register.setVisibility(View.VISIBLE);
        toLogin.setVisibility(View.VISIBLE);
    }

    public void toLogin(View view) {

        signupError.setText("");
        emailBox.setVisibility(View.INVISIBLE);
        passwordBox.setVisibility(View.INVISIBLE);
        repeatBox.setVisibility(View.INVISIBLE);
        register.setVisibility(View.INVISIBLE);
        toLogin.setVisibility(View.INVISIBLE);

        loginEmail.setVisibility(View.VISIBLE);
        loginPassword.setVisibility(View.VISIBLE);
        login.setVisibility(View.VISIBLE);
        toRegister.setVisibility(View.VISIBLE);
    }

    public void createUser(View view) {

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

                        // informs user creation was unsuccesful
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            // message to user varies depending on error made
                            if(password.length()<6){
                                signupError.setText("Password must consist of at least 6 characters.");
                                Toast.makeText(MainActivity.this, "APassword must consist of at least 6 characters.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else if(!email.contains("@") || !email.contains(".")){
                                signupError.setText("Sign in with a valid email.");
                                Toast.makeText(MainActivity.this, "Sign in with a valid email.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else {
                                signupError.setText("Email or password is already in use.");
                                Toast.makeText(MainActivity.this, "Email or password is already in use.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        // informs user that creation was succesful
                        else {
                            Toast.makeText(MainActivity.this, "created: " + email,
                                    Toast.LENGTH_SHORT).show();
                            logUserIn(email, password);
                        }
                    }
                });
    }

    public void logIn(View view) {

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
                    }
                });
    }

    public void toTab() { startActivity(new Intent(this, TabActivity.class));    }
}
