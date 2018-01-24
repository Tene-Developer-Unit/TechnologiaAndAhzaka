package com.ezra.elon.technologiaandahzaka.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.R;

/**
 * Created by elon on 27/08/2017.
 */

public class GridViewAdapter  extends BaseAdapter {

    Context context;
    String menu[];


    public GridViewAdapter(Context applicationContext, String[] main_menu_list) {
        context = applicationContext;
        menu = main_menu_list;

    }


    @Override
    public int getCount() {
        return menu.length;
    }

    @Override
    public Object getItem(int i) {
        return menu[i];
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
        ViewHolder viewHolder = null;

        if (row == null) {

            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inf.inflate(R.layout.logo_item, viewGroup, false);



            viewHolder = new ViewHolder(row);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) row.getTag();
        }

        switch (i)
        {
            case 0:
                viewHolder.imageView.setImageResource(R.drawable.background_item_mechenic);
                break;
            case 1 :
                viewHolder.imageView.setImageResource(R.drawable.background_item_car);
                break;
            case 2:
                viewHolder.imageView.setImageResource(R.drawable.background_item_electronic);
                break;
            case 3:
                viewHolder.imageView.setImageResource(R.drawable.background_item_electricity);
                break;
            case 4:
                viewHolder.imageView.setImageResource(R.drawable.background_item_toon);
                break;


        }

        //viewHolder.textView.setText(menu[i]);

    return row;
    }



}






