package com.nerdspoint.android.promoter.fragments;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nerdspoint.android.promoter.Activities.Buy;
import com.nerdspoint.android.promoter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Last extends Fragment {

    Bundle bundle;
    String Adname;
    String URL;
    String Description;
    String Note;
    String Money;

    TextView urlName, url, money, description, note, states;
    Button back,save;

    private List<String> stateList;

    public Last() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_last, container, false);

        urlName = (TextView) view.findViewById(R.id.name_view);
        url = (TextView) view.findViewById(R.id.url_view);
        description = (TextView) view.findViewById(R.id.desc_view);
        note = (TextView) view.findViewById(R.id.nnote_view);
        money = (TextView) view.findViewById(R.id.money_view);
        states = (TextView) view.findViewById(R.id.state_view);

        back = (Button) view.findViewById(R.id.prev_btn);
        save = (Button) view.findViewById(R.id.save_btn);

        bundle = ((Buy)getActivity()).getvalues();

       Adname =  bundle.getString("urlName");
        URL = bundle.getString("url");
        Description= bundle.getString("desc");
        Note=bundle.getString("Note");
        Money = bundle.getString("Money");
        stateList=bundle.getStringArrayList("state");

        urlName.setText(Adname);

        url.setText(URL);
        description.setText(Description);
        note.setText(Note);
        money.setText(Money);
        states.setText(""+stateList);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Buy)getActivity()).movetosixth(null);
            }
        });

        return view;
    }

}
