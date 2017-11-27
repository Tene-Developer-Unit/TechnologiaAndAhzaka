package com.ezra.elon.technologiaandahzaka.Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import android.widget.Toast;

import com.ezra.elon.technologiaandahzaka.Acivities.MainActivity;
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

/**
 * A simple {@link Fragment} subclass.
 * the list of the courses from the chousen megama
 */

 class AJob
{

    String jobName;
    String jobDesc;

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

    int position;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<HolderTIT> holderTITArrayList = new ArrayList<>();
    String Jobs_Name[];
    FragmentTransaction ft;
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
        // Inflate the layout for this fragment
         rootview = inflater.inflate(R.layout.listview_fragment, container, false);

        listView = (ListView) rootview.findViewById(R.id.list_view_id);

        ImageView imageView;

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


        final ProgressDialog Dialog = new ProgressDialog(getContext());
        Dialog.setMessage("מחפש משרות חדשות...");
        Dialog.setCanceledOnTouchOutside(true);
        Dialog.setCancelable(false);
        Dialog.show();



        myRef.child("Jobs").child(megama).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
                {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    GenericTypeIndicator<ArrayList<AJob>> t = new GenericTypeIndicator<ArrayList<AJob>>(){};

                    ArrayList<AJob> value = dataSnapshot.getValue(t);

                    if(value != null) {
                        for (int i = 0; i < value.size(); i++) {//value.get(i).getJobDesc()
                            /////TODO: add the objects to the lists
                            holderTITArrayList.add(new HolderTIT(value.get(i).getJob_name(),"null", value.get(i).getJobDesc().toString()));

                            Log.d("DATABSE", "Value is:" + value.get(i).getJobDesc());

                        }

                        display();



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
            Log.d("holder", arr[i]);
        }

        return arr;
    }

    void display()
    {

        listView.setAdapter(new CostumeAddapter(getActivity().getBaseContext(),JobsNameToStringArray()));

    }

}
