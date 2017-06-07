package com.nerdspoint.android.promoter.Activities;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nerdspoint.android.promoter.R;

public class LinkDetail extends AppCompatActivity {

    String LinkId,Remain,UrlName,URL,Description,Note,ImageName;

    TextView remain,desc,urlNam,note;

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_detail);

        Intent i = getIntent();
        LinkId= i.getStringExtra("LinkId");
        UrlName = i.getStringExtra("UrlName");
        URL = i.getStringExtra("URL");
        Description = i.getStringExtra("Description");
        Note=i.getStringExtra("Note");
        ImageName=i.getStringExtra("ImageName");
        Remain = i.getStringExtra("Remain");


        urlNam= (TextView) findViewById(R.id.url_name);
        desc= (TextView) findViewById(R.id.desc);
        remain = (TextView) findViewById(R.id.remain_tv);
        note= (TextView) findViewById(R.id.not);
        image = (ImageView) findViewById(R.id.image1);

        urlNam.setText(UrlName);
        desc.setText(Description);
        remain.setText(Remain);
        urlNam.setText(UrlName);

    }


    public void openLink(View view)
    {
        Intent i = new Intent(LinkDetail.this,WeebView.class);
        startActivity(i);
    }
}
