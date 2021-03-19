package com.example.justfollow;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

public class Local extends AppCompatActivity {

    Animation translateLeft;
    Animation translateRight;
    boolean isPageOpen = false;
    LinearLayout page;
    navmenuitemAdapter adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);


        page = (LinearLayout) findViewById(R.id.page);


        translateLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRight = AnimationUtils.loadAnimation(this, R.anim.tranlate_right);


        Local.SlidingAnimationListener listener = new Local.SlidingAnimationListener();
        translateLeft.setAnimationListener(listener);
        translateRight.setAnimationListener(listener);

        adapter1 = new navmenuitemAdapter();

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter1);

        adapter1.addItem(R.drawable.h1, "Directions");
        adapter1.addItem(R.drawable.h2, "Translator");
        adapter1.addItem(R.drawable.h3, "Exchange Rates");
        adapter1.addItem(R.drawable.h4, "Helpful Number");
        adapter1.addItem(R.drawable.h5, "Notice");
        adapter1.addItem(R.drawable.h6, "FAQ");
        adapter1.addItem(R.drawable.h8, "Setting");

        adapter1.notifyDataSetChanged();


        //버튼
        ImageButton local01 = (ImageButton) findViewById(R.id.local01);

        local01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local01.class);
                startActivity(intent);
            }

        });

        ImageButton local02 = (ImageButton) findViewById(R.id.local02);

        local02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local02.class);
                startActivity(intent);
            }

        });
        ImageButton local03 = (ImageButton) findViewById(R.id.local03);

        local03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local03.class);
                startActivity(intent);
            }

        });

        ImageButton local04 = (ImageButton) findViewById(R.id.local04);

        local04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local04.class);
                startActivity(intent);
            }

        });
        ImageButton local05 = (ImageButton) findViewById(R.id.local05);

        local05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local05.class);
                startActivity(intent);
            }

        });

        ImageButton local06 = (ImageButton) findViewById(R.id.local06);

        local06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local06.class);
                startActivity(intent);
            }

        });
        ImageButton local07 = (ImageButton) findViewById(R.id.local07);

        local07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local07.class);
                startActivity(intent);
            }

        });

        ImageButton local08 = (ImageButton) findViewById(R.id.local08);

        local08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local08.class);
                startActivity(intent);
            }

        });
        ImageButton local09 = (ImageButton) findViewById(R.id.local09);

        local09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local09.class);
                startActivity(intent);
            }

        });

        ImageButton local10 = (ImageButton) findViewById(R.id.local10);

        local10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local10.class);
                startActivity(intent);
            }

        });
        ImageButton local11 = (ImageButton) findViewById(R.id.local11);

        local11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local11.class);
                startActivity(intent);
            }

        });

        ImageButton local12 = (ImageButton) findViewById(R.id.local12);

        local12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local12.class);
                startActivity(intent);
            }

        });
        ImageButton local13 = (ImageButton) findViewById(R.id.local13);

        local13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local13.class);
                startActivity(intent);
            }

        });

        ImageButton local14 = (ImageButton) findViewById(R.id.local14);

        local14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local14.class);
                startActivity(intent);
            }

        });
        ImageButton local15 = (ImageButton) findViewById(R.id.local15);

        local15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local15.class);
                startActivity(intent);
            }

        });

        ImageButton local16 = (ImageButton) findViewById(R.id.local16);

        local16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local16.class);
                startActivity(intent);
            }

        });
        ImageButton local17 = (ImageButton) findViewById(R.id.local17);

        local17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local17.class);
                startActivity(intent);
            }

        });

        ImageButton local18 = (ImageButton) findViewById(R.id.local18);

        local18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Local18.class);
                startActivity(intent);
            }

        });


    }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()) {
                case android.R.id.home: { // 뒤로가기 버튼 눌렀을 때
                    finish();
                    return true;

                }

                case R.id.toolbar_menu:

                    if (isPageOpen) {
                        page.startAnimation(translateRight);            //오른쪽으로 보이기
                    } else {
                        page.setVisibility(View.VISIBLE);
                        page.startAnimation(translateLeft);

                    }


                    break;


            }

            return super.onOptionsItemSelected(item);


        }

        class SlidingAnimationListener implements Animation.AnimationListener {

            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (isPageOpen) {
                    page.setVisibility(View.INVISIBLE);
                    isPageOpen = false;
                } else {
                    isPageOpen = true;
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.toolbar, menu);

            return true;

        }
    }

