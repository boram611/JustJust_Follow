package com.example.justfollow;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Frag3 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View moreview = inflater.inflate(R.layout.activity_frag3, container, false);



        Button callbtn = (Button)moreview.findViewById(R.id.bt_call);

        callbtn.setOnClickListener(new View.OnClickListener() {

            @Override

            //전화걸기 *아래 AndroidManifest에서 permission(허가)을 주어야 바로 전화거 걸립니다
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1330"));
                startActivity(intent);
            }

        });
        return moreview;
    }

}