package com.example.justfollow.adapter;


public class Localitem {
    private String mName;
    private  int mFlagImage;

    public Localitem(String Name, Integer flagImage){
        mName = Name;

        mFlagImage = flagImage;
    }
    public String getmName(){
        return mName;
    }

    public int getmFlagImage(){
        return mFlagImage;
    }

}

