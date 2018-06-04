package com.ezra.elon.technologiaandahzaka.Adapter;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.ezra.elon.technologiaandahzaka.Acivities.MainActivity;
import com.ezra.elon.technologiaandahzaka.Fragments.JobsFragments;
import com.ezra.elon.technologiaandahzaka.R;


/*

    this class define the pageviewer.

    it contain the animation of the the scrolling (include size change, screen position)



 */


public class CarouselPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    public final static float BIG_SCALE = 1.5f;
    public final static float SMALL_SCALE = 1.0f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    public static int[] images;
    public static String[] title;
    private Context context;
    private FragmentManager fragmentManager;
    private float scale;


    public CarouselPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm;
        this.context = context;
        getItem(this.getItemPosition(this));
    }
    public CarouselPagerAdapter(Context context, FragmentManager fm, int[] images) {
        super(fm);
        this.fragmentManager = fm;
        this.context = context;
        this.images = images;
        getItem(this.getItemPosition(this));


    }

    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        position ++;
        try {
            if (position == images.length)
                scale = BIG_SCALE;
            else
                scale = SMALL_SCALE;

            position = position % images.length;

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("getItem","" + position);

        return ItemFragment.newInstance(context, position, scale);
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = images.length * JobsFragments.LOOPS;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        try {
            if (positionOffset >= 0f && positionOffset <= 1f) {

                CarouselLinearLayout cur = getRootView(position);
                CarouselLinearLayout next = getRootView(position + 1);

                cur.setScaleBoth(BIG_SCALE - DIFF_SCALE * positionOffset);
                next.setScaleBoth(SMALL_SCALE + DIFF_SCALE * positionOffset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

//    @Override
//    public int getItemPosition(Object object) {
//        return POSITION_UNCHANGED;
//    }

    @SuppressWarnings("ConstantConditions")
    private CarouselLinearLayout getRootView(int position) {
        return (CarouselLinearLayout) fragmentManager.findFragmentByTag(this.getFragmentTag(position))
                .getView().findViewById(R.id.root_container);
    }



    private String getFragmentTag(int position) {
        return "android:switcher:" + JobsFragments.pager.getId() + ":" + (position);
    }
}

