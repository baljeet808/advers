package com.nerdspoint.android.promoter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nerdspoint.android.promoter.ForList.taskDetail;
import com.nerdspoint.android.promoter.R;
import com.nerdspoint.android.promoter.fragments.Tasks;

public class FragmentHolder extends AppCompatActivity {


    RelativeLayout frag;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);

        frag = (RelativeLayout) findViewById(R.id.frag);

        Tasks task = new Tasks();


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frag, task);
        fragmentTransaction.commit();
        try {
            Snackbar.make(getParent().getCurrentFocus(), "Swipe down to refresh", 2000).setAction("Action", null).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "swipe down to refresh", Toast.LENGTH_SHORT).show();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FragmentHolder.this,MainPage.class);
                startActivity(i);
                finish();
            }
        });



    }

    public void move(taskDetail detail) {
        Intent i = new Intent(FragmentHolder.this,LinkDetail.class);
        i.putExtra("LinkId",detail.LinkId);
        i.putExtra("UrlName",detail.urlName);
        i.putExtra("URL",detail.url);
        i.putExtra("Remain",detail.remain);
        i.putExtra("ImageName",detail.ImageName);
        i.putExtra("Description",detail.description);
        i.putExtra("Note",detail.note);


        startActivity(i);
        finish();
    }
}
