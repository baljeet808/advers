package com.nerdspoint.android.promoter.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nerdspoint.android.promoter.Databases.OfflineDB;
import com.nerdspoint.android.promoter.Databases.Sharedperps;
import com.nerdspoint.android.promoter.R;
import com.nerdspoint.android.promoter.fragments.Login;
import com.nerdspoint.android.promoter.fragments.SignUp;

public class Splash extends AppCompatActivity {

    Animation animation,anima,anim2,slideUp;
    Button imnew,sign_btn;
    ImageView logo,plus;
    RelativeLayout fragments,forLogo,showcase;
    TextView tv_slogan;
    LinearLayout For_button;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {


        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);


        //connecting relative ids form layout
        For_button = (LinearLayout) findViewById(R.id.For_buttons);
        fragments = (RelativeLayout) findViewById(R.id.fragments);
        showcase = (RelativeLayout) findViewById(R.id.fragment_showcase);
        forLogo = (RelativeLayout) findViewById(R.id.For_logo);
        imnew = (Button) findViewById(R.id.imnew);
        sign_btn = (Button) findViewById(R.id.sign_BTN);
        logo = (ImageView) findViewById(R.id.logo);
        plus = (ImageView) findViewById(R.id.plus);
        tv_slogan = (TextView) findViewById(R.id.tv_slogan);


                                                                                            //animations
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pop);
        anima = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pop);
        anim2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pop);
        slideUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);


                                                                                            //initiating animations with timer
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logo.startAnimation(anima);
                logo.setVisibility(View.VISIBLE);

            }
        },1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                plus.setVisibility(View.VISIBLE);
                plus.startAnimation(animation);
            }
        },1300);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_slogan.startAnimation(anim2);
                tv_slogan.setVisibility(View.VISIBLE);
            }
        },1500);

        if(Sharedperps.getObject(getApplicationContext()).getIsActive())
        {
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 Intent i = new Intent(Splash.this,MainPage.class);
                 startActivity(i);
                 finish();
             }
         },2500);
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    For_button.startAnimation(slideUp);
                    For_button.setVisibility(View.VISIBLE);
                }
            }, 2500);
        }
       /* Login login = new Login();

        Bundle bundle = new Bundle();
        bundle.putString("Password",null);
        bundle.putString("Email",null);

        login.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.add(R.id.fragments, login);
        fragmentTransaction.commit();
*/

        new OfflineDB(getApplicationContext()).createHistory();

    }

    public void Skip(View  view)
    {
        Intent i = new Intent(Splash.this,MainPage.class);
        startActivity(i);
        finish();
    }

    public void imNew(View view)
    {
        sign_btn.setClickable(true);
        imnew.setClickable(false);
        forLogo.setVisibility(View.INVISIBLE);
        fragments.setVisibility(View.VISIBLE);
        showcase.setVisibility(View.INVISIBLE);

        SignUp signUp=new SignUp();
        if(fragmentTransaction!=null) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
            fragmentTransaction.replace(R.id.fragments, signUp);
            fragmentTransaction.commit();
        }
        else
        {

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
            fragmentTransaction.add(R.id.fragments, signUp);
            fragmentTransaction.commit();
        }
    }
    public void SignIn(View view)
    {
        sign_btn.setClickable(false);
        imnew.setClickable(true);
        forLogo.setVisibility(View.INVISIBLE);
        fragments.setVisibility(View.VISIBLE);
        showcase.setVisibility(View.INVISIBLE);

        Login login = new Login();

        Bundle bundle = new Bundle();
        bundle.putString("Password",null);
        bundle.putString("Email",null);

        if(fragmentTransaction!=null)
        {
        login.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.fragments, login);
        fragmentTransaction.commit();
        }
        else
        {
            login.setArguments(bundle);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragmentTransaction.add(R.id.fragments, login);
            fragmentTransaction.commit();
        }
    }

    public void moveToMainPage()
    {
        Intent i = new Intent(Splash.this,StartShowcase.class);
        startActivity(i);
        finish();
    }

    public void moveToSignIn(String password, String email) {
        sign_btn.setClickable(false);
        imnew.setClickable(true);
        forLogo.setVisibility(View.INVISIBLE);
        fragments.setVisibility(View.VISIBLE);
        showcase.setVisibility(View.INVISIBLE);

        Login login = new Login();

        Bundle bundle = new Bundle();
        bundle.putString("Password",password);
        bundle.putString("Email",email);

        login.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.fragments, login);
        fragmentTransaction.commit();
    }
}
