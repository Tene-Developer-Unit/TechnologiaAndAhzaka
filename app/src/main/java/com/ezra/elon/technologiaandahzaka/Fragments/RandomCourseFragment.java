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

import com.ezra.elon.technologiaandahzaka.Adapter.Asistent;
import com.ezra.elon.technologiaandahzaka.Adapter.CourseButton;
import com.ezra.elon.technologiaandahzaka.Adapter.CourseGridViewAddapter;
import com.ezra.elon.technologiaandahzaka.Adapter.HolderTIT;
import com.ezra.elon.technologiaandahzaka.Adapter.NewsListViewAdapter;
import com.ezra.elon.technologiaandahzaka.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 *
 * class that handle the random course
 *
 */
public class RandomCourseFragment extends Fragment {

    ArrayList<CourseButton> randomCourseArrayList;

    FragmentTransaction ft;

   ArrayList<HolderTIT> holderTITArrayList;

    public RandomCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  rootview =inflater.inflate(R.layout.simple_gridview, container, false);

        holderTITArrayList = new ArrayList<>();

        GridView listView = (GridView) rootview.findViewById(R.id.maingridview);

        Asistent asistent = new Asistent();

        randomCourseArrayList = new ArrayList<>();

        randomCourseArrayList.clear();

       randomCourseArrayList = (ArrayList<CourseButton>) asistent.RandomCoures(getActivity().getBaseContext());



        ListAdapter adapter = new CourseGridViewAddapter(getActivity().getBaseContext(),randomCourseArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                for(int d = 0; d < randomCourseArrayList.size(); d++)
                {/////////todo: need to proceed the develop
                    holderTITArrayList.add(new HolderTIT(randomCourseArrayList.get(i).getTitle(),randomCourseArrayList.get(i).getTitle()));
                }

                Fragment fragment = new DisplayInfoFragment();
                ft = getActivity().getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putParcelable("holderer", holderTITArrayList.get(i));
                fragment.setArguments(bundle);                fragment.setArguments(bundle);
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
