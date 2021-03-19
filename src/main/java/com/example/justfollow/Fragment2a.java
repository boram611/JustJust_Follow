package com.example.justfollow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Fragment2a extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    LinearLayout page;
    Animation translateLeft;
    Animation translateRight;
    boolean isPageOpen = false;
    navmenuitemAdapter adapter1;


    ListView listView;
    String mTitle[] = {"How to use public transportation", "Transportation Card", "Time Difference", "Currency & Exchange Info", "Electricity"};
    String mDescription[] = {"Facebook Description", "Whats app Description", "Twitter Description", "Instagram Description", "Youtube Description"};
    int images[] = {R.drawable.tip1, R.drawable.tip2, R.drawable.tip3, R.drawable.tip4, R.drawable.tip5};
    // so our images and other things are set in array

    // now paste some images in drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Favirote");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        listView = findViewById(R.id.listView2);
        // now create an adapter class

        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);
        // there is my mistake...
        // now again check this..



        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    Intent intent = new Intent(getApplicationContext(), Guide1.class);
                    startActivity(intent);
                }
                if (position ==  1) {
                    Intent intent = new Intent(getApplicationContext(), Guide2.class);
                    startActivity(intent);
                }
                if (position ==  2) {
                    Intent intent = new Intent(getApplicationContext(), Guide3.class);
                    startActivity(intent);
                }
                if (position ==  3) {
                    Intent intent = new Intent(getApplicationContext(), Guide4.class);
                    startActivity(intent);
                }
                if (position ==  4) {
                    Intent intent = new Intent(getApplicationContext(), Guide5.class);
                    startActivity(intent);
                }
            }
        });
        // so item click is done now check list view

        page = (LinearLayout) findViewById(R.id.page);


        translateLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRight = AnimationUtils.loadAnimation(this, R.anim.tranlate_right);


        Fragment2a.SlidingAnimationListener listener = new Fragment2a.SlidingAnimationListener();
        translateLeft.setAnimationListener(listener);
        translateRight.setAnimationListener(listener);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment1).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab1: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment1).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.tab2: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment2).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.tab3: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment3).commitAllowingStateLoss();
                        return true;
                    }
                    default:
                        return false;
                }
            }
        });

        adapter1 = new navmenuitemAdapter();

        listView = (ListView) findViewById(R.id.listview);
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

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter(Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);

            // now set our resources on views
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);


            return row;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { // 뒤로가기 버튼 눌렀을 때
                finish();
                return true;

            }

            case R.id.toolbar_menu:

                if(isPageOpen){
                    page.startAnimation(translateRight);            //오른쪽으로 보이기
                }else {
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



