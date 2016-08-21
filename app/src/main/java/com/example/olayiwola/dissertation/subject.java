package com.example.olayiwola.dissertation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import data.subjectData;

public class subject extends AppCompatActivity {

    Firebase fb;
    ListView lv;
    ArrayList<String> sub = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView)findViewById(R.id.subjectsList);
        Firebase.setAndroidContext(this);
        fb = new Firebase(addsubject.DB_URL+"Subjects");
        this.retrieve();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String subject = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(subject.this,TOC.class);
                intent.putExtra("Subject",subject);
                startActivity(intent);
            }
        });
    }

    private void retrieve(){
        fb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getUpdate(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    private void getUpdate(DataSnapshot snapshot){
        sub.clear();
        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
            subjectData sData = new subjectData();
            sData.setTitle(dataSnapshot.getKey());
            sub.add(sData.getTitle());
        }
        if(sub.size() > 0){
            ArrayAdapter arrayAdapter = new ArrayAdapter(subject.this,android.R.layout.simple_list_item_1,sub);
            lv.setAdapter(arrayAdapter);
        }
    }

}
