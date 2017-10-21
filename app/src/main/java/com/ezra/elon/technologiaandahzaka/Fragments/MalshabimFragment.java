package com.ezra.elon.technologiaandahzaka.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezra.elon.technologiaandahzaka.Adapter.MalshabimFragmentPageAddapter;
import com.ezra.elon.technologiaandahzaka.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MalshabimFragment extends Fragment {


    public MalshabimFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  rootview =inflater.inflate(R.layout.fragment_malshabim, container, false);

        ViewPager PagerView = (ViewPager) rootview.findViewById(R.id.view_pager_malshabim_fragment);
        MalshabimFragmentPageAddapter addapter = new MalshabimFragmentPageAddapter(getActivity().getSupportFragmentManager());

        PagerView.setAdapter(addapter);

        TabLayout tb = (TabLayout) rootview.findViewById(R.id.tab_layout_malshabim_fragment);
        tb.setupWithViewPager(PagerView);

        return rootview;
    }



}
