package com.example.olayiwola.dissertation;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
    }
    public void goTOC(View view){
        Intent intent = new Intent(this,subject.class);
        startActivity(intent);
    }
    public void goNews(View view){
        Intent intent = new Intent(this,NewsToday.class);
        startActivity(intent);
    }
    public void goTest(View view){
        Intent intent = new Intent(this,subject.class);
        MainActivity.this.startActivity(intent);
    }
}
