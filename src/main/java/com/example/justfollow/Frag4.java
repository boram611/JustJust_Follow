package com.example.justfollow;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Frag4 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View moreview = inflater.inflate(R.layout.activity_frag4, container, false);



        Button callbtn = (Button)moreview.findViewById(R.id.btn_call1);

        callbtn.setOnClickListener(new View.OnClickListener() {

            @Override

            //전화걸기 *아래 AndroidManifest에서 permission(허가)을 주어야 바로 전화거 걸립니다
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:02-2003-0100"));
                startActivity(intent);
            }

        });

        Button callbtn2 = (Button)moreview.findViewById(R.id.btn_call2);

        callbtn2.setOnClickListener(new View.OnClickListener() {

            @Override

            //전화걸기 *아래 AndroidManifest에서 permission(허가)을 주어야 바로 전화거 걸립니다
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:02-397-4114"));
                startActivity(intent);
            }

        });

        Button callbtn3 = (Button)moreview.findViewById(R.id.btn_call3);

        callbtn3.setOnClickListener(new View.OnClickListener() {

            @Override

            //전화걸기 *아래 AndroidManifest에서 permission(허가)을 주어야 바로 전화거 걸립니다
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:02-2170-5200"));
                startActivity(intent);
            }

        });

        Button callbtn4 = (Button)moreview.findViewById(R.id.btn_call4);

        callbtn4.setOnClickListener(new View.OnClickListener() {

            @Override

            //전화걸기 *아래 AndroidManifest에서 permission(허가)을 주어야 바로 전화거 걸립니다
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:02-3210-5500"));
                startActivity(intent);
            }

        });


        return moreview;
    }

}