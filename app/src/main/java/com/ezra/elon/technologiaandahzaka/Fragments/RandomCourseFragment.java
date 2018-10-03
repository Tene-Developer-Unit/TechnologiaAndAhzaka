package com.ezra.elon.technologiaandahzaka.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ezra.elon.technologiaandahzaka.Adapter.Asistent;
import com.ezra.elon.technologiaandahzaka.Adapter.CourseButton;
import com.ezra.elon.technologiaandahzaka.Adapter.CourseGridViewAddapter;
import com.ezra.elon.technologiaandahzaka.Adapter.HolderTIT;
import com.ezra.elon.technologiaandahzaka.Adapter.NewsListViewAdapter;
import com.ezra.elon.technologiaandahzaka.R;

import java.net.URI;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 *
 * class that handle the random course
 *
 */
public class RandomCourseFragment extends Fragment {

    ArrayList<CourseButton> randomCourseArrayList; // will contain the random course list

    FragmentTransaction ft;

   ArrayList<HolderTIT> holderTITArrayList; // object of single course

    public RandomCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  rootview =inflater.inflate(R.layout.simple_gridview, container, false);

        holderTITArrayList = new ArrayList<>();//initialise
        randomCourseArrayList = new ArrayList<>();


        GridView listView = (GridView) rootview.findViewById(R.id.maingridview);

        Asistent asistent = new Asistent();//call the asistent


        randomCourseArrayList.clear();


       randomCourseArrayList = (ArrayList<CourseButton>) asistent.RandomCoures(getActivity());//get a random list of course



        ListAdapter adapter = new CourseGridViewAddapter(getActivity(),randomCourseArrayList);//write the courses with the adapter
        listView.setAdapter(adapter);// apply the adapter


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                for(int d = 0; d < randomCourseArrayList.size(); d++)
                {/////////todo: need to proceed the develop
                    holderTITArrayList.add(new HolderTIT(randomCourseArrayList.get(i).getTitle(),randomCourseArrayList.get(i).getImage(),randomCourseArrayList.get(i).getPath()));
                }

                Fragment fragment = new DisplayInfoFragment();

            //    ft = getActivity().getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putParcelable("holderer", holderTITArrayList.get(i));
                fragment.setArguments(bundle);
               // ft.add(R.id.frame_layout, fragment);
               // ft.disallowAddToBackStack();
                ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame_layout, fragment);
                ft.addToBackStack(null);
                ft.commit();

                //  intent.putExtra("shibutId",i);
            }
        });
        return rootview;
    }

    void generatelist()
    {



    }

}
