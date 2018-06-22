package com.ezra.elon.technologiaandahzaka.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.Adapter.MalshabimFragmentPageAddapter;
import com.ezra.elon.technologiaandahzaka.Adapter.MeetHailFragmentPageAddapter;
import com.ezra.elon.technologiaandahzaka.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetHailFragment extends Fragment {


    public MeetHailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  rootview =inflater.inflate(R.layout.fragment_malshabim, container, false);

        ViewPager PagerView = (ViewPager) rootview.findViewById(R.id.view_pager_malshabim_fragment);
        MeetHailFragmentPageAddapter addapter = new MeetHailFragmentPageAddapter(getActivity().getSupportFragmentManager());

        PagerView.setAdapter(addapter);

        TabLayout tb = (TabLayout) rootview.findViewById(R.id.tab_layout_malshabim_fragment);
        tb.setupWithViewPager(PagerView);

        return rootview;
    }

}