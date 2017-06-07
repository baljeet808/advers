package com.nerdspoint.android.promoter.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nerdspoint.android.promoter.R;
import com.nerdspoint.android.promoter.fragments.Fifth;
import com.nerdspoint.android.promoter.fragments.Last;
import com.nerdspoint.android.promoter.fragments.Second;
import com.nerdspoint.android.promoter.fragments.Sixth;
import com.nerdspoint.android.promoter.fragments.Third;
import com.nerdspoint.android.promoter.fragments.first;
import com.nerdspoint.android.promoter.fragments.fourth;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class Buy extends AppCompatActivity {

    String Adname=null;
    String URL=null;
    String Description=null;
    String Note=null;
    String Money=null;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private List<String> stateList=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        first f =  new first();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frags, f);
        fragmentTransaction.commit();

    }

    public void backToFirst()
    {
        first f =  new first();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
  /*      fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left);*/
        fragmentTransaction.replace(R.id.frags, f);
        fragmentTransaction.commit();
    }

    public String getUrlName()
    {
        return Adname;
    }

    public void movetoSecond(String name)
    {
        Second second= new Second();

        if(Adname!=null) {
            Adname = name;
        }

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
      //  fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
        fragmentTransaction.replace(R.id.frags,second);
        fragmentTransaction.commit();

    }

    public void moveToMainPage()
    {
        Intent i = new Intent(Buy.this,MainPage.class);
        startActivity(i);
        finish();
    }

    public void movetothird(String url)
    {
        Third third= new Third();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
      //  fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
        fragmentTransaction.replace(R.id.frags,third);
        fragmentTransaction.commit();

        URL=url;
    }

    public void movetofourth(String Description)
    {
        if(Description!=null) {
            this.Description = Description;
        }
        fourth f = new fourth();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
      //  fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left);
        fragmentTransaction.replace(R.id.frags,f);
        fragmentTransaction.commit();

    }

    public void movetofifth(String Note)
    {
        if(Note!=null) {
            this.Note = Note;
        }
        Fifth fifth = new Fifth();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction= fragmentManager.beginTransaction();
      //  fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left);
        fragmentTransaction.replace(R.id.frags,fifth);
        fragmentTransaction.commit();

    }

    public void movetosixth(List<String> list)
    {
        if(list!=null) {
            this.stateList = list;
        }

        Sixth sixth = new Sixth();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
      //  fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left);
        fragmentTransaction.replace(R.id.frags,sixth);
        fragmentTransaction.commit();
    }

    public void movetoLast(String money)
    {
        if(money!=null) {
            this.Money = money;
        }
        Last last = new Last();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
     //   fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left);
        fragmentTransaction.replace(R.id.frags,last);
        fragmentTransaction.commit();

    }




    public String getUrl() {
        return URL;
    }

    public Bundle getvalues()
    {
        Bundle bundle = new Bundle();
        bundle.putString("urlName",Adname );
        bundle.putString("url",URL);
        bundle.putString("desc",Description);
        bundle.putString("Note",Note);
        bundle.putString("Money",Money);
        bundle.putStringArrayList("state",(ArrayList<String>) stateList);

        return bundle;
    }

    public String getdescription() {
        return Description;
    }

    public String getNote() {
        return Note;
    }


    public List<String> getStateList() {
        return stateList;
    }

    public String getMoney() {
        return Money;
    }
}
