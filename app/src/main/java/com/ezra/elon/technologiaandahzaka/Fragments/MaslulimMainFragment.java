package com.ezra.elon.technologiaandahzaka.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaslulimMainFragment extends Fragment {

    FragmentTransaction ft;
    Fragment fragment;
    final Bundle bundle= new Bundle();

    public MaslulimMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview;

        rootview = inflater.inflate(R.layout.maslulim_main_fragment, container, false);

        ImageButton tichon = (ImageButton) rootview.findViewById(R.id.tichon);

        ImageButton atuda_technologit = (ImageButton) rootview.findViewById(R.id.atuda_technologit);

        ImageButton atuda_academit = (ImageButton) rootview.findViewById(R.id.atuda_academit);


        ImageButton miunim = (ImageButton) rootview.findViewById(R.id.miunim);


        fragment = new MaslulimListFragment();

        ft = getActivity().getSupportFragmentManager().beginTransaction();



        tichon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                lunchFragment("tichon");

            }
        });

        atuda_technologit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lunchFragment("atudatechnologit");

            }
        });

        atuda_academit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lunchFragment("atudaacademit");

            }
        });

        miunim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lunchFragment("miunim");

            }
        });
        return rootview;
    }

    void lunchFragment(String category)
    {

        bundle.putString("category",category);
        fragment.setArguments(bundle);
        ft.replace(R.id.frame_layout,fragment);
        ft.addToBackStack(null);
        ft.commit();

    }

}
