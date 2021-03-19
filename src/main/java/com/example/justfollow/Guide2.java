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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Guide2 extends AppCompatActivity {

    Animation translateLeft;
    Animation translateRight;
    boolean isPageOpen = false;
    LinearLayout page;
    navmenuitemAdapter adapter1;



    ListView listView1;
    String mTitle1[] = {"Korea Tour Card \n" +
            "& Mobile Korea Tour Card ", "Discover Seoul Pass", "MPass", "Tmoney"};
    String mDescription1[] = {"Facebook Description", "Whats app Description", "Twitter Description", "Instagram Description"};
    int images1[] = {R.drawable.card1, R.drawable.card2, R.drawable.card3, R.drawable.card4};
    // so our images and other things are set in array

    // now paste some images in drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Korean transportation card");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        listView1 = findViewById(R.id.listView1);
        // now create an adapter class

        MyAdapter adapter = new MyAdapter(this, mTitle1, mDescription1, images1);
        listView1.setAdapter(adapter);
        // there is my mistake...
        // now again check this..



        // now set item click on list view
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    Intent intent = new Intent(getApplicationContext(), Card1.class);
                    startActivity(intent);
                }
                if (position ==  1) {
                    Intent intent = new Intent(getApplicationContext(), Card2.class);
                    startActivity(intent);
                }
                if (position ==  2) {
                    Intent intent = new Intent(getApplicationContext(), Card3.class);
                    startActivity(intent);
                }
                if (position ==  3) {
                    Intent intent = new Intent(getApplicationContext(), Card4.class);
                    startActivity(intent);
                }
            }
        });
        // so item click is done now check list view

        page = (LinearLayout) findViewById(R.id.page);


        translateLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRight = AnimationUtils.loadAnimation(this, R.anim.tranlate_right);


        Guide2.SlidingAnimationListener listener = new Guide2.SlidingAnimationListener();
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

    class MyAdapter extends ArrayAdapter<String> {

        Context context1;
        String rTitle1[];
        String rDescription1[];
        int rImgs1[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row1, R.id.textView1_1, title);
            this.context1 = c;
            this.rTitle1 = title;
            this.rDescription1 = description;
            this.rImgs1 = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row1, parent, false);
            ImageView images = row.findViewById(R.id.image1);
            TextView myTitle = row.findViewById(R.id.textView1_1);

            // now set our resources on views
            images.setImageResource(rImgs1[position]);
            myTitle.setText(rTitle1[position]);




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


