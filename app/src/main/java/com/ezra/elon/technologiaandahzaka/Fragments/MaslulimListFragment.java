package com.ezra.elon.technologiaandahzaka.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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
 * Activities that contain this fragment must implement the
 * {@link MaslulimListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MaslulimListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaslulimListFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private String mParam1;
    private String mParam2;
    String filename;
    ArrayList<HolderTIT> holderTITArrayList = new ArrayList<HolderTIT>();
    FragmentTransaction ft;

    private OnFragmentInteractionListener mListener;

    public MaslulimListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MaslulimListFragment.
     */
    public static MaslulimListFragment newInstance(String param1, String param2) {
        MaslulimListFragment fragment = new MaslulimListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("funk stat","im in onCreat");
        JsontoArrayList();

        if (getArguments() != null) {
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview;
        rootview = inflater.inflate(R.layout.listview_fragment, container, false);

        Bundle bundle = new Bundle();

       filename = bundle.getString("fileName");

        ListView listView = (ListView) rootview.findViewById(R.id.list_view_id);

        //todo: display the maslulim ArrayList on the listView
        String [] maslulimName = MaslulimNameToStringArray();

        listView.setAdapter(new CostumeAddapter(getActivity(),maslulimName));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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
        return rootview;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

   public void JsontoArrayList()
    {


        try {
            JSONObject root  = new JSONObject(Asistent.loadJSONFromAsset((getActivity()),"jsonFiles/" + "maslulim"+".json"));
            JSONArray maslulim = root.getJSONArray("maslulim");
            String name;
            String url;
            for(int i = 0; i< maslulim.length(); i++)
            {
                JSONObject maslul = maslulim.getJSONObject(i);//get single object
                holderTITArrayList.add(new HolderTIT(maslul.getString("name"),maslul.getString("videoUrl"),maslul.getString("textPath")));

                Log.i("JsonToArryList",maslul.getString("name"));
            }

        } catch (JSONException e) {
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
