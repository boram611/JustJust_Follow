package com.example.justfollow;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;

public class Guide5 extends AppCompatActivity {

    Animation translateLeft;
    Animation translateRight;
    boolean isPageOpen = false;
    LinearLayout page;
    navmenuitemAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide5);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);


        page = (LinearLayout) findViewById(R.id.page);


        translateLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRight = AnimationUtils.loadAnimation(this, R.anim.tranlate_right);


        Guide5.SlidingAnimationListener listener = new Guide5.SlidingAnimationListener();
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


    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home: { // ???????????? ?????? ????????? ???
                finish();
                return true;

            }

            case R.id.toolbar_menu:

                if (isPageOpen) {
                    page.startAnimation(translateRight);            //??????????????? ?????????
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);

        return true;
    }
    }

