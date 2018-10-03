package com.ezra.elon.technologiaandahzaka.Acivities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefs = getPreferences(MODE_PRIVATE);
       // database.setPersistenceEnabled(true);
      //  read_data_for_news();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();


    }

    void after_get_data()
    {


    }

/*

    todo: bulid function that read the data base in the node of Report, you can get help from JobByMegamaFragment
    the function return string of json

    */


    void read_data_for_news()
    {

//        reportArrayList.add(new Report("https://images.pexels.com/photos/87452/flowers-background-butterflies-beautiful-87452.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260","חטל המציאו פרפר מוות","המערכת הבאה שתחסל את כל בחרי החמאס; פרפר עם נשק קטלני","heloo"));
//        reportArrayList.add(new Report("https://firebasestorage.googleapis.com/v0/b/teneapp-fe33d.appspot.com/o/compnies_logo_for_jobs%2Ffatal.png?alt=media&token=0b0827f1-fdb6-4c71-8a1d-2df8615f5a9c","רשת פתאל מצטרפת למקצוע לחיים","רשת המלונות הגדולה בארץ מצטרפת למקצוע לחיים;מגוון תפקידים שונים שמציעה החברה;","heloo"));
//        reportArrayList.add(new Report("","raport 3","subtitle 3","heloo"));


        mPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final SharedPreferences.Editor prefsEditor = mPrefs.edit();
        final Gson gson = new Gson();




    }





    /*
    * todo: build function that read the json text and insert it to file.
    * */

}


