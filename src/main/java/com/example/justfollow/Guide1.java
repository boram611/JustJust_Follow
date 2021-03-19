package com.example.justfollow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Guide1 extends AppCompatActivity {


    ListView listView2;
    String mTitle2[] = {"Bus", "Subway", "Taxi"};
    String mDescription2[] = {"Facebook Description", "Whats app Description", "Twitter Description", "Instagram Description"};
    int images2[] = {R.drawable.bus, R.drawable.subway, R.drawable.taxi};
    // so our images and other things are set in array

    // now paste some images in drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Korean transportation card");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        listView2 = findViewById(R.id.listView2);
        // now create an adapter class

        MyAdapter adapter = new MyAdapter(this, mTitle2, mDescription2, images2);
        listView2.setAdapter(adapter);
        // there is my mistake...
        // now again check this..


        // now set item click on list view
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(), Bus.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(getApplicationContext(), Subway.class);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(getApplicationContext(), Taxi.class);
                    startActivity(intent);
                }
            }
        });
        // so item click is done now check list view
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context2;
        String rTitle2[];
        String rDescription2[];
        int rImgs2[];

        MyAdapter(Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row2, R.id.textView2, title);
            this.context2 = c;
            this.rTitle2 = title;
            this.rDescription2 = description;
            this.rImgs2 = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row2, parent, false);
            ImageView images = row.findViewById(R.id.image2);
            TextView myTitle = row.findViewById(R.id.textView2);

            // now set our resources on views
            images.setImageResource(rImgs2[position]);
            myTitle.setText(rTitle2[position]);


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


        }


        return super.onOptionsItemSelected(item);
    }
}


