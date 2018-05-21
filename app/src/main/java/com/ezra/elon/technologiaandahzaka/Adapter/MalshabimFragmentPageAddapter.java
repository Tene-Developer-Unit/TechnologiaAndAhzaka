package com.ezra.elon.technologiaandahzaka.Adapter;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.ezra.elon.technologiaandahzaka.Fragments.ComunicateFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.MainMenuFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.MaslulimListFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.ShibutzimFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.WebViewOnlyFragment;
import com.ezra.elon.technologiaandahzaka.R;

import static android.R.attr.fragment;

/**
 *
 *
 *
 */

public class MalshabimFragmentPageAddapter extends FragmentStatePagerAdapter {
    FragmentTransaction ft;
    public MalshabimFragmentPageAddapter(FragmentManager fm) {
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
              Fragment fragment = new WebViewOnlyFragment();

              Bundle bundle = new Bundle();
              bundle.putString("url","file:///android_asset/httpFiles/Malshabim.html");
              fragment.setArguments(bundle);
              return fragment;

          case 1:
              return new MaslulimListFragment();
          case 2:
              return new ShibutzimFragment();
      }
  return null;

    }

    @Override
    public CharSequence getPageTitle(int position){

        switch(position)
        {
            case 1:
            return "מסלולים";
            case 2:
            return "קורסים";
            case 0:
            return "רקע";
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
