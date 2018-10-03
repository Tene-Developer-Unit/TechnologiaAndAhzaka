package com.ezra.elon.technologiaandahzaka.Fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.StackView;
import android.widget.TextView;
import android.widget.Toast;

import com.ezra.elon.technologiaandahzaka.Acivities.MainActivity;
import com.ezra.elon.technologiaandahzaka.Adapter.Asistent;
import com.ezra.elon.technologiaandahzaka.Adapter.GridViewAdapter;
import com.ezra.elon.technologiaandahzaka.Adapter.MainSrcnCourseGridViewAdapter;
import com.ezra.elon.technologiaandahzaka.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FragmentTransaction ft;

    Fragment fragment = null;

    String weekphotourl,weekphototitel;
    ImageView weeklyImage;
    TextView weeklyImageTitle;
    Button moreNews;

    private Context context;
    DatabaseReference mDatabase;

    ImageButton logitechBtn;

    ImageButton meethail;

    ImageButton mitgaisimBtn;

    ImageButton miktzoaBtn;


    private OnFragmentInteractionListener mListener;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenuFragment.
     */
    //
    public static MainMenuFragment newInstance(String param1, String param2) {
        MainMenuFragment fragment = new MainMenuFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.main_screen_fragment, container, false);

        Toast.makeText(getActivity().getBaseContext(),"טוען רכיבים",Toast.LENGTH_LONG).show();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        context = getContext();
        weeklyImage = (ImageView) rootview.findViewById(R.id.weeklyphoto);
        //weeklyImage.setBackgroundResource(R.drawable.common_google_signin_btn_icon_disabled);
        weeklyImageTitle = (TextView) rootview.findViewById(R.id.main_screen_weekphoto_title);

        moreNews = (Button) rootview.findViewById(R.id.more_news_button);



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//
//        logitechBtn = (ImageButton) rootview.findViewById(R.id.logitech_button);
//        meethail = (ImageButton) rootview.findViewById(R.id.meethail_button);
//        mitgaisimBtn = (ImageButton) rootview.findViewById(R.id.mitgaisim_button);
//
//        miktzoaBtn = (ImageButton) rootview.findViewById(R.id.miktzoa_button);
//
//
//        meethail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fragment = new MeetHailFragment();
//                runFragment();
//
//            }
//        });
//
//        logitechBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fragment = new WebViewOnlyFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("url","file:///android_asset/logitechWeb/landingpage/index.html");
//                fragment.setArguments(bundle);
//
//                runFragment();
//
//            }
//        });
//
//        mitgaisimBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fragment = new MalshabimFragment();
//                runFragment();
//            }
//        });
//        miktzoaBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fragment = new MiktzoaLaFragment();
//                runFragment();
//
//            }
//        });


///////////////////////////////////// THE COMENDS THAT CONNECT ME TO THE WEEKLY PHOTO //////////////////////////////////////////

        //weeklyImage.setImageResource();
        ViewGroup.LayoutParams size = weeklyImage.getLayoutParams();

        size.height = 300;
        size.width = 300;
        weeklyImage.setLayoutParams(size);



        mDatabase.child("URLS").child("weeklyphototitle").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                weekphototitel = dataSnapshot.getValue(String.class);
                weeklyImageTitle.setText(weekphototitel);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("URLS").child("weeklyphoto").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              weekphotourl = dataSnapshot.getValue(String.class);
                Picasso.with(context).load(weekphotourl).into(weeklyImage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                        weeklyImage.setBackgroundResource(android.R.color.transparent);
                        ViewGroup.LayoutParams size = weeklyImage.getLayoutParams();
                        size.height = ViewGroup.LayoutParams.MATCH_PARENT;
                        size.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        weeklyImage.setLayoutParams(size);

                    }


                    @Override
                    public void onError() {

                        Log.i("pic stat", "Error");

                    }
                });;// it used an outside librarry called Picaso

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}});




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////More News Button ////////////////////////////////


        moreNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment fragment = new NewsListViewFragment();//.setArrayList_holder(holderTITArrayList.get(i));

                ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame_layout,fragment);
                ft.addToBackStack(null);
                ft.commit();



            }
        });



/////////////////////////////////////////////////////////////////////////



////////////////////////////// News List HAndeler    //////////////////////////////////////////
        NewsListViewFragment newsListViewFragment = new NewsListViewFragment();

        getChildFragmentManager().beginTransaction().add(R.id.news_listview_fragment,newsListViewFragment).commit();

 ////////////////////////////////////////////////////////////////////////////////////////////////////


/////////////////////////// Random course handler /////////////////////////////////
        RandomCourseFragment randomCourseFragment = new RandomCourseFragment();
        getChildFragmentManager().beginTransaction().add(R.id.random_course_fragment,randomCourseFragment).commit();

/////////////////////////////////////////////////////////////////////////////////////



        return rootview;
    }


    void runFragment()
    {
        ft = getFragmentManager().beginTransaction();
        ft.setAllowOptimization(true);
        ft.replace(R.id.frame_layout,fragment);
        ft.addToBackStack(null);
        ft.commit();
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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
