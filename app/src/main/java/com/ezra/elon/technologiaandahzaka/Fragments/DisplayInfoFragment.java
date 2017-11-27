package com.ezra.elon.technologiaandahzaka.Fragments;


import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.ezra.elon.technologiaandahzaka.Adapter.Asistent;
import com.ezra.elon.technologiaandahzaka.Adapter.HolderTIT;
import com.ezra.elon.technologiaandahzaka.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayInfoFragment extends Fragment {

    HolderTIT arrayList ;
    ViewGroup.LayoutParams size;



    public DisplayInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview;
        String videoUrl;
        rootview = inflater.inflate(R.layout.display_info_title_video_text, container, false);
        MediaController mediacontroller = new MediaController(getActivity());
        ImageView titleFrame = (ImageView) rootview.findViewById(R.id.titleimageRelativelayout);
        arrayList = getArguments().getParcelable("holderer");

         final VideoView videoView = (VideoView) rootview.findViewById(R.id.display_info_video_view);

        TextView title = (TextView) rootview.findViewById(R.id.display_info_text_title);
        WebView infoText  = (WebView) rootview.findViewById(R.id.display_info_text_info);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            infoText.getSettings().setAllowFileAccessFromFileURLs(true);
            infoText.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }
        infoText.setWebViewClient(new WebViewClient());
        infoText.getSettings().setJavaScriptEnabled(true);
        infoText.getSettings().setPluginState(WebSettings.PluginState.ON);
        infoText.getSettings().setLoadWithOverviewMode(false);
        infoText.getSettings().setAllowFileAccess(true);
        infoText.setBackgroundColor(Color.TRANSPARENT);


        //// TODO: 04/09/2017 get the name and display it on the screen, ofcourse use the position
        //JsontoArrayList();
        mediacontroller.show();
        videoView.setMediaController(mediacontroller);

       videoUrl =  arrayList.getUrlVideo();

         titleFrame.setImageDrawable(getResources().getDrawable(R.drawable.rect_red_background));

        if(videoUrl !=  "null")
        {

            //videoView.setVisibility(View.VISIBLE);
            titleFrame.setImageURI(Uri.parse("android.resource://"+ getActivity().getPackageName() + "/drawable/" + videoUrl));

            videoView.setVideoURI(Uri.parse("android.resource://"+ getActivity().getPackageName() + "/raw/" + videoUrl));

        }


        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.requestFocus();
                videoView.start();
                Log.i("video","started");
            }
        });

        title.setText(arrayList.getMaslulName());


            //infoText.setText(Html.fromHtml(Asistent.loadJSONFromAsset(getActivity(), "textFiles/" + arrayList.getTextPath())));
            infoText.loadUrl("file:///android_asset/textFiles/" + arrayList.getTextPath());


        return rootview;
    }

}
