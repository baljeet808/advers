package com.nerdspoint.android.promoter.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nerdspoint.android.promoter.Databases.Sharedperps;
import com.nerdspoint.android.promoter.Helper.OnSwipeTouchListener;
import com.nerdspoint.android.promoter.R;
import com.nerdspoint.android.promoter.fragments.Tasks;

public class MainPage extends AppCompatActivity{



    Animation close,open;
    //LinearLayout drawer;
    ImageView buy_icon,task_icon,passbook_icon,history_icon,setting_icon,rate_icon,share_icon;
    TextView buy_tv,task_tv,passbook_tv,history_tv,setting_tv,help_tv,rate_tv,share_tv,help_icon,revenue_tv,userName_tv;

    Typeface type;
    boolean tag=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        View view = findViewById(R.id.mainPage);

        close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);
        open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.transalte_back);

        buy_icon=(ImageView) findViewById(R.id.buy_icon);
        task_icon=(ImageView) findViewById(R.id.task_icon);
        passbook_icon=(ImageView) findViewById(R.id.passbook_icon);
        history_icon=(ImageView) findViewById(R.id.history_icon);
        setting_icon=(ImageView) findViewById(R.id.setting_icon);
        help_icon=(TextView) findViewById(R.id.help_icon);
        rate_icon=(ImageView) findViewById(R.id.rate_icon);
        share_icon=(ImageView) findViewById(R.id.share_icon);

        buy_tv=(TextView) findViewById(R.id.buy_tv);
        task_tv=(TextView) findViewById(R.id.task_tv);
        passbook_tv=(TextView) findViewById(R.id.passbook_tv);
        history_tv=(TextView) findViewById(R.id.history_tv);
        setting_tv=(TextView) findViewById(R.id.setting_tv);
        help_tv=(TextView) findViewById(R.id.help_tv);
        rate_tv=(TextView) findViewById(R.id.rate_tv);
        share_tv=(TextView) findViewById(R.id.share_tv);
        revenue_tv=(TextView) findViewById(R.id.revenue_tv);
        userName_tv = (TextView) findViewById(R.id.userName_tv);

        type = Typeface.createFromAsset(getAssets(),"fonts/kenpixel_high_square.ttf");
        revenue_tv.setTypeface(type);

        userName_tv.setText(Sharedperps.getObject(getApplicationContext()).getName());
        revenue_tv.setText("$ "+Sharedperps.getObject(getApplicationContext()).getRevenue());

    }

    public void setRevenue()
    {
            revenue_tv.setText("$ "+ Sharedperps.getObject(getApplicationContext()).getRevenue());
    }

    public void action(View v)
    {
        if(v.getId()==R.id.task_icon || v.getId()==R.id.task_tv)
        {
            Intent i = new Intent(MainPage.this,FragmentHolder.class);
            startActivity(i);
            finish();
        }else if(v.getId()==R.id.buy_icon || v.getId()==R.id.buy_tv)
        {
            Intent i = new Intent(MainPage.this,Buy.class);
            startActivity(i);
            finish();

            }else if(v.getId()==R.id.passbook_icon || v.getId()==R.id.passbook_tv)
        {

            }else if(v.getId()==R.id.history_icon || v.getId()==R.id.history_tv)
        {


            }else if(v.getId()==R.id.setting_icon || v.getId()==R.id.setting_tv)
        {

            }else if(v.getId()==R.id.help_icon || v.getId()==R.id.help_icon)
        {


            }else if(v.getId()==R.id.rate_icon || v.getId()==R.id.rate_tv)
        {


            }else if(v.getId()==R.id.share_icon || v.getId()==R.id.share_tv)
        {

           }

    }

}
