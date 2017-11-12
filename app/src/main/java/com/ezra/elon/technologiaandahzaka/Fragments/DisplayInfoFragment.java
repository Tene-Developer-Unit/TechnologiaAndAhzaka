package com.ezra.elon.technologiaandahzaka.Fragments;


import android.content.res.Resources;
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
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.ezra.elon.technologiaandahzaka.Adapter.Asistent;
import com.ezra.elon.technologiaandahzaka.Adapter.HolderTIT;
import com.ezra.elon.technologiaandahzaka.R;

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

//    public static DisplayInfoFragment setArrayList_holder(HolderTIT arrayList_holder)
//    {
//        DisplayInfoFragment fragment = new DisplayInfoFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("holder", arrayList_holder);
//        fragment.setArguments(bundle);
//        return fragment;
//    }

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
        arrayList = getArguments().getParcelable("holderer");

         final VideoView videoView = (VideoView) rootview.findViewById(R.id.display_info_video_view);

        TextView title = (TextView) rootview.findViewById(R.id.display_info_text_title);
        TextView infoText  = (TextView) rootview.findViewById(R.id.display_info_text_info);



        //// TODO: 04/09/2017 get the name and display it on the screen, ofcourse use the position
        //JsontoArrayList();
        mediacontroller.show();
        videoView.setMediaController(mediacontroller);

       videoUrl =  arrayList.getUrlVideo();





        if(videoUrl !=  "null")
        {
            videoView.setVisibility(View.VISIBLE);
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
//
        infoText.setText(Html.fromHtml(Asistent.loadJSONFromAsset(getActivity(), "textFiles/" + arrayList.getTextPath())));


        return rootview;
    }

}
