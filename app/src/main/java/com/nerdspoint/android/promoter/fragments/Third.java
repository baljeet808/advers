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

/**
 * A simple {@link Fragment} subclass.
 */
public class Third extends Fragment {

    EditText desc;
    Button next,previous;
    String description;


    public Third() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_third, container, false);

        desc= (EditText) view.findViewById(R.id.description_et);
        next= (Button) view.findViewById(R.id.next_btn);
        previous=(Button) view.findViewById(R.id.previous_btn);

        try {
            description = ((Buy) getActivity()).getdescription();
        }
        catch(Exception e)
        {

        }
        if(description!=null)
        {
            desc.setText(description);
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(desc.getText().toString().length()>20) {
                    ((Buy) getActivity()).movetofourth(desc.getText().toString());
                }
                else
                {
                    desc.setError("please Write at least 20 character");
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Buy)getActivity()).movetoSecond(null);
            }
        });
        return view;
    }

}
