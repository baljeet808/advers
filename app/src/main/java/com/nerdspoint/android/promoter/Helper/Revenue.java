package com.nerdspoint.android.promoter.Helper;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.nerdspoint.android.promoter.Activities.MainPage;
import com.nerdspoint.android.promoter.Activities.Splash;
import com.nerdspoint.android.promoter.Databases.Sharedperps;
import com.nerdspoint.android.promoter.R;

import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.blurry.Blurry;

/**
 * Created by android on 5/13/2017.
 */

public class Revenue {

    Context context;
    static Revenue revenue;


    public Revenue(Context context)
    {
        this.context=context;
    }

    public static synchronized Revenue getObject(Context context)
    {
        if(revenue!=null)
        {
            return revenue;
        }
        return  new Revenue(context);
    }

    public void updateRevenue(final String LinkId,final LinearLayout view)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alert = builder.create();
        alert.setTitle("Updating Revenue");
        final ProgressBar progressBar = new ProgressBar(context);

        final ImageView tick = new ImageView(context);
        tick.setImageResource(R.drawable.tick);

        final ImageView wrong = new ImageView(context);
        wrong.setImageResource(R.drawable.red_cross);


        LinearLayout layout= new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(progressBar);
        layout.addView(tick);
        layout.addView(wrong);

        tick.setVisibility(View.GONE);
        wrong.setVisibility(View.GONE);


        alert.setView(layout);
        alert.setCancelable(false);
        alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Blurry.delete((ViewGroup) view.getRootView());
            }
        });
        alert.show();
        Blurry.with(context).radius(25).sampling(2).onto((ViewGroup) view.getRootView());


        StringRequest request = new StringRequest(Request.Method.POST, new Links(context).LinkClicked, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Response", response + "ok");
                if (response.equals("failed") == false) {

                    progressBar.setVisibility(View.GONE);
                    tick.setVisibility(View.VISIBLE);

                    alert.setView(tick);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alert.cancel();
                            Blurry.delete((ViewGroup) view.getRootView());
                            Sharedperps.getObject(context).setRevenue(Sharedperps.getObject(context).getRevenue()+0.02f);
                            ((MainPage)context).setRevenue();
                        }
                    },1500);







                } else {
                    progressBar.setVisibility(View.GONE);
                    wrong.setVisibility(View.VISIBLE);

                    alert.setView(tick);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alert.cancel();
                            Blurry.delete((ViewGroup) view.getRootView());
                        }
                    },1500);         }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", "error => " + error.toString());


                progressBar.setVisibility(View.GONE);
                wrong.setVisibility(View.VISIBLE);

                alert.setView(tick);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        alert.cancel();
                        Blurry.delete((ViewGroup) view.getRootView());
                    }
                },1500);     }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("LinkId",LinkId );
                map.put("UserId", Sharedperps.getObject(context).getUserId());

                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);


    }


}
