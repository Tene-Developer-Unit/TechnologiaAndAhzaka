package com.ezra.elon.technologiaandahzaka.Adapter;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.ezra.elon.technologiaandahzaka.Fragments.MaslulimListFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.ShibutzimFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.WebViewOnlyFragment;

/**
 * Created by elon on 08/06/2018.
 */

public class MeetHailFragmentPageAddapter extends FragmentStatePagerAdapter {

    FragmentTransaction ft;
    Fragment fragment;
    Bundle bundle;
    public MeetHailFragmentPageAddapter(FragmentManager fm) {
        super(fm);
        ft = fm.beginTransaction();
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:

                 fragment = new WebViewOnlyFragment();

                 bundle = new Bundle();
                bundle.putString("url","file:///android_asset/logitechWeb/landingpage/meetthehail.html");
                fragment.setArguments(bundle);
                return fragment;

            case 1:
                 fragment = new WebViewOnlyFragment();

                 bundle = new Bundle();
                bundle.putString("url","file:///android_asset/logitechWeb/landingpage/comander_info.html");
                fragment.setArguments(bundle);
                return fragment;

            case 2:
                fragment = new WebViewOnlyFragment();

                bundle = new Bundle();
                bundle.putString("url","file:///android_asset/httpFiles/teudatlohem.html");
                fragment.setArguments(bundle);
                return fragment;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position)
        {
            case 0:
                return "הכר את החיל";
           case 1:
                return "דבר הקטנא\"ר";
            case 2:
                return "תעודת לוחם";
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
