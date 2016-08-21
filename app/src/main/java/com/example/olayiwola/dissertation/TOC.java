package com.example.olayiwola.dissertation;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import android.content.Intent;
import android.app.AlertDialog;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shaded.fasterxml.jackson.annotation.JsonIgnoreProperties;

import data.subjectData;
import data.topicData;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TOC extends AppCompatActivity {

    ListView listView;
    SearchView searchView;
    ArrayAdapter arrayAdapter;
    public ArrayList<String> TOC;
    String pname,subName;
    Firebase tb;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //makes sure my app runs in portrait only
        setContentView(R.layout.activity_toc);
        Intent intent = getIntent();
        listView = (ListView)findViewById(R.id.listView);
        searchView = (SearchView)findViewById(R.id.searchView);
        TextView textView = (TextView)findViewById(R.id.txtProfile);
        TOC = new ArrayList<String>();

        /*TOC.add("Concepts of matter");
        TOC.add("Fundamental and derived quantities and units");
        TOC.add("Position, distance and displacement");
        TOC.add("Mass and weight");
        TOC.add("Time");
        TOC.add("Fluids at rest");
        TOC.add("Motion");
        TOC.add("Speed and velocity");
        TOC.add("Rectilinear acceleration");
        TOC.add("Scalars and vectors");*/
        subName = intent.getStringExtra("Subject");

        Firebase.setAndroidContext(this);
        tb = new Firebase(addsubject.DB_URL+"/Subjects");

        this.retrieve();
        //arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,TOC);


        //listView.setAdapter(arrayAdapter);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(TOC.this,MainActivity.class);
                startActivity(intent);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String Text) {
                arrayAdapter.getFilter().filter(Text);
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String topic = parent.getItemAtPosition(position).toString();
                final int pos = parent.getPositionForView(view);
                //view represents the row of selected item
                new AlertDialog.Builder(TOC.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Choose a mode")
                        .setMessage("Which mode would you like for " + topic)
                        .setPositiveButton("Revision", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(TOC.this, TOPIC.class);
                                intent.putExtra("topic title", topic);
                                intent.putExtra("Subject",subName);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Test", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(TOC.this, Quiz.class);
                                intent.putExtra("topic title", topic);

                                intent.putExtra("topic position", String.valueOf(pos));
                                startActivity(intent);
                                finish();
                            }
                        })
                        .show();

            }
        });
    }
    private void retrieve(){
        tb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getUpdate(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    private void getUpdate(DataSnapshot dataSnapshot){
        TOC.clear();
        DataSnapshot topNames = dataSnapshot.child(subName);
        for(DataSnapshot data : topNames.getChildren()){
            topicData td = (data.child("topicName").getValue(topicData.class));
            TOC.add(td.getTopicName());
        }
        if(TOC.size() > 0){
            arrayAdapter = new ArrayAdapter(TOC.this,android.R.layout.simple_list_item_1,TOC);
            listView.setAdapter(arrayAdapter);
        }
    }
}
