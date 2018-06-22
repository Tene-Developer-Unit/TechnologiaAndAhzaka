package com.ezra.elon.technologiaandahzaka.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

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
public class MiktzoaMaslulimFragment extends Fragment {

    ArrayList<HolderTIT> holderTITArrayList = new ArrayList<>();
    ImageButton hasaka;
    ImageButton hachsharot;
    ImageButton mavarim;
    Fragment fragment;

    FragmentTransaction ft;


    public MiktzoaMaslulimFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview;
        rootview = inflater.inflate(R.layout.miktoa_maslulim, container, false);
        hasaka = (ImageButton) rootview.findViewById(R.id.hasaka);
        hachsharot = (ImageButton) rootview.findViewById(R.id.hachsharot);
        mavarim = (ImageButton) rootview.findViewById(R.id.mavarim);

        hasaka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new WebViewOnlyFragment();
                Bundle bundle = new Bundle();
                bundle.putString("url","file:///android_asset/logitechWeb/landingpage/hasaka-info.html");
                fragment.setArguments(bundle);

                ft = getFragmentManager().beginTransaction();
                ft.setAllowOptimization(true);
                ft.replace(R.id.frame_layout,fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        hachsharot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new WebViewOnlyFragment();
                Bundle bundle = new Bundle();
                bundle.putString("url","file:///android_asset/logitechWeb/landingpage/hachsharot-info.html");
                fragment.setArguments(bundle);

                ft = getFragmentManager().beginTransaction();
                ft.setAllowOptimization(true);
                ft.replace(R.id.frame_layout,fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        mavarim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new WebViewOnlyFragment();
                Bundle bundle = new Bundle();
                bundle.putString("url","file:///android_asset/logitechWeb/landingpage/mavarim-info.html");
                fragment.setArguments(bundle);

                ft = getFragmentManager().beginTransaction();
                ft.setAllowOptimization(true);
                ft.replace(R.id.frame_layout,fragment);
                ft.addToBackStack(null);
                ft.commit();


            }
        });


        return  rootview;


    }








}
