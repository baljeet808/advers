package com.nerdspoint.android.promoter.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nerdspoint.android.promoter.APIs.Links;
import com.nerdspoint.android.promoter.Activities.Splash;
import com.nerdspoint.android.promoter.Databases.Sharedperps;
import com.nerdspoint.android.promoter.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.blurry.Blurry;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment implements View.OnClickListener {


    EditText name,email,password,confirm;
    AutoCompleteTextView country;
    ArrayAdapter adapter;
    Button signUp;
    String[] countryList;
    LinearLayout signup_layout;


    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        signup_layout = (LinearLayout) view.findViewById(R.id.signUp_layout);

        name= (EditText) view.findViewById(R.id.name);
        email= (EditText) view.findViewById(R.id.Email2);
        password= (EditText) view.findViewById(R.id.Password);
        confirm= (EditText) view.findViewById(R.id.confirm_password);
        country= (AutoCompleteTextView) view.findViewById(R.id.country);

        signUp = (Button) view.findViewById(R.id.signUp_btn);
        signUp.setOnClickListener(this);
        countryList = new String[]{"ALBANIA"
                ,"ALGERIA"
                ,"ANDORRA"
                ,"ANGOLA"
                ,"ANGUILLA"
                ,"ANTIGUA & BARBUDA"
                ,"ARGENTINA",
                "ARMENIA"
                ,"ARUBA"
                ,"AUSTRALIA"
                ,"AUSTRIA"
                ,"AZERBAIJAN"
                ,"BAHAMAS"
                ,"BAHRAIN"
                ,"BARBADOS"
                ,"BELARUS"
                ,"BELGIUM"
                ,"BELIZE"
                ,"BENIN"
                ,"BERMUDA"
                ,"BHUTAN"
                ,"BOLIVIA"
                ,"OSNIA & HERZEGOVINA"
                ,"BOTSWANA"
                ,"BRAZIL"
                ,"BRITISH VIRGIN ISLANDS"
                ,"BRUNEI"
                ,"BULGARIA"
                ,"BURKINA FASO"
                ,"BURUNDI"
                ,"CAMBODIA"
                ,"CAMEROON"
                ,"CANADA"
                ,"CAPE VERDE"
                ,"CAYMAN ISLANDS"
                ,"CHAD"
                ,"CHILE"
                ,"CHINA"
                ,"COLOMBIA"
                ,"COMOROS"
                ,"CONGO - BRAZZAVILLE"
                ,"CONGO - KINSHASA"
                ,"COOK ISLANDS"
                ,"COSTA RICA",
                "CÔTE D’IVOIRE"
                ,"CROATIA"
                ,"CYPRUS"
                ,"CZECH REPUBLIC"
                ,"DENMARK"
                ,"DJIBOUTI"
                ,"DOMINICA"
                ,"DOMINICAN REPUBLIC"
                ,"ECUADOR"
                ,"EGYPT"
                ,"EL SALVADOR"
                ,"ERITREA"
                ,"ESTONIA"
                ,"ETHIOPIA"
                ,"FALKLAND ISLANDS"
                ,"FAROE ISLANDS"
                ,"FIJI"
                ,"FINLAND"
                ,"FRANCE"
                ,"FRENCH GUIANA"
                ,"FRENCH POLYNESIA"
                ,"GABON"
                ,"GAMBIA"
                ,"GEORGIA"
                ,"GERMANY"
                ,"GIBRALTAR"
                ,"GREECE"
                ,"GREENLAND"
                ,"GRENADA"
                ,"GUADELOUPE"
                ,"GUATEMALA"
                ,"GUINEA"
                ,"GUINEA-BISSAU"
                ,"GUYANA"
                ,"HONDURAS"
                ,"HONG KONG SAR CHINA"
                ,"HUNGARY"
                ,"ICELAND"
                ,"INDIA"
                ,"INDONESIA"
                ,"IRELAND",
                "ISRAEL"
                ,"ITALY"
                ,"JAMAICA"
                ,"JAPAN"
                ,"JORDAN"
                ,"KAZAKHSTAN",
                "KENYA"
                ,"KIRIBATI"
                ,"KUWAIT"
                ,"KYRGYZSTAN"
                ,"LAOS"
                ,"LATVIA"
                ,"LESOTHO"
                ,"LIECHTENSTEIN"
                ,"LITHUANIA"
                ,"LUXEMBOURG"
                ,"MACEDONIA"
                ,"MADAGASCAR"
                ,"MALAWI"
                ,"MALAYSIA"
                ,"MALDIVES"
                ,"MALI"
                ,"MALTA"
                ,"MARSHALL ISLAND"
                ,"MARTINIQUE"
                ,"MAURITANIA"
                ,"MAURITIUS"
                ,"MAYOTTE"
                ,"MEXICO"
                ,"MICRONESIA"
                ,"MOLDOVA"
                ,"MONACO"
                ,"MONGOLIA"
                ,"MONTENEGRO"
                ,"MONTSERRAT"
                ,"MOROCCO"
                ,"MOZAMBIQUE"
                ,"NAMIBIA"
                ,"NAURU"
                ,"NEPAL"
                ,"NETHERLANDS"
                ,"NEW CALEDONIA"
                ,"NEW ZEALAND"
                ,"NICARAGUA"
                ,"NIGER"
                ,"NIGERIA"
                ,"NORFOLK ISLAND"
                ,"NORWAY"
                ,"OMAN"
                ,"PALAU"
                ,"PANAMA"
                ,"PAPUA NEW GUINEA"
                ,"PARAGUAY"
                ,"PERU"
                ,"PHILIPPINES"
                ,"PITCAIRN ISLANDS"
                ,"POLAND"
                ,"PORTUGAL"
                ,"QATAR"
                ,"RÉUNION"
                ,"ROMANIA"
                ,"RUSSIA"
                ,"RWANDA"
                ,"SAMOA"
                ,"SAN MARINO"
                ,"SÃO TOMÉ & PRÍNCIPE"
                ,"SAUDI ARABIA"
                ,"SENEGAL"
                ,"SERBIA"
                ,"SEYCHELLES"
                ,"SIERRA LEONE"
                ,"SINGAPORE"
                ,"SLOVAKIA"
                ,"SLOVENIA"
                ,"SOLOMON ISLANDS"
                ,"SOMALIA"
                ,"SOUTH AFRICA"
                ,"SOUTH KOREA"
                ,"SPAIN"
                ,"SRI LANKA"
                ,"ST. HELENA"
                ,"ST. KITTS & NEVIS"
                ,"ST. LUCIA"
                ,"ST. PIERRE & MIQUELON"
                ,"ST. VINCENT & GRENADINES"
                ,"SURINAME"
                ,"SVALBARD & JAN MAYEN"
                ,"SWAZILAND"
                ,"SWEDEN"
                ,"SWITZERLAND"
                ,"TAIWAN"
                ,"TAJIKISTAN"
                ,"TANZANIA"
                ,"THAILAND"
                ,"TOGO"
                ,"TONGA"
                ,"TRINIDAD & TOBAGO"
                ,"TUNISIA"
                ,"TURKMENISTAN"
                ,"TURKS & CAICOS ISLANDS"
                ,"TUVALU"
                ,"UGANDA"
                ,"UKRAINE"
                ,"UNITED ARAB EMIRATES"
                ,"UNITED KINGDOM"
                ,"UNITED STATES"
                ,"URUGUAY"
                ,"VANUATU"
                ,"VATICAN CITY"
                ,"VENEZUELA"
                ,"VIETNAM"
                ,"WALLIS & FUTUNA"
                ,"YEMEN"
                ,"ZAMBIA"
                ,"ZIMBABWE"};

            adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_dropdown_item_1line,countryList);
        country.setAdapter(adapter);
        country.setThreshold(1);
        return view;
    }

    @Override
    public void onClick(View v) {
        int check=0;
        String nam = name.getText().toString();
        if(nam.length()<2)
        {
            name.setError("enter your name");
            check=1;
        }

        {
            String Email = email.getText().toString();
            if(Email.length()<6)
            {
                email.setError("enter a valid email");
                check=1;
            }

            {


                if(country.getText().length()<2)
                {
                    country.setError("select a country first");
                    check=1;
                }

                {

                    String Country = country.getText().toString();

                    if(password.length()!=8)
                    {
                        password.setError("please enter 8-digit pin");
                        check=1;
                    }
                    {
                        String Password = password.getText().toString();
                        if(confirm.getText().toString().equals(password)!=false)
                        {
                            confirm.setError("password doesn't Match");
                            check=1;
                        }
                        if(check==0)
                        {
                            SaveUser(nam,Email,Country,Password);
                        }
                    }
                }
            }
        }
    }

    private void SaveUser(final String nam, final String email, final String country, final String password)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog alert = builder.create();
        alert.setTitle("Creating Account");
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
                Blurry.delete((ViewGroup) signup_layout.getRootView());
            }
        });
        alert.show();
        Blurry.with(getActivity()).radius(25).sampling(2).onto((ViewGroup) signup_layout.getRootView());


        StringRequest request = new StringRequest(Request.Method.POST, new Links(getActivity()).SignUp, new Response.Listener<String>() {

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
                            Blurry.delete((ViewGroup) signup_layout.getRootView());

                            ((Splash)getActivity()).moveToSignIn(password,email);

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
                            Blurry.delete((ViewGroup) signup_layout.getRootView());
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
                        Blurry.delete((ViewGroup) signup_layout.getRootView());
                    }
                },1500);     }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Email", email);
                map.put("Password", password);
                map.put("Country", country);
                map.put("Name", nam);

                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);

    }
}
