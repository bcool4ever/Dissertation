package com.example.olayiwola.dissertation;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final EditText username = (EditText) findViewById(R.id.Email);
        final EditText password = (EditText) findViewById(R.id.Password);
        final Button Login = (Button) findViewById(R.id.btnLogin);

        //initialize by creating instant of firebase auth mechanism
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //initialize the authentication listener, to be used in getting current user if exists
        authStateListener = new FirebaseAuth.AuthStateListener() {
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //Firebase fb = new Firebase("https://notedsdb.firebase.com");
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, TOC.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = username.getText().toString();
                final String pass = password.getText().toString();

                //http://www.androidhive.info/2016/06/android-getting-started-firebase-simple-login-registration-auth/
                //Code pattern copied from above
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter email address",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(),"Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                //If sign in fails, a toast will display the failure
                            if(!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            else{
                                Intent intent = new Intent(getApplicationContext(),addsubject.class);
                                startActivity(intent);
                                //finish();
                            }

                            }
                        });
            }
        });



    }

}
