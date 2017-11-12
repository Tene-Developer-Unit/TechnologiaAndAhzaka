package com.ezra.elon.technologiaandahzaka.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.Adapter.Asistent;
import com.ezra.elon.technologiaandahzaka.Adapter.GridViewAdapter;
import com.ezra.elon.technologiaandahzaka.Adapter.HolderTIT;
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
 */
public class JobsFragments extends Fragment {

    ArrayList<HolderTIT> holderTITArrayList = new ArrayList<>();
    int position;
    FragmentTransaction ft;
    DatabaseReference mDatabase;
    public JobsFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview;

        rootview = inflater.inflate(R.layout.grid_view_list, container, false);

        ListView gridView = (ListView) rootview.findViewById(R.id.maingridview);

        mDatabase = FirebaseDatabase.getInstance().getReference();//get the refference of the databse



        //todo: display the maslulim ArrayList on the listView
        String  ShibutzimName [] = getActivity().getApplicationContext().getResources().getStringArray(R.array.megamot);

        gridView.setAdapter(new GridViewAdapter(getContext(),ShibutzimName));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //// TODO: 13/09/2017 transfer the call in to a Fragment
                Fragment fragment = new ShibutzimListFragment();
                ft = getActivity().getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("position", i);
                fragment.setArguments(bundle);
                ft.replace(R.id.frame_layout, fragment);
                ft.addToBackStack(null);
                ft.commit();

                //  intent.putExtra("shibutId",i);
            }
        });
        return rootview;
    }

    public void JsontoArrayList()
    {


        try {
            JSONObject root = new JSONObject(Asistent.loadJSONFromAsset((getActivity().getBaseContext()), "jsonFiles/shibutzim.json"));
            String megama = null;

            mDatabase.child("Jobs").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.i("jobs",dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            switch (position) {
                case 0:
                    megama = "Mechanic";
                    break;
                default:
                    break;
            }
            if (megama != null)
            {
                JSONArray maslulim = root.getJSONArray(megama);
                String name;
                String url;
                for (int i = 0; i < maslulim.length(); i++) {
                    JSONObject maslul = maslulim.getJSONObject(i);//get single object
                    holderTITArrayList.add(new HolderTIT(maslul.getString("name"),maslul.getString("videoUrl"),maslul.getString("textPath")));
                    Log.i("JsonToArryList", maslul.getString("name"));
                }
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }



    private String[] MaslulimNameToStringArray()
    {
        int sizearray = holderTITArrayList.size();

        String [] arr = new String[sizearray];

        for (int i = 0; i< sizearray;i++)
        {
            arr[i] = holderTITArrayList.get(i).getMaslulName();
        }

        return arr;
    }


}
