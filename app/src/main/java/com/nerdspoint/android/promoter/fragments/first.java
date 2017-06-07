package com.nerdspoint.android.promoter.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nerdspoint.android.promoter.Activities.Buy;
import com.nerdspoint.android.promoter.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class first extends Fragment {

   String adName;
    EditText urlname;
    Button next,previous;
    public first() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_first, container, false);

        urlname= (EditText) view.findViewById(R.id.urlName_et);
        next= (Button) view.findViewById(R.id.next_btn);
        previous=(Button) view.findViewById(R.id.previous_btn);

        try {
            adName = ((Buy) getActivity()).getUrlName();
        }
        catch(Exception e)
        {


        }
            if(adName!=null)
        {
            urlname.setText(adName);
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(urlname.getText().toString().length()>1) {
                    ((Buy) getActivity()).movetoSecond(urlname.getText().toString());
                }
                else
                {
                    urlname.setError("please enter name first");
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Buy)getActivity()).moveToMainPage();
            }
        });

        return view;
    }

}
