package com.ezra.elon.technologiaandahzaka.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    class ViewHolder
    {
        TextView textView;

        ViewHolder(View v)
        {
            textView = (TextView) v.findViewById(R.id.menu_name);
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


        viewHolder.textView.setText(menu[i]);

    return row;
    }

}






