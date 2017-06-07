package com.nerdspoint.android.promoter.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nerdspoint.android.promoter.Activities.Buy;
import com.nerdspoint.android.promoter.R;

import static android.R.attr.description;
import static android.R.attr.type;
import static android.R.string.ok;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sixth extends Fragment {

    TextView spent;
    Typeface type;
    EditText users;
    String money;
    Button next,previous;


    public Sixth() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sixth, container, false);

        try {
            money = ((Buy) getActivity()).getMoney();
        }
        catch(Exception e)
        {

        }
        if(money!=null)
        {
            spent.setText(money+" INR");
            users.setText(money);
        }


        spent = (TextView) view.findViewById(R.id.spent_tv);
        users = (EditText) view.findViewById(R.id.User_strength);
        next= (Button) view.findViewById(R.id.next_btn);
        previous=(Button) view.findViewById(R.id.previous_btn);

        type = Typeface.createFromAsset(getActivity().getAssets(),"fonts/kenpixel_high_square.ttf");
        spent.setTypeface(type);


        users.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(users.getText().length()>=2 || users.getText().toString().equals("0") || users.getText().toString().equals("1") || users.getText().toString().equals("2") || users.getText().toString().equals("3") || users.getText().toString().equals("4") || users.getText().toString().equals("5") || users.getText().toString().equals("6") || users.getText().toString().equals("7") || users.getText().toString().equals("8") || users.getText().toString().equals("9")) {
                    int value = Integer.parseInt(users.getText().toString());

                    spent.setText("" + value + " INR");
                    if (value < 50) {
                        users.setError("minimum users Should be 50");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(users.getText().length()>=2) {
                    if (Integer.parseInt(users.getText().toString()) >= 50) {
                        ((Buy) getActivity()).movetoLast(users.getText().toString());
                    }
                }
                else
                {
                    users.setError("minimum 50 users");
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Buy)getActivity()).movetofifth(null);
            }
        });


        return view;
    }

}
