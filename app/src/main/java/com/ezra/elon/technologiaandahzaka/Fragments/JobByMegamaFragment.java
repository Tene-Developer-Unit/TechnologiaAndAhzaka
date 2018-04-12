package com.ezra.elon.technologiaandahzaka.Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ezra.elon.technologiaandahzaka.Acivities.MainActivity;
import com.ezra.elon.technologiaandahzaka.Adapter.Asistent;
import com.ezra.elon.technologiaandahzaka.Adapter.CostumeAddapter;
import com.ezra.elon.technologiaandahzaka.Adapter.HolderTIT;
import com.ezra.elon.technologiaandahzaka.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A simple {@link Fragment} subclass.
 * the list of the courses from the chousen megama
 */

 class AJob
{

    String jobName;
    String jobDesc;
    public Asistent asistent = new Asistent();

    private  AJob()
    {

    }

    public AJob(String job_name, String job_description)
    {
        this.jobName = job_name;
        this.jobDesc = job_description;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public String getJob_name() {
        return jobName;
    }
}

public class JobByMegamaFragment extends Fragment {

    public static int position;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static ArrayList<HolderTIT> holderTITArrayList = new ArrayList<>();
    String Jobs_Name[];
    FragmentTransaction ft;

    /**
     *              ABOUT THE flag_were_in_on_creat
     *            ------------------------------------
     *
     *       whene i choose item  and press the back button i lost my list
     *       because when you use the getbackstuck (or somthing like that) it take me to the oncreat view.
     *       my megama indentifier is on onCreat so ....
     *
     *       i use the flage to check if i was in on create or didnt.
     *
     */

    boolean flag_were_in_on_creat = false;

    String megama = null;
    DatabaseReference myRef;
    ListView listView;
    View rootview;
    public JobByMegamaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = new Bundle();
        position  =   getArguments().getInt("position");

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
        flag_were_in_on_creat = true;//// i was in the onCreat

        /////////////////////       TEMPERARY        ////////////////////////////////
        Toast.makeText(getContext(),megama,Toast.LENGTH_SHORT).show();

        myRef = FirebaseDatabase.getInstance().getReference();

        holderTITArrayList.clear();

        JsontoArrayList();// initialize the databse in the list

        Jobs_Name = JobsNameToStringArray();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(!flag_were_in_on_creat)/// did you were in the OnCreat?
             this.onCreate(getArguments());/// no, i will go now!
        flag_were_in_on_creat = false;/// i were, and now i leave
        // Inflate the layout for this fragment
         rootview = inflater.inflate(R.layout.listview_fragment, container, false);

        listView = (ListView) rootview.findViewById(R.id.list_view_id);

        Log.d("Lifetime","onCreatView");

          //  display();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment fragment = new JobInfoFragment();//.setArrayList_holder(holderTITArrayList.get(i));
                Bundle bundle = new Bundle();
                bundle.putParcelable("jobholder",holderTITArrayList.get(i));
                fragment.setArguments(bundle);
                ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame_layout,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }

        });

        return rootview;

    }


    public void JsontoArrayList()
    {

    ////////////// dialog alert while check jobs//////////

        final ProgressDialog Dialog = new ProgressDialog(getContext());
        Dialog.setMessage("מחפש משרות חדשות...");
        Dialog.setCanceledOnTouchOutside(true);
        Dialog.setCancelable(false);
        Dialog.show();

    /////////////////////////////////////////////////////



    /////// Read From Firebase Databse////////////////////
        myRef.child("Jobs").child(megama).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
                {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    GenericTypeIndicator<ArrayList<AJob>> t = new GenericTypeIndicator<ArrayList<AJob>>(){};

                    if(dataSnapshot.getChildrenCount() > 0 )
                    {

                 //   ArrayList<AJob> value = dataSnapshot.getValue(t);

                        ArrayList<AJob>value = new ArrayList<>();///list to contain the jobs from the databse

                        for (DataSnapshot child: dataSnapshot.getChildren())//initialize the jobs
                        {
                            value.add(child.getValue(AJob.class));
                        }


                    if(value != null || value.size()> 0 )
                    {
                        for (int i = 0; i < value.size(); i++) {//value.get(i).getJobDesc()
                            /////TODO: add the objects to the lists
                            holderTITArrayList.add(new HolderTIT(value.get(i).getJob_name(),"null", value.get(i).getJobDesc().toString()));

                            Log.d("DATABSE", "Value is:" + value.get(i).getJobDesc());

                        }



                        display();
                    }

                    }
                    else
                    {
                        AlertDialog ad = new AlertDialog.Builder(getContext()).create();
                        ad.setCancelable(true);
                        ad.setTitle("לא נמצאו משרות");
                        ad.setMessage("לצערנו לא נמצאו משרות במגמה המבוקשת.");
                        ad.setButton("בסדר", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                getActivity().getSupportFragmentManager().popBackStack();
                            }
                        });

                        ad.show();

                    }
                    Dialog.hide();


                }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getContext(),"Failed to read value",Toast.LENGTH_SHORT).show();
                Log.w("DATABSE", "Failed to read value." + error.toException());
            }
        });




    }



    private String[] JobsNameToStringArray()
    {
        int sizearray = holderTITArrayList.size();

        String [] arr = new String[sizearray];

        for (int i = 0; i< sizearray;i++)
        {
            arr[i] = holderTITArrayList.get(i).getMaslulName();

        }

        return arr;
    }

    void display()
    {

        listView.setAdapter(new CostumeAddapter(getActivity().getBaseContext(),JobsNameToStringArray()));


    }






}
