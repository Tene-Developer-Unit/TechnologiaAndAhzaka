package com.ezra.elon.technologiaandahzaka.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.R;

import java.util.ArrayList;

/**
 * Created by elon on 17/05/2018.
 */

public class CourseGridViewAddapter extends BaseAdapter {



    Context context;
    ArrayList<CourseButton> randomCourseArrayList;

    private class ViewHolder
    {
        TextView TitleTextView;
        ImageView imageImageView;

        ViewHolder(View v)
        {
            TitleTextView = (TextView) v.findViewById(R.id.coures_view_item_main_screen_title);
            imageImageView = (ImageView) v.findViewById(R.id.coures_view_item_imageView);
        }

    }

    public CourseGridViewAddapter(Context context, ArrayList<CourseButton> randomCourseArrayList)
    {

        this.context=context;
        this.randomCourseArrayList = randomCourseArrayList;
        // FUNCTION THAT GET THE INFO FROM THE SERVER
        GetInfoFromServer();
    }



    @Override
    public int getCount() {
        return randomCourseArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return randomCourseArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        View row = convertView;
        CourseGridViewAddapter.ViewHolder holder = null;

        if(row==null){

            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inf.inflate(R.layout.course_view_item_main_screen,parent,false);

            holder = new CourseGridViewAddapter.ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (CourseGridViewAddapter.ViewHolder)row.getTag();
        }


        CourseButton temp = randomCourseArrayList.get(position);
        holder.imageImageView.setImageResource(temp.getImage());
        holder.TitleTextView.setText(temp.getTitle());
        //setImageURI(Uri.parse("android.resource://"+ context.getApplicationContext().getPackageName() + "/drawable/" + temp.getImage()));

        return row;

    }
    void GetInfoFromServer()
    {

        CourseButton courseButton;

              for(int i = 0; i<4; i++)
        {

            courseButton = new CourseButton(
                    randomCourseArrayList.get(i).getImage(),randomCourseArrayList.get(i).getTitle());
            randomCourseArrayList.add(courseButton);
        }

    }

}

