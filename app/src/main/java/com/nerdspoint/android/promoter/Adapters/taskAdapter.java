package com.nerdspoint.android.promoter.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nerdspoint.android.promoter.Activities.FragmentHolder;
import com.nerdspoint.android.promoter.ForList.taskDetail;
import com.nerdspoint.android.promoter.R;
import com.nerdspoint.android.promoter.fragments.Tasks;

import java.util.List;

/**
 * Created by android on 5/14/2017.
 */

public class taskAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<taskDetail> list;
    taskDetail detail;
    List<String> linkIds;

    public taskAdapter(Context context,List<taskDetail> list,List<String> linkIds) {
        this.context = context;
        this.list = list;
        this.linkIds = linkIds;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    public static class ViewHolder{

        public TextView urlName;
        public TextView Note;
        public TextView remain ;
        public TextView linkid;

    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;

        if(convertView==null){

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.cards, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new  ViewHolder();
            holder.Note = (TextView) vi.findViewById(R.id.note);
            holder.urlName = (TextView) vi.findViewById(R.id.urlName);
            holder.remain = (TextView) vi.findViewById(R.id.remain);
            holder.linkid = (TextView) vi.findViewById(R.id.linkid);


            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else {
            holder = ( ViewHolder) vi.getTag();
        }




        {
            /***** Get each Model object from Arraylist ********/
            detail = null;
            detail =list.get(position);

            /************  Set Model values in Holder elements ***********/
            if(linkIds.contains(detail.LinkId))
            {
                vi.setVisibility(View.GONE);
            }
            holder.urlName.setText(detail.urlName);


            holder.Note.setText(detail.note);
            /******** Set Item Click Listner for LayoutInflater for each row *******/
            holder.remain.setText(detail.remain);
            holder.linkid.setText(detail.LinkId);

        }
        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(context, ""+detail.LinkId, Toast.LENGTH_SHORT).show();
                ((FragmentHolder)context).move(detail);
            }
        });

        return vi;
    }
}
