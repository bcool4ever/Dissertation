package com.example.olayiwola.dissertation;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.firebase.client.Firebase;

import java.util.ArrayList;

import data.ratingData;


public class TOPIC extends AppCompatActivity {


    ListView listView;
    VideoView videoView;
    RatingBar ratingBar;
    LinearLayout ratingLayout;
    LinearLayout linearLayout;
    Button btnRate;
    Button btnRating;
    SharedPreferences sharedPreferences;
    Button btnComment;
    TextView textView;
    ArrayList<String> topicVideo;
    ArrayAdapter<String> arrayAdapter;
    MediaController mediaController;
    String topicTitle;
    String fileName = "GCSE_Physics_ Atoms.html";
    String filePath = "file:android_asset/";
    String file = filePath + fileName;
    String[] toVisit = {
            file,
            "http://www.google.com",
            "http://www.reddit.com/.compact",
            "http://www.dribbble.com"
    };
    ViewPager viewPager;
    FragmentStatePagerAdapter adapter;
    boolean scrolledOnLastPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_topic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);
        mediaController = new MediaController(this);
        viewPager = (ViewPager) findViewById(R.id.pager);
        btnRate = (Button) findViewById(R.id.btnRate);
        btnComment = (Button) findViewById(R.id.btnComment);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout4);
        ratingLayout = (LinearLayout) findViewById(R.id.ratingLayout);

        //locates the View Pager assigned to XML of this class
        adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return browserFragment.newInstance(toVisit[position]);
                //calls the browserFragment method newInstance to traverse the list of notes content url
                //prepared for selected topic
            }

            @Override
            public int getCount() {
                return toVisit.length;
            }
        };
        viewPager.setAdapter(adapter);

        ViewPager.OnPageChangeListener rListener = new ViewPager.OnPageChangeListener() {

            private int selPageIndex = -1;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // if positionOffset is equal to the last page in the direction
                if (positionOffset == 0F) {
                    int lastPage = adapter.getCount() - 1;
                    if (position == lastPage) {
                        scrolledOnLastPage = true;
                        linearLayout.setVisibility(View.VISIBLE);
                        //android.app.FragmentManager fragmentManager = getFragmentManager();
                        //subject1.ratingFragment rf = new subject1.ratingFragment();
                        //rf.show(fragmentManager, "Rating topic");
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                selPageIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
//wraps the onpage listener into the adapter
        viewPager.addOnPageChangeListener(rListener);
        Intent intent = getIntent();
        topicTitle = intent.getStringExtra("topic title");
        setTitle(topicTitle);
        final String[] videoTitle = {"Atomic Weight and Mass", "Mass and Weight Clarification"};
        listView = (ListView) findViewById(R.id.listView3);
        //list view content of all available video for the specific topic
        videoView = (VideoView) findViewById(R.id.videoView);
        //videoView holds the currently tapped video
        topicVideo = new ArrayList<String>();
        //videos are initialized as an array of string i.e. video file locations
        for (int i = 0; i < videoTitle.length; i++)
            topicVideo.add(videoTitle[i]);
        //video titles are loaded into the array list
        arrayAdapter = new ArrayAdapter<String>(TOPIC.this, android.R.layout.simple_list_item_1, topicVideo);
        //array adapter is used to display the instances of available topic videos
        listView.setAdapter(arrayAdapter);
        //list view of encapsulated topic videos
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //when one of the video title is tapped
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (topicVideo.get(position) == videoTitle[0]) {
                    videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.mass1);
                    mediaController.setAnchorView(videoView);
                    videoView.setMediaController(mediaController);
                    videoView.start();
                } else if (topicVideo.get(position) == videoTitle[1]) {
                    //videoView.suspend();
                    videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.atom1);
                    mediaController = new MediaController(TOPIC.this);
                    mediaController.setAnchorView(videoView);
                    videoView.setMediaController(mediaController);
                    videoView.start();
                }
//TO DO: change to hashmap, so as to use the ID of video for retrieving


            }
        });
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRating();
            }
        });
    }


    public void doRating() {
//On Rating Bar change listener ensures the user sees the rating value passed in real time
        final Dialog d = new Dialog(this);
        d.setTitle("Rate this Topic");
        d.setContentView(R.layout.rating_layout);
        btnRating = (Button)d.findViewById(R.id.btnRating);
        ratingBar = (RatingBar)d.findViewById(R.id.ratingBar);
        textView = (TextView)d.findViewById(R.id.txtValue);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textView.setText("Rating Value: " + String.valueOf(rating));
                sharedPreferences = getApplicationContext().getSharedPreferences("Rating", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat("Rating", rating);
                editor.commit();
            }
        });
        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float val = sharedPreferences.getFloat("Rating", 0);
                addRating(topicTitle, val);
                Toast.makeText(getApplicationContext(),"Thanks for rating "+topicTitle+", value successfully sent",Toast.LENGTH_SHORT).show();
                d.dismiss();
            }
        });
        d.show();
    }
    public void addRating(String title, float val){
        Firebase firebase = new Firebase(addsubject.DB_URL);
        ratingData rd = new ratingData();
        rd.setTitle(title);
        rd.setValue(val);
        //get the subject to rate using intent

        String subjectName = getIntent().getStringExtra("Subject");
        Log.i("HIV CHeck",subjectName+" "+rd.getValue());
        //the child is suppose to be a person, a teacher making the rating
        firebase.child("Ratings").child(subjectName).child(topicTitle).setValue(rd.getValue());
    }
}

