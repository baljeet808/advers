package com.nerdspoint.android.promoter.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nerdspoint.android.promoter.APIs.Links;
import com.nerdspoint.android.promoter.Activities.Splash;
import com.nerdspoint.android.promoter.Adapters.taskAdapter;
import com.nerdspoint.android.promoter.Databases.OfflineDB;
import com.nerdspoint.android.promoter.ForList.taskDetail;
import com.nerdspoint.android.promoter.Helper.OnSwipeTouchListener;
import com.nerdspoint.android.promoter.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.blurry.Blurry;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tasks extends Fragment {


    ListView listView;
    List<taskDetail> list;
    taskAdapter adapter;
    SwipeRefreshLayout refreshLayout;
    taskDetail detail;
    TextView countTask;
    List<String> linkIds;
    Animation open,close;
    LinearLayout drawer;

    public Tasks() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_tasks, container, false);
        countTask = (TextView) view.findViewById(R.id.countTask);
        listView = (ListView) view.findViewById(R.id.task_list);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        list = new ArrayList<>();



        linkIds = new ArrayList<>();

        Cursor cursor = new OfflineDB(getActivity()).getLinkIds();
        if(cursor.moveToFirst()) {
            while (cursor.isAfterLast())
            {
                   linkIds.add(cursor.getString(cursor.getColumnIndex("LinkId")));
                cursor.moveToNext();
            }
        }
        adapter = new taskAdapter(getActivity(),list,linkIds);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView linkid =(TextView) view.findViewById(R.id.linkid);


            }
        });

        refreshLayout.setColorSchemeResources(R.color.color_2, R.color.color_16, R.color.color_12);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        StringRequest request = new StringRequest(Request.Method.POST, new Links(getActivity()).FetchActiveLinks, new Response.Listener<String>() {

                            @Override
                            public void onResponse(final String response) {
                                Log.d("Response", response + "ok");
                                if (response.equals("failed") == false) {
                                    list.clear();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                             Blurry.delete((ViewGroup) view.getRootView());
                                            try {
                                                JSONArray jsonArray = new JSONArray(response);
                                                countTask.setText(""+jsonArray.length()+" new");
                                                for(int i =0 ; i<jsonArray.length();i++)
                                                {
                                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                    detail= new taskDetail();
                                                    detail.LinkId = jsonObject.get("LinkId").toString();
                                                    detail.description = jsonObject.get("Description").toString();
                                                    detail.urlName=jsonObject.get("UrlName").toString();
                                                    detail.url = jsonObject.get("URL").toString();
                                                    detail.remain = jsonObject.get("UnitRemain").toString();
                                                    detail.ImageName= jsonObject.get("ImageName").toString();
                                                    detail.note = jsonObject.get("Note").toString();
                                                    list.add(detail);
                                                }

                                                refreshLayout.setRefreshing(false);
                                                adapter.notifyDataSetChanged();


                                            }
                                            catch (Exception e)
                                            {

                                            }

                                        }
                                    },1500);







                                } else {
                                    refreshLayout.setRefreshing(false);

                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("ERROR", "error => " + error.toString());

                                refreshLayout.setRefreshing(false);

                            }
                        }
                        ) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> map = new HashMap<>();

                                return map;
                            }
                        };

                        RequestQueue queue = Volley.newRequestQueue(getActivity());
                        queue.add(request);

                    }
                },2000);
            }
        });
        drawer = (LinearLayout) view.findViewById(R.id.drawer_layout);

        close = AnimationUtils.loadAnimation(getActivity(),R.anim.translate);
        open = AnimationUtils.loadAnimation(getActivity(),R.anim.transalte_back);

    /*    view.setOnTouchListener(new OnSwipeTouchListener(getActivity())
                                {

                                    @Override
                                    public void onSwipeLeft() {

                                        drawer.clearAnimation();
                                        drawer.startAnimation(close);
                                    }
                                    @Override
                                    public void onSwipeRight()
                                    {
                                        drawer.clearAnimation();
                                        drawer.startAnimation(open);
                                    }
                                }
        );*/

        return view;
    }

}
