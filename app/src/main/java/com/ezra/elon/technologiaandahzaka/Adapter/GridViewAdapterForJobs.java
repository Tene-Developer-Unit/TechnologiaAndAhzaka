package com.ezra.elon.technologiaandahzaka.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by elon on 04/06/2018.
 */

public class GridViewAdapterForJobs extends BaseAdapter {

    Context context;

    ArrayList<HolderTIT> compniesname;

     public GridViewAdapterForJobs(Context applicationContext, ArrayList<HolderTIT> arrayList)
    {

        context = applicationContext;
        compniesname = arrayList;


    }

    @Override
    public int getCount() {
        return compniesname.size();
    }

    @Override
    public Object getItem(int i) {
        return compniesname.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder
    {
        TextView textView;
        ImageView imageView;

        ViewHolder(View v)
        {
            textView = (TextView) v.findViewById(R.id.coures_view_item_main_screen_title);
            imageView = (ImageView) v.findViewById(R.id.logo_item_imageview);
        }

        void invisibleTitle()
        {
            textView.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {

        View row = view;
        GridViewAdapterForJobs.ViewHolder viewHolder = null;

        if (row == null) {

            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inf.inflate(R.layout.logo_item, viewGroup, false);
            viewHolder = new GridViewAdapterForJobs.ViewHolder(row);
            row.setTag(viewHolder);

        } else {
            viewHolder = (GridViewAdapterForJobs.ViewHolder) row.getTag();
        }
          //  viewHolder.imageView.setImageResource(R.drawable.yael);


        Picasso.with(context).load(compniesname.get(i).TextPath).into(viewHolder.imageView, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {


            }

            @Override
            public void onError() {

            }
        });

        return row;
    }
}
