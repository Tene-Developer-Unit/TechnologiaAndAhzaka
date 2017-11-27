package com.ezra.elon.technologiaandahzaka.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.Acivities.MainActivity;
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
 *
 * fragment to display the list of megamot First page
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
if(isNetworkConnected())//ther is an internet connection
{
    rootview = inflater.inflate(R.layout.grid_view_list, container, false);

    ListView gridView = (ListView) rootview.findViewById(R.id.maingridview);

    mDatabase = FirebaseDatabase.getInstance().getReference();//get the refference of the databse


    //todo: display the maslulim ArrayList on the listView
    String ShibutzimName[] = getActivity().getApplicationContext().getResources().getStringArray(R.array.megamot);// the list of the megamot

    gridView.setAdapter(new GridViewAdapter(getContext(), ShibutzimName));


    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            Fragment fragment = new JobByMegamaFragment();
            ft = getActivity().getSupportFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putInt("position", i);// the choosen megama
            fragment.setArguments(bundle);
            ft.replace(R.id.frame_layout, fragment);
            ft.addToBackStack(null);
            ft.commit();

            //  intent.putExtra("shibutId",i);
        }
    });
}

    else
        {

            rootview = inflater.inflate(R.layout.webview_news, container, false);
            WebView webView = (WebView) rootview.findViewById(R.id.webview_fragment);
            webView.loadDataWithBaseURL("","אין חיבור לאינטרנט\n בדוק את חיבורי הרשת","","","");

        }
        return rootview;

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


}
