package com.ezra.elon.technologiaandahzaka.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.ezra.elon.technologiaandahzaka.Acivities.MainActivity;
import com.ezra.elon.technologiaandahzaka.Adapter.GridViewAdapter;
import com.ezra.elon.technologiaandahzaka.Adapter.HolderTIT;
import com.ezra.elon.technologiaandahzaka.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShibutzimFragment extends Fragment {
    ArrayList<HolderTIT> Shuibutzim = new ArrayList<>();
FragmentTransaction ft;
    public ShibutzimFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview;

        rootview = inflater.inflate(R.layout.grid_view_list, container, false);

        ListView gridView = (ListView) rootview.findViewById(R.id.maingridview);

        //todo: display the maslulim ArrayList on the listView
        final String  ShibutzimName [] = getActivity().getApplicationContext().getResources().getStringArray(R.array.megamot);

         gridView.setAdapter(new GridViewAdapter(getContext(),ShibutzimName));



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //// TODO: 13/09/2017 transfer the call in to a Fragment
                Fragment fragment = new ShibutzimListFragment();
                ft = getActivity().getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("position", i);
                bundle.putString("title", ShibutzimName[i]);
                fragment.setArguments(bundle);
                ft.replace(R.id.frame_layout, fragment);
                ft.addToBackStack(null);
                ft.commit();

                //  intent.putExtra("shibutId",i);
            }
        });
        return rootview;
    }

//    public void JsontoArrayList()
//    {
//
//
//        try {
//            JSONObject root  = new JSONObject(Asistent.loadJSONFromAsset((getActivity()),"jsonFiles/maslulim.json"));
//            JSONArray maslulim = root.getJSONArray("maslulim");
//            String name;
//            String url;
//            for(int i = 0; i< maslulim.length(); i++)
//            {
//                JSONObject shibutz = maslulim.getJSONObject(i);//get single object
//                //.add(new HolderTIT(shibutz.getString("name")));
//                Log.i("JsonToArryList",shibutz.getString("name"));
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }

//    private String[] ShibutzimNameToStringArray()
//    {
//       int sizearray = Shuibutzim.size();
//
//        String [] arr = new String[sizearray];
//
//        for (int i = 0; i< sizearray;i++)
//        {
//            arr[i] = Shuibutzim.get(i).getMaslulName();
//        }
//
//        return arr;
//    }

}
