package com.ezra.elon.technologiaandahzaka.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.Adapter.CostumeAddapter;
import com.ezra.elon.technologiaandahzaka.Adapter.CourseGridViewAddapter;
import com.ezra.elon.technologiaandahzaka.Adapter.NewsListViewAdapter;
import com.ezra.elon.technologiaandahzaka.Adapter.Report;
import com.ezra.elon.technologiaandahzaka.R;
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
public class NewsListViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SharedPreferences mPrefs;
    ArrayList<Report> reportArrayList = new ArrayList<>();
    public NewsListViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragmenFragment.
     */
    // TODO: Rename and change types and number of parameters
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

       mPrefs  = getActivity().getPreferences(MODE_PRIVATE);

        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        Type type = new TypeToken<ArrayList<Report>>(){}.getType();
        ArrayList<Report> reports= gson.fromJson(json, type);
        ListView listView = (ListView) rootview.findViewById(R.id.list_view_id);

//        ListAdapter adapter = new NewsListViewAdapter(getContext(),reports);
//        listView.setAdapter(adapter);
        return rootview;
    }





    }
