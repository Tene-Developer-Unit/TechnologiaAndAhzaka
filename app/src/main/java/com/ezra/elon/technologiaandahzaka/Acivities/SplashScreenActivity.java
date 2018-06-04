package com.ezra.elon.technologiaandahzaka.Acivities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.ezra.elon.technologiaandahzaka.Adapter.Asistent;
import com.ezra.elon.technologiaandahzaka.Adapter.HolderTIT;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import com.ezra.elon.technologiaandahzaka.Adapter.*;
import com.google.gson.Gson;

public class SplashScreenActivity extends Activity {

    Asistent asistent;
    DatabaseReference databaseReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<Report> reportArrayList;
    SharedPreferences mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reportArrayList = new ArrayList<>();
        mPrefs = getPreferences(MODE_PRIVATE);
        asistent = new Asistent();
       // database.setPersistenceEnabled(true);
        databaseReference = database.getReference();
        read_data_for_news();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

/*

    todo: bulid function that read the data base in the node of Report, you can get help from JobByMegamaFragment
    the function return string of json

    */


    void read_data_for_news()
    {

        reportArrayList.add(new Report("","raport 1","subtitle 1","heloo"));
        reportArrayList.add(new Report("","raport 2","subtitle 2","heloo"));
        reportArrayList.add(new Report("","raport 3","subtitle 3","heloo"));



//
//        databaseReference.child("ReportList").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                GenericTypeIndicator<ArrayList<Report_holder>> t = new GenericTypeIndicator<>();
//
//                ArrayList<Report_holder> value = new ArrayList<>();
//
//                for (DataSnapshot child: dataSnapshot.getChildren())//initialize the jobs
//                {
//                    value.add(child.getValue(Report_holder.class));
//                }
//
//                if(value != null || value.size()> 0 )
//                    {
//                        for (int i = 0; i < value.size(); i++) {
//
//                            //value.get(i).getJobDesc();
//
//                           //reportArrayList.add(new Report(value.get(i).image,value.get(i).title,value.get(i).subtitle,value.get(i).text));
//
//
//                        }
//
//                    }
//
//
//                }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(reportArrayList);
        prefsEditor.putString("ReportList", json);
        prefsEditor.commit();
    }





    /*
    * todo: build function that read the json text and insert it to file.
    * */

}

class Report_holder
{

    String image;
    String title;
    String subtitle;
    String text;
    long time;
    private  Report_holder()
    {

    }

    public Report_holder(String image, String subtitle,String title, String text, long time)
    {
        this.title = title;
        this.subtitle = subtitle;
        this.text = text;
        this.time = time;
       this.image = image;
    }



    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getText() {
        return text;
    }

    public long getTime() {
        return time;
    }


}
