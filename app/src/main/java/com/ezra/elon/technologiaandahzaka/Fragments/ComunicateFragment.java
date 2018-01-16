package com.ezra.elon.technologiaandahzaka.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ezra.elon.technologiaandahzaka.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ComunicateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ComunicateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComunicateFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public ComunicateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComunicateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComunicateFragment newInstance(String param1, String param2) {
        ComunicateFragment fragment = new ComunicateFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  rootview =inflater.inflate(R.layout.communication, container, false);
        ImageButton phonenumber = (ImageButton) rootview.findViewById(R.id.phone_number_button);
        ImageButton email = (ImageButton) rootview.findViewById(R.id.email_button);

        phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getActivity().getResources().getString(R.string.phone_number)));
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("email/*");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getActivity().getResources().getString(R.string.mail_address)});

                intent.putExtra(Intent.EXTRA_SUBJECT, "דואר משתמש מהאפליקציה");
                intent.putExtra(Intent.EXTRA_TEXT,"שם:\n\n מספר זהות:\n\n מספר טלפון: \n \n תוכן ההודעה:\n\n");

                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
