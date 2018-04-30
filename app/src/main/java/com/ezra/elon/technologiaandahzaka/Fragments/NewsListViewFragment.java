package com.ezra.elon.technologiaandahzaka.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.Adapter.CostumeAddapter;
import com.ezra.elon.technologiaandahzaka.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsListViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsListViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsListViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


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
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  rootview =inflater.inflate(R.layout.listview_fragment, container, false);


        ListView listView = (ListView) rootview.findViewById(R.id.list_view_id);

//        listView.setAdapter(new CostumeAddapter(getActivity(),new String[]{"elon","elon"}));
       return rootview;
    }





    }
