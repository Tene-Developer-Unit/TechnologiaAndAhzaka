package com.ezra.elon.technologiaandahzaka.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.ezra.elon.technologiaandahzaka.Adapter.CarouselPagerAdapter;
import com.ezra.elon.technologiaandahzaka.Adapter.GridViewAdapter;
import com.ezra.elon.technologiaandahzaka.Adapter.HolderTIT;
import com.ezra.elon.technologiaandahzaka.R;

import java.util.ArrayList;

import static com.ezra.elon.technologiaandahzaka.Fragments.JobsFragments.pager;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShibutzimFragment extends Fragment {
    ArrayList<HolderTIT> Shuibutzim = new ArrayList<>();
    FragmentTransaction ft;
    int[] image = new int[] {R.drawable.mechenic_item, R.drawable.car_item,R.drawable.electronic_item,R.drawable.electricity_item,R.drawable.toon_item} ;

    public ShibutzimFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview;

        rootview = inflater.inflate(R.layout.simple_pageviewer, container, false);




           pager = (ViewPager) rootview.findViewById(R.id.myviewpager);
            DisplayMetrics metrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int pageMargin = ((metrics.widthPixels / 4) * 2);
            pager.setPageMargin(-pageMargin);

            //todo: display the maslulim ArrayList on the listView
            String ShibutzimName[] = getActivity().getApplicationContext().getResources().getStringArray(R.array.megamot);// the list of the megamot

            CarouselPagerAdapter adapter = new CarouselPagerAdapter(getActivity(), getChildFragmentManager(), image);// include cutome image and text
            pager.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            pager.addOnPageChangeListener(adapter);

            // Set current item to the middle page so we can fling to both
            // directions left and right

            pager.setOffscreenPageLimit(3);
            pager.setCurrentItem(1);

            pager.setCurrentItem(pager.getCurrentItem() + 10);






//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        ListView gridView = (ListView) rootview.findViewById(R.id.mainlistview);
//
//        //todo: display the maslulim ArrayList on the listView
//        final String  ShibutzimName [] = getActivity().getApplicationContext().getResources().getStringArray(R.array.megamot);
//        GridViewAdapter gridViewAdapter = new GridViewAdapter(getContext(),ShibutzimName);
//
//         gridView.setAdapter(gridViewAdapter);
//
//
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//
//                    Fragment fragment = new ShibutzimListFragment();
//                    ft = getActivity().getSupportFragmentManager().beginTransaction();
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("position", i);
//                    bundle.putString("title", ShibutzimName[i]);
//                    fragment.setArguments(bundle);
//                    ft.replace(R.id.frame_layout, fragment);
//                    ft.addToBackStack(null);
//                    ft.commit();
//
//                //  intent.putExtra("shibutId",i);
//            }
//        });
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
