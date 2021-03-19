package com.example.justfollow.adapter;


public class Countryitem {
    private String mCountryName;
    private  int mFlagImage;

    public Countryitem(String countryName, Integer flagImage){
        mCountryName = countryName;

        mFlagImage = flagImage;
    }
    public String getmCountryName(){
        return mCountryName;
    }

    public int getmFlagImage(){
        return mFlagImage;
    }

}

