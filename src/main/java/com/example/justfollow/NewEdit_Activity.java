package com.example.justfollow;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class NewEdit_Activity extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    Button btnSave,btnDate;
    EditText date,title,content;
    RadioGroup division;
    RadioButton basic;
    ImageButton btnCamera,btnAlbum;
    private Spinner weather;

    String fileUri;

    private File tempFile;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_edit_);



        btnCamera = (ImageButton)findViewById(R.id.btnCamera);
        btnAlbum = (ImageButton)findViewById(R.id.btnAlbum);

        btnSave = (Button)findViewById(R.id.btnSave);
        date =(EditText)findViewById(R.id.date);
        title =(EditText)findViewById(R.id.title);
        content =(EditText)findViewById(R.id.content);
        division =(RadioGroup) findViewById(R.id.division);
        basic = (RadioButton)findViewById(R.id.basic);


        weather =(Spinner) findViewById(R.id.weather);
        final String[] selectWeather = new String[1];
        final ArrayList<String> list = new ArrayList<>();
        list.add("Sunny");
        list.add("Cloud");
        list.add("Rain");
        list.add("Snow");

        ArrayAdapter spinnerAdapter;
        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list);
        weather.setAdapter(spinnerAdapter);

        weather.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    selectWeather[0] ="Sunny";
                }else if (position==1){
                    selectWeather[0] ="Cloudy";
                }else if (position==2){
                    selectWeather[0] ="Rain";
                }else if (position==3){
                    selectWeather[0] ="Snow";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnDate=(Button)findViewById(R.id.btnDate);

        basic.setChecked(true);

        // ??????????????? msec ?????? ?????????.
        long now = System.currentTimeMillis();
        // ??????????????? date ????????? ????????????.
        Date nowDate = new Date(now);
        // ????????? ????????? ????????? ????????? ( yyyy/MM/dd ?????? ????????? ?????? ?????? )
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd");
        // nowDate ????????? ?????? ????????????.
        final String formatDate = sdfNow.format(nowDate);

        date.setText(formatDate);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d_division = null;
                int id= division.getCheckedRadioButtonId();
                String d_date=date.getText().toString();
                String d_weather=selectWeather[0];
                String d_title=title.getText().toString();
                String d_content=content.getText().toString();
                if (id!=-1) {
                    RadioButton rb = (RadioButton) findViewById(id);
                    d_division = rb.getText().toString();
                }


                if(d_date==null||d_date.equals("")){
                    Toast.makeText(getApplicationContext(),"Select Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(d_weather==null||d_weather.equals("")){
                    Toast.makeText(getApplicationContext(),"Select Weather", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(d_title==null||d_title.equals("")){
                    Toast.makeText(getApplicationContext(),"Select Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(d_content==null||d_content.equals("")){
                    Toast.makeText(getApplicationContext(),"Select Content", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(fileUri==null||fileUri.equals("")){
                    fileUri="";
                }

                helper.insert(d_title,d_date,d_weather,d_division,d_content,fileUri);
                finish();
            }
        });


        //??????????????????
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DatePick.class);
                //formatDAte??? ???????????????
                intent.putExtra("first",formatDate);

                startActivityForResult(intent,0);
            }
        });



        btnAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Album();
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera();
            }
        });

    }

    //??????????????????????????? ????????? ???????????? ??????????????? ?????? ??? ???????????? ??????
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        //?????????
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, "Cancel.", Toast.LENGTH_SHORT).show();

            if(tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        tempFile = null;
                    }
                }
            }

            return;
        }

        if(requestCode==0){
            int year = (Integer) data.getExtras().getInt("year");
            int month = (Integer) data.getExtras().getInt("month");
            int day = (Integer) data.getExtras().getInt("date");
            date.setText(year+"-"+month+"-"+day);
        }


        if (requestCode == PICK_FROM_ALBUM) {

            Uri photoUri = data.getData();

            Cursor cursor = null;

            try {

                String[] proj = { MediaStore.Images.Media.DATA };

                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));


            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();

        } else if (requestCode == PICK_FROM_CAMERA&& resultCode == Activity.RESULT_OK && data.hasExtra("data")) {

            setImage(data);

        }
    }

    private void Album(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    private void camera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PICK_FROM_CAMERA);

    }


    private void setImage(Intent data){
        ImageView selectImg = findViewById(R.id.selectImg);

        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        if (bitmap != null) {
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            File root = Environment.getExternalStorageDirectory();
            File file = new File(root.getAbsolutePath()+"/DCIM/Camera/" + time + ".jpg");

            fileUri=file.getPath();

            try
            {
                file.createNewFile();
                FileOutputStream ostream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                ostream.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            selectImg.setImageBitmap(bitmap);
        }

    }

    private void setImage(){

        ImageView selectImg = findViewById(R.id.selectImg);

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap img = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);

        fileUri = tempFile.getAbsolutePath();
        selectImg.setImageBitmap(img);

    }
}
