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

import static android.R.attr.description;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fifth extends Fragment {


    Spinner states;
    ListView selectedStates;
    Button next,back;
    ArrayAdapter<String>  adapter;

    List<String> list,list1;
    Boolean lock=false;

    public Fifth() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fifth, container, false);
        list = new ArrayList<>();




        states = (Spinner) view.findViewById(R.id.spinner_state);
        next= (Button) view.findViewById(R.id.next_btn);
        back=(Button) view.findViewById(R.id.previous_btn);
        selectedStates = (ListView) view.findViewById(R.id.states_LIST);



        adapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,list);
        selectedStates.setAdapter(adapter);




        try {
            list1 =((Buy) getActivity()).getStateList();
        }
        catch(Exception e)
        {

        }
        if(list1!=null)
        {
            list=((Buy) getActivity()).getStateList();
            adapter.notifyDataSetChanged();
        }

        states.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                    if(lock) {
                        String item = parent.getItemAtPosition(position).toString();
                        list.add(item);
                        adapter.notifyDataSetChanged();
                    }
                    else
                    {
                        lock= true;
                    }

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

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
