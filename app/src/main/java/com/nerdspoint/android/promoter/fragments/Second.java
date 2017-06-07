package com.nerdspoint.android.promoter.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nerdspoint.android.promoter.Activities.Buy;
import com.nerdspoint.android.promoter.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Second extends Fragment {


    Button previous,next;
    EditText url;
    String urL;
    public Second() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_second, container, false);

        url= (EditText) view.findViewById(R.id.url_et);
        next= (Button) view.findViewById(R.id.next_btn);
        previous=(Button) view.findViewById(R.id.previous_btn);


        try {
            urL = ((Buy) getActivity()).getUrl();
        }
        catch(Exception e)
        {
            Toast.makeText(getActivity(), "url not fetched ....", Toast.LENGTH_SHORT).show();
        }

        if(urL!=null)
        {
            url.setText(urL);
        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(url.getText().toString().length()>1) {
                    ((Buy) getActivity()).movetothird(url.getText().toString());
                }
                else
                {
                    url.setError("please enter valid url");
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Buy)getActivity()).backToFirst();
            }
        });

        return  view;
    }

}
