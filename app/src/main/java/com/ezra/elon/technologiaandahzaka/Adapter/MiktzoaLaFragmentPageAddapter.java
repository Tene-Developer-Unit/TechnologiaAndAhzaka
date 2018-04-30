package com.ezra.elon.technologiaandahzaka.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.ezra.elon.technologiaandahzaka.Acivities.MainActivity;
import com.ezra.elon.technologiaandahzaka.Fragments.JobsFragments;
import com.ezra.elon.technologiaandahzaka.Fragments.MaslulimListFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.MiktzoaMaslulimFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.ShibutzimFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.WebViewOnlyFragment;
import com.ezra.elon.technologiaandahzaka.R;



public class MiktzoaLaFragmentPageAddapter extends FragmentStatePagerAdapter{

Context context;


    public MiktzoaLaFragmentPageAddapter(FragmentManager fm,Context context) {
        super(fm);



    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                Fragment fragment = new WebViewOnlyFragment();

                Bundle bundle = new Bundle();
                bundle.putString("url","file:///android_asset/httpFiles/MiktzoaLahim.html");
                fragment.setArguments(bundle);

                WebView webView = WebViewOnlyFragment.newInstance(bundle).webView;

             //   webView.setBackgroundColor(Color.RED);


                return fragment;


            case 1:

                return new MiktzoaMaslulimFragment();

            case 2:
                return new JobsFragments();

            case 3:
                Fragment fragment2 = new WebViewOnlyFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("url","https://goo.gl/forms/7oSmvhRWWDybn26Z2");
                fragment2.setArguments(bundle2);
                return fragment2;
        }

        return null;
    }


    @Override
    public CharSequence getPageTitle(int position){

        switch(position)
        {
            case 0:
                return "רקע";
            case 1:
                return "מסלולים";
            case 2:
                return "השמה";
            case 3:
                return "שאלון";
        }

        return null;
    }


    @Override
    public int getCount() {
        return 4;
    }
    @Override
    public Parcelable saveState() {
        return null;
    }



}
