package com.example.justfollow;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class navmenuitemAdapter extends BaseAdapter {

    private ImageView iconImageView;
    private TextView contentTextView;

    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    public navmenuitemAdapter() {

    }


    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.nav_menu_item, parent, false);
        }
        iconImageView = (ImageView) convertView.findViewById(R.id.icon);
        contentTextView = (TextView) convertView.findViewById(R.id.content);

        final ListViewItem listViewItem = listViewItemList.get(position);

        iconImageView.setImageResource(listViewItem.getIcon());
        contentTextView.setText(listViewItem.getContent());

        LinearLayout shapeLayout = (LinearLayout) convertView.findViewById(R.id.shapeLayout);
        shapeLayout.setOnClickListener(new View.OnClickListener() {
            public  void onClick(View v) {
                if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(), Directions.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    getApplicationContext().startActivity(intent);
                }

              if (position == 1) {
                Intent intent = new Intent(getApplicationContext(), Translator.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                getApplicationContext().startActivity(intent);
            }

        if (position == 2) {
            Intent intent = new Intent(getApplicationContext(), Exchange.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            getApplicationContext().startActivity(intent);
        }

      if (position == 3) {
        Intent intent = new Intent(getApplicationContext(), Helpful.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        getApplicationContext().startActivity(intent);
    }

  if (position == 4) {
          Intent intent = new Intent(getApplicationContext(), Local2_1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        getApplicationContext().startActivity(intent);
        }

        if (position == 5) {
        Intent intent = new Intent(getApplicationContext(), FAQ.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        getApplicationContext().startActivity(intent);
        }

                if (position == 6) {
                    Intent intent = new Intent(getApplicationContext(), Setting.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    getApplicationContext().startActivity(intent);
                }
        }
        });




        return convertView;
    }

    public ArrayList<ListViewItem> getListViewItemList() {
        return listViewItemList;
    }

    public void setListViewItemList(ArrayList<ListViewItem> listViewItemList) {
        this.listViewItemList = listViewItemList;
    }

    public void addItem(int icon, String content) {
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setContent(content);

        listViewItemList.add(item);
    }
}


