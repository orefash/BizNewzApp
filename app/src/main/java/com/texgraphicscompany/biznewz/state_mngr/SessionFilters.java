package com.texgraphicscompany.biznewz.state_mngr;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SessionFilters {
    private SharedPreferences prefs;

    public SessionFilters(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setCountryFilter(String country){
        prefs.edit().putString("country_filter", country).apply();
    }

    public String getCountryFilter(){
        String country = prefs.getString("country_filter", "all");
        return country;
    }

    public void setSourceFilter(String source){
        prefs.edit().putString("source_filter", source).apply();
    }

    public String getSourceFilter(){
        String source = prefs.getString("source_filter", "all");
        return source;
    }

    public String getFromDateFilter(){
        String date = prefs.getString("from_date", "");
        return date;
    }

    public void setFromDateFilter(String fromDate){
        prefs.edit().putString("from_date", fromDate).apply();
    }

    public String getToDateFilter(){
        String date = prefs.getString("to_date", "");
        return date;
    }

    public void setToDateFilter(String toDate){
        prefs.edit().putString("to_date", toDate).apply();
    }

}
