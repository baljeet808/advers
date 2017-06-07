package com.nerdspoint.android.promoter.APIs;

import android.content.Context;

/**
 * Created by android on 5/10/2017.
 */

public class Links {

    Context context;
    static Links links;
    public String NewLink ="newLink.php";
    public String SignUp ="SignUp.php";
    public String SignIn ="SignIn.php";
    public String FetchUserLinks ="FetchUserLinks.php";
    public String ActivateLink ="ActivateLink.php";
    public String FetchActiveLinks ="FetchActiveLinks.php";
    public String FetchSingleLink ="FetchSingleLink.php";
    public String FetchTransactionHistory ="FetchTransactionHistory.php";
    public String UpdateTransaction ="UpdateTransaction.php";
    public String LinkClicked ="LinkClicked.php";
    public String FetchHistory ="FetchHistory.php";
    public String UpdateHistory ="updateHistory.php";
    public String updateRevenue ="updateRevenue.php";



    public String url="https://app-1494398379.000webhostapp.com/promoter/";


    public Links(Context context)
    {
        this.context=context;
        NewLink = url+""+NewLink;
        SignIn = url+""+SignIn;
        SignUp = url+""+SignUp;
        FetchActiveLinks = url+""+FetchActiveLinks;
        FetchSingleLink = url+""+FetchSingleLink;
        UpdateTransaction = url+""+UpdateTransaction;
        LinkClicked = url+""+LinkClicked;
        FetchTransactionHistory = url+""+FetchTransactionHistory;
        ActivateLink = url+""+ActivateLink;
        FetchUserLinks = url+""+FetchUserLinks;
        UpdateHistory=url+""+UpdateHistory;
        FetchHistory = url+""+FetchHistory;
        updateRevenue = url+""+updateRevenue;

    }

    public static synchronized Links getObject(Context context)
    {
        if(links != null)
        {
            return links;
        }
        return links = new Links(context);
    }

}
