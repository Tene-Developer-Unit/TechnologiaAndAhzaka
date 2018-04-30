package com.ezra.elon.technologiaandahzaka.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.Acivities.MainActivity;
import com.ezra.elon.technologiaandahzaka.Fragments.JobByMegamaFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.JobsFragments;
import com.ezra.elon.technologiaandahzaka.R;

/*

       this class in charge on the definition of a itme in the carousel.
       it include the image view that i set in the JobsFragment.java and the text that do as well.


       also in charge on the OnClicked $$$$$$$ I WANT TO MOVE IT TO THE JOBSFRAGMENT.JAVA $$$$$$$



 */


// TODO: 10/04/2018 check why the item fragment is not load up, try to take the item fragnent out of the stuck



public class ItemFragment extends Fragment {

    private static final String POSITON = "position";
    private static final String SCALE = "scale";
    private static final String DRAWABLE_RESOURE = "resource";
    FragmentTransaction ft;
    String title;
    private int screenWidth;
    private int screenHeight;

    Intent intent;

//    private int[] imageArray = new int[]{R.drawable.image1, R.drawable.image2,
//            R.drawable.image3, R.drawable.image4, R.drawable.image5,
//            R.drawable.image6, R.drawable.image7, R.drawable.image8,
//            R.drawable.image9, R.drawable.image10};

    public static Fragment newInstance(Context context, int pos, float scale) {

        Bundle b = new Bundle();
        b.putInt(POSITON, pos);
        b.putFloat(SCALE, scale);



        return Fragment.instantiate(context, ItemFragment.class.getName(), b);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWidthAndHeight();

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        final int postion = this.getArguments().getInt(POSITON);
        float scale = this.getArguments().getFloat(SCALE);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(screenWidth / 2, screenHeight / 2);
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_carousel_view, container, false);

        TextView textView = (TextView) linearLayout.findViewById(R.id.text);
        CarouselLinearLayout root = (CarouselLinearLayout) linearLayout.findViewById(R.id.root_container);
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.pagerImg);
        switch (postion)
        {
            case 0:
                title = "מכונות";
                break;
            case 1:
                title = "רכב";
                break;
            case 2:
                title = "אלקטרוניקה";
                break;
            case 3:
                title = "חשמל";
                break;

        }
        Log.i("fragment item", title);
        textView.setText(title);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageResource(CarouselPagerAdapter.images[postion]);

        //handling click event
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Fragment fragment = new JobByMegamaFragment();
            ft = getActivity().getSupportFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putInt("position", postion);// the choosen megama
            fragment.setArguments(bundle);
            ft.replace(R.id.frame_layout, fragment);
            ft.addToBackStack(null);
            ft.commit();

            }
        });

        root.setScaleBoth(scale);

        return linearLayout;
    }

    /**
     * Get device screen width and height
     */
    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }
}
