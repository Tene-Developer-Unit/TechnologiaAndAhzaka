package com.ezra.elon.technologiaandahzaka.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.R;

/**
 * Created by elon on 04/09/2017.
 */

public class CostumeAddapter extends ArrayAdapter<String> {

    Context context;
    String [] objects;
    public CostumeAddapter(Context context, String[] objects) {
        super(context, R.layout.list_view_item, objects);
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_view_item, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.list_view_item_text_view);

        textView.setText(objects[position]);

        return rowView;

    }
}
