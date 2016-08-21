package com.example.olayiwola.dissertation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import data.subjectData;
import data.topicData;
public class addTopics extends AppCompatActivity {
    Firebase fb;
    EditText txtTitle;
    ArrayList<String> titles = new ArrayList<String>();
    Button b;
    Spinner sp;
    String selSubject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_topics);
        Firebase.setAndroidContext(this);
        fb = new Firebase(addsubject.DB_URL+"/Subjects");
        txtTitle = (EditText)findViewById(R.id.txtTitle);
        b = (Button)findViewById(R.id.btnSave);
        sp = (Spinner)findViewById(R.id.subjectSpinner);


        this.retrieveSubjects();
       sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               selSubject = parent.getItemAtPosition(position).toString();
               Log.i("Chosen Subject",selSubject);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selSubject != ""){
                    addTopics(txtTitle.getText().toString());
                    Log.i("Chosen Subject", selSubject);
                    txtTitle.setText("");
                }
            }
        });
    }

    private void addTopics(String title){
        topicData tData = new topicData();
        tData.setTopicName(title);
        fb.child(selSubject).push().setValue(tData);
    }
    private void retrieveSubjects(){
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
    private void getUpdate(DataSnapshot dataSnapshot){
        titles.clear();
        for(DataSnapshot data : dataSnapshot.getChildren()){
            //subjectData sd = data.getKey();
            titles.add(String.valueOf(data.getKey()));
        }
        if(titles.size() > 0){
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,titles);
            sp.setAdapter(adapter);
        }
    }
}
