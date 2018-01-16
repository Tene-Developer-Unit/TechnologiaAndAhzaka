package com.ezra.elon.technologiaandahzaka.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ezra.elon.technologiaandahzaka.R;

/**
 * Created by elon on 15/01/2018.
 */

public class MainCourseListViewPageAddapter extends PagerAdapter {

    Activity activity;
    String [] images;
    LayoutInflater inflater;

    public MainCourseListViewPageAddapter(Activity activity, String [] images)
    {
        this.activity = activity;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(View container, int position) {

        inflater =   (LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.course_view_item_main_screen, (ViewGroup) container,false);

        ImageView imageView = (ImageView)view.findViewById(R.id.coures_view_item_imageView);


        return super.instantiateItem(container, position);
    }
}
