package com.ezra.elon.technologiaandahzaka.Fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ezra.elon.technologiaandahzaka.Acivities.MainActivity;
import com.ezra.elon.technologiaandahzaka.Adapter.Asistent;
import com.ezra.elon.technologiaandahzaka.Adapter.CostumeAddapter;
import com.ezra.elon.technologiaandahzaka.Adapter.HolderTIT;
import com.ezra.elon.technologiaandahzaka.Fragments.DisplayInfoFragment;
import com.ezra.elon.technologiaandahzaka.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.attr.defaultHeight;
import static android.R.attr.fragment;
import static java.security.AccessController.getContext;

/*

this class display the list of the courses of specific profession

 */
public class ShibutzimListFragment extends Fragment {



ArrayList<HolderTIT> holderTITArrayList = new ArrayList<>();
    int position;
    String title;
    FragmentTransaction ft;
    String maslulimName [];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview;
        rootview = inflater.inflate(R.layout.listview_fragment, container, false);


        Bundle bundle = new Bundle();
        position  =   getArguments().getInt("position");
        title = getArguments().getString("title");

        holderTITArrayList.clear();//clear the list of the json objects
        JsontoArrayList();
        ListView listView = (ListView) rootview.findViewById(R.id.list_view_id);


        maslulimName = MaslulimNameToStringArray();

        listView.setAdapter(new CostumeAddapter(getActivity().getBaseContext(),maslulimName));

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("קורסים ב" + title);//display new title


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ft = getActivity().getSupportFragmentManager().beginTransaction();
                if(!holderTITArrayList.get(i).getMaslulName().isEmpty())
                {
                    Log.i("Elon pay attantion","item cliked from shibutzimfragment");
                    Fragment fragment = new DisplayInfoFragment();//.setArrayList_holder(holderTITArrayList.get(i));
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("holderer", holderTITArrayList.get(i));
                    fragment.setArguments(bundle);
                    ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout, fragment);

                    ft.addToBackStack(null);

                    ft.commit();
                }


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
                case 1:
                    megama = "Cars";
                    break;
                case 2:
                    megama = "Electronic";
                    break;
                case 3:
                    megama = "Electricity";
                    break;
                case 4:
                    megama = "toon";
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
