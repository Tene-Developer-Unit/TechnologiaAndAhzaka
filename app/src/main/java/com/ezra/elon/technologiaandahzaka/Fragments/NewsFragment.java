package com.ezra.elon.technologiaandahzaka.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * @author elon
 *
 * show an article!
 *
 */
public class NewsFragment extends Fragment {

    DatabaseReference mDatabase;
    String URL;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  rootview =inflater.inflate(R.layout.webview_news, container, false);
        final WebView webView = (WebView) rootview.findViewById(R.id.webview_fragment);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("URLS").child("newspage").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                URL =  dataSnapshot.getValue(String.class);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setPluginState(WebSettings.PluginState.ON);
                webView.getSettings().setAllowFileAccess(false);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(URL);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
            return rootview;

    }


}
