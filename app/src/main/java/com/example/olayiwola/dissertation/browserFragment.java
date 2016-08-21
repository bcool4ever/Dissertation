package com.example.olayiwola.dissertation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by OLAYIWOLA on 24/07/2016.
 */
public class browserFragment extends Fragment {
    WebView cBrowser;
    String uri;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.my_pager, container, false);
        //inflating fragment with my pager design layout
        cBrowser = (WebView)view.findViewById(R.id.my_browser);
        cBrowser.setWebViewClient(new WebViewClient());
        cBrowser.loadUrl(uri);
        return view;
    }
    //method used by page adapter for creating new fragment
    //After initializing the browser, we start passing the Resource indicator uri to it
    public static Fragment newInstance(String uri){
        browserFragment f = new browserFragment();
        f.uri = uri;
        return f;
    }

}
//http://www.whycouch.com/2012/12/the-simplest-way-to-use-viewpager.html