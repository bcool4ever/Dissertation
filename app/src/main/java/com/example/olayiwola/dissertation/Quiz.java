package com.example.olayiwola.dissertation;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.firebase.client.Firebase;

import java.nio.channels.WritableByteChannel;


public class Quiz extends AppCompatActivity {

    int topicid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_quiz);
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
        Intent intent = getIntent();
        setTitle(intent.getStringExtra("topic title"));
        topicid = Integer.parseInt(intent.getStringExtra("topic position"));
        WebView myWebView = (WebView)findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
/*
        try{
            //SQLiteDatabase mySQL = this.openOrCreateDatabase("Notes",MODE_PRIVATE,null);
            Firebase ref = new Firebase("https://notedsdb.firebaseio.com/");
            mySQL.execSQL("CREATE TABLE IF NOT EXISTS topicQuiz (id INTEGER, url VARCHAR)");
            mySQL.execSQL("INSERT INTO topicQuiz (id,url) VALUES (1,'http://www.gradegorilla.com/AQA_Mains2/A_Mains1.php')");
            mySQL.execSQL("INSERT INTO topicQuiz (id,url) VALUES (2,'http://www.gradegorilla.com/AQA_Mains2/A_Mains1.php')");
            mySQL.execSQL("INSERT INTO topicQuiz (id,url) VALUES (3,'http://www.gradegorilla.com/AQA_Mains2/A_Mains1.php')");
            mySQL.execSQL("INSERT INTO topicQuiz (id,url) VALUES (4,'http://gradegorilla.com/E_Particles2/E_Particles1.php')");
            mySQL.execSQL("INSERT INTO topicQuiz (id,url) VALUES (5,'http://gradegorilla.com/E_light2/E_light1.php')");
            mySQL.execSQL("INSERT INTO topicQuiz (id,url) VALUES (6,'http://gradegorilla.com/E_WavesE2/E_wavesE1.php')");
            mySQL.execSQL("INSERT INTO topicQuiz (id,url) VALUES (7,'http://gradegorilla.com/OCR_Forces2/O_Forces1.php')");
            mySQL.execSQL("INSERT INTO topicQuiz (id,url) VALUES (8,'http://gradegorilla.com/E_Energy2_2/E_Energy1.php')");
            mySQL.execSQL("INSERT INTO topicQuiz (id,url) VALUES (9,'http://gradegorilla.com/OCR_Mains2/O_Mains1.php')");
            mySQL.execSQL("INSERT INTO topicQuiz (id,url) VALUES (10,'http://gradegorilla.com/E_Radioactivity3/E_Radioactivity1.php')");
            //mySQL.execSQL("DELETE FROM topicQuiz");
            Cursor c = mySQL.rawQuery("SELECT * FROM topicQuiz where id ='"+(topicid+1)+"'",null);
            int idIndex = c.getColumnIndex("id");
            int urlIndex = c.getColumnIndex("url");
            c.moveToFirst();
               myWebView.setWebViewClient(new WebViewClient());
            Log.i("Tapped URL: ",c.getString(urlIndex));
               myWebView.loadUrl(c.getString(urlIndex));
            subjectTopic topic = new subjectTopic(1,"http://www.gradegorilla.com/AQA_Mains2/A_Mains1.php");
            ref.setValue(topic);
        }catch (Exception e){
            e.printStackTrace();

        }*/

    }

}
