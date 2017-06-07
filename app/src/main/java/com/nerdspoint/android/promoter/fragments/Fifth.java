package com.nerdspoint.android.promoter.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.nerdspoint.android.promoter.Activities.Buy;
import com.nerdspoint.android.promoter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fifth extends Fragment {


    Spinner states;
    ListView selectedStates;
    Button next,back;
    ArrayAdapter<String>  adapter;

    List<String> list;

    public Fifth() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fifth, container, false);

        states = (Spinner) view.findViewById(R.id.spinner_state);
        next= (Button) view.findViewById(R.id.next_btn);
        back=(Button) view.findViewById(R.id.previous_btn);
        selectedStates = (ListView) view.findViewById(R.id.states_LIST);

        list = new ArrayList<>();

        adapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,list);
        selectedStates.setAdapter(adapter);

        states.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                list.add(item);
                adapter.notifyDataSetChanged();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(list.size()>0) {
                    ((Buy) getActivity()).movetosixth(list);
                }
                else
                {
                    Toast.makeText(getActivity(), "please select atleast one state of India", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Buy)getActivity()).movetofourth(null);
            }
        });

        return view;
    }

}
