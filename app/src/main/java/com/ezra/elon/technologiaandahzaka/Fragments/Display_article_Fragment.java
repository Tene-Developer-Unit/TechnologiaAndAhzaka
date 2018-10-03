package com.ezra.elon.technologiaandahzaka.Fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.Adapter.HolderTIT;
import com.ezra.elon.technologiaandahzaka.Adapter.Report;
import com.ezra.elon.technologiaandahzaka.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Display_article_Fragment extends Fragment {

    ArrayList<Report> holderTITArrayList = new ArrayList<>();
    int position;
    FragmentTransaction ft;
    Report arrayList;
    String company;
    String starthtml = "<html dir=\"rtl\">\n" + "<body style=\"color: #000\">";
    String endhtml = "</body>\n" + "</html>";

    public Display_article_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview;
        rootview = inflater.inflate(R.layout.fragment_display_article, container, false);
        TextView articletitle = (TextView) rootview.findViewById(R.id.display_article_title);
        WebView textViewDiscription = (WebView) rootview.findViewById(R.id.display_article_text);
        TextView articlesubtitle = (TextView) rootview.findViewById(R.id.display_article_subtitle);
        ImageView imagetitel = (ImageView) rootview.findViewById(R.id.display_article_image);


        arrayList = getArguments().getParcelable("articleholder");//the object with the data of the job we chouse
        articletitle.setText(arrayList.getTitle());
        Picasso.with(getContext()).load(arrayList.getImage()).placeholder(R.drawable.animation_loading).into(imagetitel, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });;// it used an outside librarry called Picaso

        textViewDiscription.getSettings().setJavaScriptEnabled(true);
        textViewDiscription.loadDataWithBaseURL(null,starthtml + arrayList.getContent_report() + endhtml,null,"utf-8",null);
        articlesubtitle.setText(arrayList.getSubtitle());

        //textViewDiscription.loadUrl("https://www.codota.com/android/methods/android.webkit.WebView/loadDataWithBaseURL");
        //textViewDiscription.setBackgroundColor(Color.TRANSPARENT);




        return  rootview;


    }

}
