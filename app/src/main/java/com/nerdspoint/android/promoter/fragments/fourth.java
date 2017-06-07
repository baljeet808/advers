package com.nerdspoint.android.promoter.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nerdspoint.android.promoter.Activities.Buy;
import com.nerdspoint.android.promoter.R;

import static android.R.attr.description;

/**
 * A simple {@link Fragment} subclass.
 */
public class fourth extends Fragment {


    Button next,previous;
    String note;
    EditText note_et;

    public fourth() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fourth, container, false);


        note_et= (EditText) view.findViewById(R.id.note_et);
        next= (Button) view.findViewById(R.id.next_btn);
        previous=(Button) view.findViewById(R.id.previous_btn);

        try {
            note = ((Buy) getActivity()).getNote();
        }
        catch(Exception e)
        {


        }
        if(note!=null)
        {
            note_et.setText(note);
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(note_et.getText().toString().length()>20) {
                    ((Buy) getActivity()).movetofifth(note_et.getText().toString());
                }
                else
                {
                    note_et.setError("please Write at least 10 character");
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Buy)getActivity()).movetothird(null);
            }
        });


        return  view;
    }

}
