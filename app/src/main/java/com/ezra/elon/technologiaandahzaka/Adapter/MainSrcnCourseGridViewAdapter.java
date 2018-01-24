package com.ezra.elon.technologiaandahzaka.Adapter;

        import android.content.Context;
        import android.net.Uri;
        import android.support.annotation.NonNull;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.ezra.elon.technologiaandahzaka.R;

        import java.util.ArrayList;

/**
 * Created by elon on 27/08/2017.
 */

public class MainSrcnCourseGridViewAdapter extends ArrayAdapter<Asistent.itemHolder>   {

    Context context;
    ArrayList<Asistent.itemHolder> menu = new ArrayList<>();

        public MainSrcnCourseGridViewAdapter(@NonNull Context context, ArrayList<Asistent.itemHolder> templist) {
                super(context, 0, templist);

        }


        /*
        this class ViewHolder holde the view

        do not Touch

         */
    public class ViewHolder
    {

        TextView textView;
        ImageView imageView;

        ViewHolder(View v)
        {
            textView = (TextView) v.findViewById(R.id.coures_view_item_main_screen_title);
            imageView = (ImageView) v.findViewById(R.id.coures_view_item_imageView);
        }

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {

        View row = view;
        ViewHolder viewHolder = null;
        Asistent.itemHolder itemHolder = getItem(i);

        if (row == null) {

            //LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = LayoutInflater.from(getContext()).inflate(R.layout.course_view_item_main_screen, viewGroup, false);


            viewHolder = new ViewHolder(row);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) row.getTag();
        }
              //  viewHolder.imageView.setImageResource(R.drawable.background_item_toon);

          viewHolder.imageView.setImageURI(Uri.parse("android.resource://"+ getContext().getPackageName() + "/drawable/" + itemHolder.getImage()));
        viewHolder.textView.setText("hhhhhhh");

        return row;
    }



}






