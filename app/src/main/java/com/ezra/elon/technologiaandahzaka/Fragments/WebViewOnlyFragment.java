package com.ezra.elon.technologiaandahzaka.Fragments;


import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewOnlyFragment extends Fragment {

    String URL;
   public WebView webView;
    View  rootview;

    public WebViewOnlyFragment() {
        // Required empty public constructor
    }

   public static WebViewOnlyFragment newInstance(Bundle bundle)
   {
        WebViewOnlyFragment fragment = new WebViewOnlyFragment();

       fragment.setArguments(bundle);
       return fragment;
   }



    public WebView getWebView()
    {
        return webView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final AnimationDrawable loading_anim;

          rootview =inflater.inflate(R.layout.webview_news, container, false);
        webView = (WebView) rootview.findViewById(R.id.webview_fragment);


        webView.setBackgroundResource(R.drawable.animation_loading);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webView.getSettings().setAllowFileAccessFromFileURLs(true);
            webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }




            URL = getArguments().getString("url");


                webView.setWebViewClient(new WebViewClient());
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setPluginState(WebSettings.PluginState.ON);
                webView.getSettings().setLoadWithOverviewMode(false);
                webView.getSettings().setAllowFileAccess(true);





        webView.loadUrl(URL);

        webView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int progress) {
                // Log.d(TAG + " 185", "Load Status: " + progress);
                if (progress == 100) {
                    ViewGroup.LayoutParams size = webView.getLayoutParams();

                    size.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    size.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    webView.setLayoutParams(size);
                    webView.setBackgroundColor(Color.TRANSPARENT);
                   // webView.setBackgroundResource(R.drawable.mikbg);

                }
            }});

            return rootview;

        }



}
