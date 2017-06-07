package com.nerdspoint.android.promoter.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;
import com.nerdspoint.android.promoter.Databases.Sharedperps;
import com.nerdspoint.android.promoter.R;

import java.util.ArrayList;
import java.util.List;

public class StartShowcase extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* if(Sharedperps.getObject(getApplicationContext()).getIsActive())
        {
            Intent i = new Intent(StartShowcase.this,Splash.class);
            startActivity(i);
            finish();
        }*/


        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Cool Way of Earning Money", "Get Paid 0.02$ for Browsing or Downloading Content Online", R.drawable.wallet);

        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Cheaper Promotion", "Get Responsive and Genuine User at very low price", R.drawable.buuy);

        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Daily Earning", "Helping you earn daily with minimum withdraw limit of 10$", R.drawable.calendar);

        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);

        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);

        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.white);
            page.setDescriptionColor(R.color.grey_200);
        }

        setFinishButtonTitle("Get Started");
        showNavigationControls(true);
        setGradientBackground();
        //setImageBackground(R.drawable.blured);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        //setFont(face);

        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.white);

        setOnboardPages(pages);

    }

    @Override
    public void onFinishButtonPressed() {

        Intent i = new Intent(StartShowcase.this,MainPage.class);
        startActivity(i);
        finish();

    }
}
