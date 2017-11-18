package com.example.msi.newproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RestaurantDetail extends AppCompatActivity {
    static MyAdapter adapter;
    //    ArrayList<MyItem> data = new ArrayList<MyItem>();
    private DBHelper mDbHelper;

    int num;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        mDbHelper = new DBHelper(this);

        TextView main = (TextView) findViewById(R.id.bhc);
        ImageView img = (ImageView) findViewById(R.id.imageView2);
        TextView t1 = (TextView) findViewById(R.id.location);
        TextView t2 = (TextView) findViewById(R.id.phonenumber);

        ImageView img2=(ImageView)findViewById(R.id.imageView) ;
        TextView m1= (TextView) findViewById(R.id.text1);
        TextView m2 = (TextView) findViewById(R.id.text2);


//***********************************맛집등록*********************************
        Cursor cursor = mDbHelper.getAllUsersByMethod();
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
                R.layout.activity_restaurant_detail, cursor, new String[]{

                UserContract.Users.KEY_NAME,
                UserContract.Users.KEY_ADDRESS,
                UserContract.Users.KEY_PHONE,
                UserContract.Users.KEY_IMGNAME},
                new int[]{R.id.bhc, R.id.location, R.id.phonenumber, R.id.imageView2}, 0);

//        *******************************************************************
//        *******************************메뉴등록******************************
        Cursor cursor2 = mDbHelper.getAllUsersByMethod2();
        final SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(getApplicationContext(),
                R.layout.item, cursor2, new String[]{

                UserContract.Users.KEY_NAME,
                UserContract.Users.KEY_PRICE,
                UserContract.Users.KEY_DETAIL,
                UserContract.Users.KEY_IMGNAME
                },
                new int[]{R.id.text1, R.id.text2,0,R.id.imageView}, 0);


//        ******************************************************************
        Intent intent = getIntent();
        num = intent.getIntExtra("int", 0);

        final int num2= intent.getIntExtra("int2",0);
        String mPhotoFileName = ((Cursor) adapter.getItem(num - 1)).getString(4);

        File mPhotoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName);
        img.setImageURI(Uri.fromFile(mPhotoFile));
        main.setText(((Cursor) adapter.getItem(num - 1)).getString(1));
        t1.setText(((Cursor) adapter.getItem(num - 1)).getString(2));
        t2.setText(((Cursor) adapter.getItem(num - 1)).getString(3));
//        *********************************************************************

//
//        if(num2>0) {
//            for(int i=0;i<(adapter2.getCount());i++) {
//
//                String mPhotoFileName2 = ((Cursor) adapter2.getItem(num2 - 1)).getString(4);
//                File mPhotoFile2 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName2);
//
//
//                img2.setImageURI(Uri.fromFile(mPhotoFile2));
//
//            }
//        }


        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(adapter2);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter adapter = adapterView.getAdapter();
//
//                m1.setText(((Cursor)adapter.getItem(i)).getString(0));
//                m2.setText(((Cursor)adapter.getItem(i)).getString(1));
//                mPhone.setText(((Cursor)adapter.getItem(i)).getString(2));
            }
        });
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


//        ********************************************************************
        final String pnum = ((Cursor) adapter.getItem(num - 1)).getString(3);
        //통화버튼누르면 Dail 연결
        ImageButton btn = (ImageButton) findViewById(R.id.dial);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent implicit_intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + pnum));
                startActivity(implicit_intent);
            }


        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quick_action1:
                Intent intent=new Intent(this, MenuDetailRegister.class);
                intent.putExtra("int",num);
                startActivity(intent);


            default:
                return super.onOptionsItemSelected(item);
        }


    }
}