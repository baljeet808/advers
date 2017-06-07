package com.nerdspoint.android.promoter.Databases;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by android on 5/5/2017.
 */

public class Sharedperps {

    static Sharedperps sharedperps;
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Sharedperps(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("ActiveUserDetails", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }


    public static synchronized Sharedperps getObject(Context context) {
        if (sharedperps != null) {
            return sharedperps;
        }
        return sharedperps = new Sharedperps(context);

    }


    public void setUserId(String userId) {
        editor.putString("UserId", userId);
        editor.apply();
    }

    public String getUserId() {
        return sharedPreferences.getString("UserId", "0");
    }

    public void setName(String name) {
        editor.putString("Name", name);
        editor.apply();
    }

    public String getName() {
        return sharedPreferences.getString("Name", "0");
    }

    public void setRevenue(String revenue) {
        editor.putString("Revenue", revenue);
        editor.apply();
    }

    public String getRevenue() {
        return sharedPreferences.getString("Revenue","00.00");
    }


    public void setVLinkIds(String VLinkIds) {
        editor.putString("VLinkIds", VLinkIds);
        editor.apply();
    }

    public String getVLinkIds() {
        return sharedPreferences.getString("VLinkIds", "0");
    }

    public void setPLinkIds(String PLinkIds) {
        editor.putString("PLinkIds", PLinkIds);
        editor.apply();
    }

    public String getPLinkIds() {
        return sharedPreferences.getString("PLinkIds", "0");
    }

    public void setEmail(String email) {
        editor.putString("Email", email);
        editor.apply();
    }

    public String getEmail() {
        return sharedPreferences.getString("Email", "0");
    }

    public void setPassword(String Password) {
        editor.putString("Password", Password);
        editor.apply();
    }

    public String getPassword() {
        return sharedPreferences.getString("Password", "0");
    }


    public void setIsActive(Boolean status) {
        editor.putBoolean("status", status);
        editor.apply();
    }

    public Boolean getIsActive() {
        return sharedPreferences.getBoolean("status", false);
    }
}
