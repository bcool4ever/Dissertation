package com.example.olayiwola.dissertation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class NewsToday extends AppCompatActivity {
    ArrayList<String> newsTitle = new ArrayList<String>();
    ArrayList<String> newsLink = new ArrayList<String>();
    Elements newsBody;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ProgressDialog mProgressDialog;
    String chosenURL = "";
    String target = "";
    int clickedPaper = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_news_today);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Intent newsIntent = new Intent(NewsToday.this,NewsDetails.class);
        listView = (ListView) findViewById(R.id.listView2);
        final ImageButton imgVanguard = (ImageButton) findViewById(R.id.imgVanguard);
        final ImageButton imgPunch = (ImageButton) findViewById(R.id.imgPunch);
        final ImageButton imgNation = (ImageButton) findViewById(R.id.imgNation);
        final ImageButton imgPtimes = (ImageButton) findViewById(R.id.imgPtimes);
        final ImageButton imgSahara = (ImageButton) findViewById(R.id.imgSahara);

        final ArrayList<String>newsURLS = new ArrayList<>();
        newsURLS.add("http://www.vanguardngr.com/");
        newsURLS.add("http://www.punchng.com/");
        newsURLS.add("http://www.thenationonlineng.net/");
        newsURLS.add("http://www.premiumtimesng.com/category/news/headlines");
        newsURLS.add("http://www.saharareporters.com/");
        final ArrayList<String>targetElement = new ArrayList<>();
        targetElement.add("span[class=rtp-latest-news-title]");
        targetElement.add("div[class=item-details]");
        targetElement.add("li[class=post-data]");
        targetElement.add("div[class=a-story]");
        targetElement.add("span[class=block-module-content-header-title]");
        //TO DO: CHANGE ARRAYLIST TO MAP, TO REDUCE LINE OF CODE

        imgVanguard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickedPaper = Integer.parseInt(imgVanguard.getTag().toString());
                target = targetElement.get(clickedPaper-1).toString();
                chosenURL = newsURLS.get(clickedPaper-1).toString();
                final Title fetchTitle = new Title();
                fetchTitle.execute();
                arrayAdapter = new ArrayAdapter<String>(NewsToday.this, android.R.layout.simple_list_item_1, newsTitle);

            }
        });
        imgPunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedPaper = Integer.parseInt(imgPunch.getTag().toString());
                chosenURL = newsURLS.get(clickedPaper-1).toString();
                target = targetElement.get(clickedPaper-1).toString();
                Title fetchTitle = new Title();
                fetchTitle.execute();
                arrayAdapter = new ArrayAdapter<String>(NewsToday.this, android.R.layout.simple_list_item_1, newsTitle);
            }
        });
            imgNation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedPaper = Integer.parseInt(imgNation.getTag().toString());
                    chosenURL = newsURLS.get(clickedPaper-1).toString();
                    target = targetElement.get(clickedPaper-1).toString();
                    Title fetchTitle = new Title();
                    fetchTitle.execute();
                    arrayAdapter = new ArrayAdapter<String>(NewsToday.this, android.R.layout.simple_list_item_1, newsTitle);
                }
            });
            imgPtimes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedPaper = Integer.parseInt(imgPtimes.getTag().toString());
                    chosenURL = newsURLS.get(clickedPaper-1).toString();
                    target = targetElement.get(clickedPaper-1).toString();
                    Title fetchTitle = new Title();
                    fetchTitle.execute();
                    arrayAdapter = new ArrayAdapter<String>(NewsToday.this, android.R.layout.simple_list_item_1, newsTitle);
                }
            });
            imgSahara.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedPaper = Integer.parseInt(imgSahara.getTag().toString());
                    chosenURL = newsURLS.get(clickedPaper - 1).toString();
                    target = targetElement.get(clickedPaper - 1).toString();
                    Title fetchTitle = new Title();
                    fetchTitle.execute();
                    arrayAdapter = new ArrayAdapter<String>(NewsToday.this, android.R.layout.simple_list_item_1, newsTitle);

                }
            });
            //arrayAdapter.notifyDataSetChanged();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newsIntent.putExtra("News Link", newsLink.get(position));
                NewsToday.this.startActivity(newsIntent);
            }
        });
    }

       public class Title extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                mProgressDialog = new ProgressDialog(NewsToday.this);
                mProgressDialog.setTitle("Fetching News");
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setIndeterminate(false);
                mProgressDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    // Connect to the web site
                    Document document = Jsoup.connect(chosenURL).get();
                    // Get the html document title
                    newsBody = document.select(target);
                    //Log.i("Content: ",newsBody.toString());
                    newsTitle.clear();
                    newsLink.clear();

                    for (Element link : newsBody) {

                        newsTitle.add(link.text() + "\n");
                        newsLink.add(link.select("a").attr("href").toString() + "\n");

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                mProgressDialog.dismiss();
                return null;
            }

            protected void onPostExecute(String result) {
                listView.setAdapter(arrayAdapter);
            }


        }

}
