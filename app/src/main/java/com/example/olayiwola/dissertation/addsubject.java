package com.example.olayiwola.dissertation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import data.subjectData;

import java.sql.SQLException;
import java.util.ArrayList;

import data.subjectController;

public class addsubject extends AppCompatActivity {

    EditText subName;
    Firebase subject;
    Button b;

    //points to the datastore
    final static String DB_URL = "https://notedsdb.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");
        setContentView(R.layout.activity_addsubject);
        Firebase.setAndroidContext(this);
        subName = (EditText) findViewById(R.id.txtTitle);
        b = (Button) findViewById(R.id.btnSave);
        subject = new Firebase(DB_URL);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSub(subName.getText().toString());
                subName.setText("");
            }
        });
    }
    //instantiate subject class to add data
    private void addSub(String title){
        subjectData sData = new subjectData();
        sData.setTitle(title);

        subject.child("Subjects").child(title).setValue(sData);
    }

}
