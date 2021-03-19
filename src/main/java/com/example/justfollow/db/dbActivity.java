package com.example.justfollow.db;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.justfollow.R;

import androidx.appcompat.app.AppCompatActivity;

public class dbActivity extends AppCompatActivity {
    private EditText mTitleEditText;
    private EditText mContentsEditText;

    private long mMemoId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        mTitleEditText = (EditText) findViewById(R.id.title_edit);
        mContentsEditText = (EditText) findViewById(R.id.contents_edit);

        Intent intent = getIntent();
        if (intent != null) {
            mMemoId = intent.getLongExtra("id", -1);
            String title = intent.getStringExtra("title");
            String contents = intent.getStringExtra("contents");
            mTitleEditText.setText(title);
            mContentsEditText.setText(contents);
        }
    }

    @Override
    public void onBackPressed() {
        // DB 에 저장하는 처리
        String title = mTitleEditText.getText().toString();
        String contents = mContentsEditText.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put(dbContract.MemoEntry.COLUMN_NAME_TITLE, title);
        contentValues.put(dbContract.MemoEntry.COLUMN_NAME_CONTENTS, contents);

//        SQLiteDatabase db = MemoDbHelper.getInstance(this).getWritableDatabase();
        if (mMemoId == -1) {
//            // DB 에 저장하는 처리
//            long newRowId = db.insert(MemoContract.MemoEntry.TABLE_NAME, null, contentValues);
//
//            if (newRowId == -1) {
            Uri uri = getContentResolver().insert(dbprovider.CONTENT_URI, contentValues);
            if (uri == null) {

                Toast.makeText(this, "Save Error",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            // 기존 메모 내용을 업데이트 처리
//            int count = db.update(MemoContract.MemoEntry.TABLE_NAME, contentValues,
//                    MemoContract.MemoEntry._ID + " = " + mMemoId, null);
            int count = getContentResolver().update(dbprovider.CONTENT_URI,
                    contentValues, dbContract.MemoEntry._ID + " = " + mMemoId, null);


            if (count == 0) {
                Toast.makeText(this, "Correct Error",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Correct Faviotes", Toast.LENGTH_SHORT).show();
            }
        }


        // 뒤로 가기의 원래의 동작이 실행 됨
        super.onBackPressed();
    }

}
