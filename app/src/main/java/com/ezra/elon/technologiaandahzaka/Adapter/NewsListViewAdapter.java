package com.ezra.elon.technologiaandahzaka.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.R;

import java.util.ArrayList;

/**
 * Created by elon on 02/05/2018.
 *
 * adapter for the news listview
 *
 * discribe the item
 *
 *
 *
 *
 */

public class NewsListViewAdapter extends BaseAdapter {



   Context context;
ArrayList<Report> reportArrayList;

   private class ViewHolder
   {
       TextView TitleTextView;
       ImageView imageImageView;
       TextView SubTitleTextView;


       ViewHolder(View v)
       {
           TitleTextView = (TextView) v.findViewById(R.id.item_listview_news_title);
           imageImageView = (ImageView) v.findViewById(R.id.item_listview_news_image);
            SubTitleTextView = (TextView) v.findViewById(R.id.item_listview_news_subtitle);
       }

   }

   public NewsListViewAdapter(Context context, ArrayList<Report> reports)
   {

       this.context=context;
       reportArrayList = new ArrayList<>();

       reportArrayList = reports;

           // FUNCTION THAT GET THE INFO FROM THE SERVER
         GetInfoFromServer();
   }

    @Override
    public int getCount() {
        return reportArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return reportArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        View row = convertView;  //קוראים לVIEW
        ViewHolder holder = null;

        if(row == null){

            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inf.inflate(R.layout.item_listview_news,parent,false);

            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)row.getTag();
        }
        Report temp = reportArrayList.get(position);
        //holder.imageImageView.setImageResource(temp.getImage());
        holder.TitleTextView.setText(temp.getTitle());
        holder.SubTitleTextView.setText(temp.getSubtitle());

        return row;

    }

    void GetInfoFromServer()
    {

        Report report;

        String[] title = {"report 1","report 2", "report 3","report 4"};
        String[] subtitle = {"dddsdsdsds","dsdsdsd","dsdsdsds", "gkglojka"};
        String[] content_report = {"lafijewfhuwehfiu","asdfjhaskjdhf","kkkkkfakj","hajskdlfdf"};
        for(int i = 0; i<4; i++)
        {

             //report = new Report(reportArrayList.get(i).getImage(),reportArrayList.get(i).getTitle(),reportArrayList.get(i).getSubtitle(),reportArrayList.get(i).getContent_report());

            // reportArrayList.add(report);
        }

    }

}

