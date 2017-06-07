package com.nerdspoint.android.promoter.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.util.FloatProperty;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.blurry.Blurry;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment implements View.OnClickListener{

    Button  signIn_btn;
    EditText email,Passwword;
    LinearLayout login_layout;


    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);

        signIn_btn = (Button) view.findViewById(R.id.signIn_btn);
        signIn_btn.setOnClickListener(this);

        email = (EditText) view. findViewById(R.id.Email);
        Passwword = (EditText) view. findViewById(R.id.Password);
        login_layout = (LinearLayout) view.findViewById(R.id.login_layout);

        Bundle bundle = getArguments();

        if(bundle.getString("Email",null)!=null)
        {
            email.setText(bundle.getString("Email"));
            Passwword.setText(bundle.getString("Password"));
        }


        return view;
    }

    @Override
    public void onClick(View v) {

        String Email = email.getText().toString();
        String pass = Passwword.getText().toString();
        int check = 0;

        if(Email.length()<6 )
        {
            email.setError("email not valid");
            check=1;
        }


            if(pass.length()!=8)
            {
                Passwword.setError("please enter 8 digit password");
                check=1;
            }

            if(check==0)
            {
                login(v);
            }

    }

    public void login(View v)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        final AlertDialog alert = builder.create();
        alert.setTitle("Loging In");
        final ProgressBar progressBar = new ProgressBar(getActivity());

        final ImageView tick = new ImageView(getActivity());
        tick.setImageResource(R.drawable.tick);

        final ImageView wrong = new ImageView(getActivity());
        wrong.setImageResource(R.drawable.red_cross);


        LinearLayout layout= new LinearLayout(getActivity());
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
                Blurry.delete((ViewGroup) login_layout.getRootView());
            }
        });
        alert.show();
        Blurry.with(getActivity()).radius(25).sampling(2).onto((ViewGroup) login_layout.getRootView());


        StringRequest request = new StringRequest(Request.Method.POST, new Links(getActivity()).SignIn, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Response", response + "ok");
                if (response.equals("fail") == false) {

                    progressBar.setVisibility(View.GONE);
                    tick.setVisibility(View.VISIBLE);

                    alert.setView(tick);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alert.cancel();
                            Blurry.delete((ViewGroup) login_layout.getRootView());
                        }
                    },1500);

                    try {

                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);

                        Sharedperps.getObject(getActivity()).setUserId(jsonObject.getString("UserId"));
                        Sharedperps.getObject(getActivity()).setName(jsonObject.getString("Name"));
                        Sharedperps.getObject(getActivity()).setRevenue(jsonObject.getString("Revenue"));
                        Sharedperps.getObject(getActivity()).setPassword(Passwword.getText().toString());
                        Sharedperps.getObject(getActivity()).setEmail(email.getText().toString());
                        Sharedperps.getObject(getActivity()).setIsActive(true);

                        ((Splash)getActivity()).moveToMainPage();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                     } else {
                    progressBar.setVisibility(View.GONE);
                    wrong.setVisibility(View.VISIBLE);

                    alert.setView(tick);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alert.cancel();
                            Blurry.delete((ViewGroup) login_layout.getRootView());
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
                        Blurry.delete((ViewGroup) login_layout.getRootView());
                    }
                },1500);     }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Email", email.getText().toString());
                map.put("Password", Passwword.getText().toString());
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);

    }
}
