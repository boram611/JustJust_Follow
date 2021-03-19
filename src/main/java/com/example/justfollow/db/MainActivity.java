package com.example.justfollow.db;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justfollow.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

public class MainActivity extends  AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int REQUEST_CODE_INSERT = 1000;
    private MemoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment2);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        dbActivity.class));

            }
        });

        ListView listView = (ListView) findViewById(R.id.memo_list);

//        MemoDbHelper dbHelper = MemoDbHelper.getInstance(this);
//        Cursor cursor = dbHelper.getReadableDatabase()
//                .query(MemoContract.MemoEntry.TABLE_NAME,
//                        null, null, null, null, null, null);

        mAdapter = new MemoAdapter(this, null);
        listView.setAdapter((ListAdapter) mAdapter);

        // 리스트 클릭시 메모 내용 표시
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(MainActivity.this, dbActivity.class);
                // 클릭한 시점의 아이템을 얻음
                Cursor cursor = (Cursor) mAdapter.getItem(position);
                // 커서에서 제목과 내용을 얻음
                String title = cursor.getString(cursor.getColumnIndexOrThrow
                        (dbContract.MemoEntry.COLUMN_NAME_TITLE));
                String contents = cursor.getString(cursor.getColumnIndexOrThrow
                        (dbContract.MemoEntry.COLUMN_NAME_CONTENTS));
                // 인텐트에 id와 함께 저장
                intent.putExtra("id", id);
                intent.putExtra("title", title);
                intent.putExtra("contents", contents);
                // MemoActivity 를 시작
                startActivity(intent);
            }
        });

        // 아이템 롱 클릭 이벤트 정의
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
                                           long id) {
                final long deleteId = id;
                // 삭제할 것인지 다이얼로그 표시
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete Favirotes");
                builder.setMessage("Delete Favirotes?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db =
                                dbhelper.getInstance(MainActivity.this).getWritableDatabase();
//                        int deletedCount = db.delete(MemoContract.MemoEntry.TABLE_NAME,
//                                MemoContract.MemoEntry._ID + " = " + deleteId, null);
                        int deletedCount = getContentResolver().delete(dbprovider.CONTENT_URI,
                                dbContract.MemoEntry._ID + " = " + deleteId, null);

                        if (deletedCount == 0) {
                            Toast.makeText(MainActivity.this, "Delete Favirotes Error",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Delete Favirotes",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
                return true;
            }
        });

        getSupportLoaderManager().initLoader(0, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, dbprovider.CONTENT_URI, null, null, null,
                dbContract.MemoEntry._ID + " DESC");

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    private static class MemoAdapter extends CursorAdapter {
        public MemoAdapter(Context context, Cursor c) {
            super(context, c, false);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView titleText = (TextView) view.findViewById(android.R.id.text1);
            titleText.setText(cursor.getString(cursor.getColumnIndexOrThrow
                    (dbContract.MemoEntry.COLUMN_NAME_TITLE)));
        }
    }

}
