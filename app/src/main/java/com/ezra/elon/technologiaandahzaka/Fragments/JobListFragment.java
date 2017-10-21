package com.ezra.elon.technologiaandahzaka.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.Adapter.Asistent;
import com.ezra.elon.technologiaandahzaka.Adapter.CostumeAddapter;
import com.ezra.elon.technologiaandahzaka.Adapter.HolderTIT;
import com.ezra.elon.technologiaandahzaka.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobListFragment extends Fragment {

    ArrayList<HolderTIT> holderTITArrayList = new ArrayList<>();
    int position;
    FragmentTransaction ft;

    public JobListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview;
        rootview = inflater.inflate(R.layout.listview_fragment, container, false);


        Bundle bundle = new Bundle();
        position  =   getArguments().getInt("position");


        JsontoArrayList();
        ListView listView = (ListView) rootview.findViewById(R.id.list_view_id);


        String  maslulimName [] = MaslulimNameToStringArray();


        listView.setAdapter(new CostumeAddapter(getActivity().getBaseContext(),maslulimName));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment fragment = new DisplayInfoFragment();//.setArrayList_holder(holderTITArrayList.get(i));
                Bundle bundle = new Bundle();
                bundle.putParcelable("holderer",holderTITArrayList.get(i));
                fragment.setArguments(bundle);
                ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame_layout,fragment);

                ft.addToBackStack(null);

                ft.commit();

            }

        });
        return  rootview;

    }


    public void JsontoArrayList()
    {


        try {
            JSONObject root = new JSONObject(Asistent.loadJSONFromAsset((getActivity().getBaseContext()), "jsonFiles/shibutzim.json"));
            String megama = null;
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
                    holderTITArrayList.add(new HolderTIT(maslul.getString("name"),maslul.getString("textPath")));

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
