package com.ezra.elon.technologiaandahzaka.Adapter;

import android.graphics.pdf.PdfDocument;
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
import com.ezra.elon.technologiaandahzaka.R;

import static android.R.attr.fragment;

/**
 * Created by elon on 11/09/2017.
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
              return new MaslulimListFragment();
          case 1:
              return new ShibutzimFragment();
      }

  return null;

    }

    @Override
    public CharSequence getPageTitle(int position){

        switch(position)
        {
            case 0:
            return "מסלולים";
            case 1:
            return "אפשרויות שיבוץ";
            case 2:
            return "position2";
        }

        return null;
    }


    @Override
    public int getCount() {

        return 2;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
