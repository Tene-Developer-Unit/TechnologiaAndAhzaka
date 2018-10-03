package com.ezra.elon.technologiaandahzaka.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.Adapter.Asistent;
import com.ezra.elon.technologiaandahzaka.Adapter.CostumeAddapter;
import com.ezra.elon.technologiaandahzaka.Adapter.CourseGridViewAddapter;
import com.ezra.elon.technologiaandahzaka.Adapter.NewsListViewAdapter;
import com.ezra.elon.technologiaandahzaka.Adapter.Report;
import com.ezra.elon.technologiaandahzaka.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link NewsListViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */




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



public class NewsListViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    Asistent asistent;
    DatabaseReference databaseReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FragmentTransaction ft;

    ListView listView;



    SharedPreferences mPrefs;
    ArrayList<Report> reportArrayList = new ArrayList<>();
    public NewsListViewFragment() {
        // Required empty public constructor
    }


    public static NewsListViewFragment newInstance(String param1, String param2) {
        NewsListViewFragment fragment = new NewsListViewFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

                  }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  rootview =inflater.inflate(R.layout.listview_fragment, container, false);

        reportArrayList = new ArrayList<>();
        asistent = new Asistent();
        databaseReference = database.getReference();

        listView  = (ListView) rootview.findViewById(R.id.list_view_id);
        Gson gson = new Gson();
        String json = "";
        getList();
//       json = mPrefs.getString("ReportList", "null");
//        Log.i("json check", json);
//
//        Type type = new TypeToken<ArrayList<Report>>(){}.getType();
//        ArrayList<Report> reports= gson.fromJson(json, type);

        return rootview;
    }




    void getList()
    {
        databaseReference.child("ReportList").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                GenericTypeIndicator<ArrayList<Report_holder>> t = new GenericTypeIndicator<ArrayList<Report_holder>>(){};

                ArrayList<Report_holder> value = new ArrayList<>();

                for (DataSnapshot child: dataSnapshot.getChildren())//initialize the jobs
                {
                    value.add(child.getValue(Report_holder.class));
                }

                if(value != null || value.size()> 0 )
                {
                    for (int i = value.size()-1; i >= 0 ; i--) {

                        //  value.get(i).getSubtitle();

                        reportArrayList.add(new Report(value.get(i).getImage(),value.get(i).getTitle(),value.get(i).getSubtitle(),value.get(i).getText()));

                        Log.i("check data, change",value.get(i).getTitle());

                    }

                }

                    //stop progress bar here
                ListAdapter adapter = new NewsListViewAdapter(getContext(),reportArrayList);
                listView.setAdapter(adapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }



        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = new Display_article_Fragment();//.setArrayList_holder(holderTITArrayList.get(i));
                Bundle bundle = new Bundle();
                bundle.putParcelable("articleholder", reportArrayList.get(i));

                fragment.setArguments(bundle);
                ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame_layout,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

    }

    }
