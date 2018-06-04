package com.ezra.elon.technologiaandahzaka.Fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.Acivities.MainActivity;
import com.ezra.elon.technologiaandahzaka.Adapter.Asistent;
import com.ezra.elon.technologiaandahzaka.Adapter.CostumeAddapter;
import com.ezra.elon.technologiaandahzaka.Adapter.HolderTIT;
import com.ezra.elon.technologiaandahzaka.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class JobInfoFragment extends Fragment {

    ArrayList<HolderTIT> holderTITArrayList = new ArrayList<>();
    int position;
    FragmentTransaction ft;
    HolderTIT arrayList;
    String starthtml = "<html dir=\"rtl\">\n" + "<body style=\"color: #0060C6\">";
    String endhtml = "</body>\n" + "</html>";

    public JobInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview;
        rootview = inflater.inflate(R.layout.job_info_fragment, container, false);
        TextView textViewJobName = (TextView) rootview.findViewById(R.id.jobname_textView);
        FloatingActionButton floatingActionButton = (FloatingActionButton) rootview.findViewById(R.id.sendRequest);

    floatingActionButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/email");

            intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"kshiran32@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT,  "בקשה למשרת "+arrayList.getMaslulName());
            intent.putExtra(Intent.EXTRA_TEXT,  "שם:  מספר תז:");



            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            }



        }
    });


        arrayList = getArguments().getParcelable("jobholder");//the object with the data of the job we chouse

        WebView textViewDiscription = (WebView) rootview.findViewById(R.id.jobDicrip_textview);
        textViewJobName.setText(arrayList.getMaslulName());
        textViewDiscription.loadDataWithBaseURL(null,starthtml + arrayList.getTextPath() + endhtml,null,"utf-8",null);
        //textViewDiscription.loadUrl("https://www.codota.com/android/methods/android.webkit.WebView/loadDataWithBaseURL");
            textViewDiscription.setBackgroundColor(Color.TRANSPARENT);




        return  rootview;

    }



}

