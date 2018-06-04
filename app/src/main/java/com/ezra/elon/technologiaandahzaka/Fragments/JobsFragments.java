package com.ezra.elon.technologiaandahzaka.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ezra.elon.technologiaandahzaka.Acivities.MainActivity;
import com.ezra.elon.technologiaandahzaka.Adapter.Asistent;
import com.ezra.elon.technologiaandahzaka.Adapter.CarouselPagerAdapter;
import com.ezra.elon.technologiaandahzaka.Adapter.GridViewAdapter;
import com.ezra.elon.technologiaandahzaka.Adapter.GridViewAdapterForJobs;
import com.ezra.elon.technologiaandahzaka.Adapter.HolderTIT;
import com.ezra.elon.technologiaandahzaka.Adapter.ItemFragment;
import com.ezra.elon.technologiaandahzaka.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 *
 * fragment to display the list of megamot First page
 */
public class JobsFragments extends Fragment {

    ArrayList<HolderTIT> holderTITArrayList = new ArrayList<>();
    int position;
    public final static int LOOPS = 1000;
    public CarouselPagerAdapter adapter;
    public static ViewPager pager;
    int[] image = new int[] {R.drawable.mechenic_item, R.drawable.car_item,R.drawable.electronic_item,R.drawable.electricity_item,R.drawable.toon_item} ;
    FragmentTransaction ft;
    GridView gridView;

    ArrayList<String> companies_name = new ArrayList<>();
    public ArrayList<HolderTIT> compnieAndimage;
String compney_name;
    DatabaseReference mDatabase;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    View rootview;
    public JobsFragments() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        if(true)//Asistent.isNetworkConnected(getContext()))//ther is an internet connection
        {
            rootview = inflater.inflate(R.layout.simple_gridview, container, false);

            mDatabase = FirebaseDatabase.getInstance().getReference();//get the refference of the databse
            DatabaseReference companyLogo = mDatabase.child("CompaniesList");
            DatabaseReference JobsCompanyList = mDatabase.child("Jobs");

         companyLogo.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 compnieAndimage.clear();
                 gridView = (GridView) rootview.findViewById(R.id.maingridview);

                 for(DataSnapshot child: dataSnapshot.getChildren())
                 {
                    compnieAndimage.add(new HolderTIT(child.getKey(),child.child("logo").getValue().toString()));
                 }


                 ListAdapter gv = new GridViewAdapterForJobs(getActivity(), compnieAndimage);
                 gridView.setAdapter(gv);


                 gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                     @Override
                     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Fragment fragment = new JobByMegamaFragment();
                         Bundle bundle = new Bundle();
                         bundle.putString("position",compnieAndimage.get(i).getMaslulName());
                         fragment.setArguments(bundle);
                         ft = getFragmentManager().beginTransaction();
                         ft.setAllowOptimization(true);
                         ft.replace(R.id.frame_layout,fragment);
                         ft.addToBackStack(null);
                         ft.commit();

                     }
                 });



             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });





            compnieAndimage = new ArrayList<>();
            companies_name.clear();
            compnieAndimage.clear();
            JSONObject root = null;







                        //compnieAndimage.add(new HolderTIT(compney_name,child.child("CompaniesList").child(compney_name).child("logo").getValue().toString()));






            Log.i("compnieAnd Size", compnieAndimage.size() + " f");


            ////////////////////////// ON CLICK MEGAMAT LIMUD ///////////////
//
//            Fragment fragment = new JobByMegamaFragment();
         // ft = getActivity().getSupportFragmentManager().beginTransaction();
//            Bundle bundle = new Bundle();
//            bundle.putInt("position", i);// the choosen megama
//            fragment.setArguments(bundle);
//            ft.replace(R.id.frame_layout, fragment);
//            ft.addToBackStack(null);
//            ft.commit();

            //  intent.putExtra("shibutId",i);

            ////////////////////////// ON CLICK MEGAMAT LIMUD ///////////////



        }

    else
        {

            rootview = inflater.inflate(R.layout.webview_news, container, false);
            WebView webView = (WebView) rootview.findViewById(R.id.webview_fragment);
            webView.loadDataWithBaseURL("","אין חיבור לאינטרנט\n בדוק את חיבורי הרשת","","","");

        }


        return rootview;

    }


        void readFromDatabase2()
        {
            mDatabase.child("CompaniesList").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    for (DataSnapshot child : dataSnapshot.getChildren())
                    {

                        for(int i = 0; i < compnieAndimage.size();i++)
                        {

                            if(compnieAndimage.get(i).getMaslulName() == child.getKey())
                            {
                                compnieAndimage.get(i).setTextPath(child.child("logo").getValue().toString());
                            }

                        }

                        Log.i("second load",child.child("logo").getValue().toString());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }


}





            /*
            pager = (ViewPager) rootview.findViewById(R.id.myviewpager);
            DisplayMetrics metrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int pageMargin = ((metrics.widthPixels / 4) * 2);
            pager.setPageMargin(-pageMargin);

            //todo: display the maslulim ArrayList on the listView
            String ShibutzimName[] = getActivity().getApplicationContext().getResources().getStringArray(R.array.megamot);// the list of the megamot

            adapter = new CarouselPagerAdapter(getActivity(), getChildFragmentManager(), image);// include cutome image and text
            pager.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            pager.addOnPageChangeListener(adapter);

            // Set current item to the middle page so we can fling to both
            // directions left and right

            pager.setOffscreenPageLimit(3);
            pager.setCurrentItem(1);

            pager.setCurrentItem(pager.getCurrentItem() + 10);
*/
