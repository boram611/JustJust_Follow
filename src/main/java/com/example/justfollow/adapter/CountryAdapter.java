package com.example.justfollow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.justfollow.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class CountryAdapter extends ArrayAdapter<Countryitem> {
    public CountryAdapter(Context context, ArrayList<Countryitem> Countrylist){
        super(context,0,Countrylist);
    }
    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        return initView(position,convertView,parent);
    }
    @Override
    public View getDropDownView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        return initView(position,convertView,parent);
    }
    private View initView(int position,View convertView,ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.country_spinner_row,parent,false
            );
        }
        ImageView imageViewFlag = convertView.findViewById(R.id.image_view_flag);
        TextView textViewName = convertView.findViewById(R.id.text_view_name);

        Countryitem currentItem = getItem(position);

        if (currentItem != null){
            imageViewFlag.setImageResource(currentItem.getmFlagImage());
            textViewName.setText(currentItem.getmCountryName());
        }
        return convertView;
    }
}
