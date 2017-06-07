package com.nerdspoint.android.promoter.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nerdspoint.android.promoter.Activities.Buy;
import com.nerdspoint.android.promoter.R;

import static android.R.attr.type;
import static android.R.string.ok;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sixth extends Fragment {

    TextView spent;
    Typeface type;
    EditText users;
    Button next,previous;


    public Sixth() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sixth, container, false);

        spent = (TextView) view.findViewById(R.id.spent_tv);
        users = (EditText) view.findViewById(R.id.User_strength);
        next= (Button) view.findViewById(R.id.next_btn);
        previous=(Button) view.findViewById(R.id.previous_btn);

        type = Typeface.createFromAsset(getActivity().getAssets(),"fonts/kenpixel_high_square.ttf");
        spent.setTypeface(type);

        users.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                int value = Integer.parseInt(users.getText().toString());

                spent.setText(""+value+" INR");
                if(value<50)
                {
                    users.setError("minimum users Should be 50");
                }
                return false;
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(users.getText().toString())>=50) {
                    ((Buy) getActivity()).movetoLast(users.getText().toString());
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Buy)getActivity()).movetosixth(null);
            }
        });


        return view;
    }

}
