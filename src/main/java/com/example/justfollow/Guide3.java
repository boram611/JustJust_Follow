package com.example.justfollow;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DownloadManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telecom.Call;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Guide3 extends AppCompatActivity {


    Animation translateLeft;
    Animation translateRight;
    boolean isPageOpen = false;
    LinearLayout page;
    navmenuitemAdapter adapter1;
    TextView tv,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15,tv16,tv17,tv18,tv19,tv20,tv21,tv22,tv23,tv24;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);


        page = (LinearLayout) findViewById(R.id.page);


        translateLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRight = AnimationUtils.loadAnimation(this, R.anim.tranlate_right);


        Guide3.SlidingAnimationListener listener = new Guide3.SlidingAnimationListener();
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


        //시차
        tv = findViewById(R.id.t0);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String time = format.format(calendar.getTime());
        tv.setText(time);


        tv2 = findViewById(R.id.t1);

        Calendar calendar2 = Calendar.getInstance();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format2.setTimeZone(TimeZone.getTimeZone("GMT +01:00"));
        String time2 =  format2.format(calendar2.getTime());
        tv2.setText(time2);

        tv3 = findViewById(R.id.t2);

        Calendar calendar3 = Calendar.getInstance();
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format3.setTimeZone(TimeZone.getTimeZone("GMT+02:00"));
        String time3 = format3.format(calendar3.getTime());
        tv3.setText(time3);

        tv4 = findViewById(R.id.t3);

        Calendar calendar4 = Calendar.getInstance();
        SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format4.setTimeZone(TimeZone.getTimeZone("GMT+03:00"));
        String time4 = format4.format(calendar4.getTime());
        tv4.setText(time4);

        tv5 = findViewById(R.id.t4);

        Calendar calendar5 = Calendar.getInstance();
        SimpleDateFormat format5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format5.setTimeZone(TimeZone.getTimeZone("GMT+04:00"));
        String time5 = format5.format(calendar5.getTime());
        tv5.setText(time5);

        tv6 = findViewById(R.id.t5);

        Calendar calendar6 = Calendar.getInstance();
        SimpleDateFormat format6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format6.setTimeZone(TimeZone.getTimeZone("GMT+05:00"));
        String time6 = format6.format(calendar6.getTime());
        tv6.setText(time6);

        tv7 = findViewById(R.id.t6);

        Calendar calendar7 = Calendar.getInstance();
        SimpleDateFormat format7 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format7.setTimeZone(TimeZone.getTimeZone("GMT+06:00"));
        String time7 = format7.format(calendar7.getTime());
        tv7.setText(time7);

        tv8 = findViewById(R.id.t7);

        Calendar calendar8 = Calendar.getInstance();
        SimpleDateFormat format8 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format8.setTimeZone(TimeZone.getTimeZone("GMT+07:00"));
        String time8 = format8.format(calendar8.getTime());
        tv8.setText(time8);

        tv9 = findViewById(R.id.t8);

        Calendar calendar9 = Calendar.getInstance();
        SimpleDateFormat format9 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format9.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        String time9 = format9.format(calendar9.getTime());
        tv9.setText(time9);

        tv10 = findViewById(R.id.t9);

        Calendar calendar10 = Calendar.getInstance();
        SimpleDateFormat format10 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format10.setTimeZone(TimeZone.getTimeZone("GMT+09:00"));
        String time10 = format10.format(calendar10.getTime());
        tv10.setText(time10);

        tv11 = findViewById(R.id.t10);

        Calendar calendar11 = Calendar.getInstance();
        SimpleDateFormat format11 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format11.setTimeZone(TimeZone.getTimeZone("GMT+10:00"));
        String time11 = format11.format(calendar10.getTime());
        tv11.setText(time11);

        tv12 = findViewById(R.id.t11);

        Calendar calendar12 = Calendar.getInstance();
        SimpleDateFormat format12 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format12.setTimeZone(TimeZone.getTimeZone("GMT+11:00"));
        String time12 = format12.format(calendar12.getTime());
        tv12.setText(time12);

        tv13 = findViewById(R.id.t12);

        Calendar calendar13 = Calendar.getInstance();
        SimpleDateFormat format13 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format13.setTimeZone(TimeZone.getTimeZone("GMT+12:00"));
        String time13 = format13.format(calendar13.getTime());
        tv13.setText(time13);


        tv14 = findViewById(R.id.t13);

        Calendar calendar14 = Calendar.getInstance();
        SimpleDateFormat format14 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format14.setTimeZone(TimeZone.getTimeZone("GMT-11:00"));
        String time14 = format14.format(calendar14.getTime());
        tv14.setText(time14);

        tv15 = findViewById(R.id.t14);

        Calendar calendar15 = Calendar.getInstance();
        SimpleDateFormat format15 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format15.setTimeZone(TimeZone.getTimeZone("GMT-10:00"));
        String time15 = format15.format(calendar15.getTime());
        tv15.setText(time15);

        tv16 = findViewById(R.id.t15);

        Calendar calendar16 = Calendar.getInstance();
        SimpleDateFormat format16 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format16.setTimeZone(TimeZone.getTimeZone("GMT-09:00"));
        String time16 = format16.format(calendar16.getTime());
        tv16.setText(time16);

        tv17 = findViewById(R.id.t16);

        Calendar calendar17 = Calendar.getInstance();
        SimpleDateFormat format17 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format17.setTimeZone(TimeZone.getTimeZone("GMT-08:00"));
        String time17 = format17.format(calendar17.getTime());
        tv17.setText(time17);

        tv18 = findViewById(R.id.t17);

        Calendar calendar18 = Calendar.getInstance();
        SimpleDateFormat format18 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format18.setTimeZone(TimeZone.getTimeZone("GMT-07:00"));
        String time18 = format18.format(calendar18.getTime());
        tv18.setText(time18);

        tv19 = findViewById(R.id.t18);

        Calendar calendar19 = Calendar.getInstance();
        SimpleDateFormat format19 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format19.setTimeZone(TimeZone.getTimeZone("GMT-06:00"));
        String time19 = format19.format(calendar19.getTime());
        tv19.setText(time19);

        tv20 = findViewById(R.id.t19);

        Calendar calendar20 = Calendar.getInstance();
        SimpleDateFormat format20 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format20.setTimeZone(TimeZone.getTimeZone("GMT-05:00"));
        String time20 = format20.format(calendar20.getTime());
        tv20.setText(time20);

        tv21 = findViewById(R.id.t20);

        Calendar calendar21 = Calendar.getInstance();
        SimpleDateFormat format21 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format21.setTimeZone(TimeZone.getTimeZone("GMT-04:00"));
        String time21 = format21.format(calendar21.getTime());
        tv21.setText(time21);

        tv22 = findViewById(R.id.t21);

        Calendar calendar22 = Calendar.getInstance();
        SimpleDateFormat format22 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format22.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        String time22 = format22.format(calendar22.getTime());
        tv22.setText(time22);

        tv23 = findViewById(R.id.t22);

        Calendar calendar23 = Calendar.getInstance();
        SimpleDateFormat format23 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format23.setTimeZone(TimeZone.getTimeZone("GMT-02:00"));
        String time23 = format23.format(calendar23.getTime());
        tv23.setText(time23);

        tv24 = findViewById(R.id.t23);

        Calendar calendar24 = Calendar.getInstance();
        SimpleDateFormat format24 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format24.setTimeZone(TimeZone.getTimeZone("GMT-01:00"));
        String time24 = format24.format(calendar24.getTime());
        tv24.setText(time24);


    }









    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);

        return true;
    }

}