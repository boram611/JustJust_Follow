package com.example.justfollow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

public class Local2_1 extends AppCompatActivity {
    private final String packageName = "com.kakao.taxi";
    private Intent taxi;
    private RatingBar ratingbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local2_1);


        ratingbar = findViewById(R.id.rating);

        ImageButton map = (ImageButton) findViewById(R.id.map);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }

        });
        taxi = this.getPackageManager().getLaunchIntentForPackage(packageName);
        ImageButton imageButtons = (ImageButton) findViewById(R.id.taxi);
        imageButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Local2_1.this.startActivity(taxi);
            }
        });

        ImageButton review = (ImageButton) findViewById(R.id.review);

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(intent);
            }

        });

        ImageButton information = (ImageButton) findViewById(R.id.information);

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://english.visitseoul.net/walking-tour/BukchonHanokVillage_/622"));
                startActivity(intent);
            }

        });

            }
        }






