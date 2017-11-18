package com.example.msi.newproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    public static final String PREFERENCES_1 = "info";
    public static final String INTRESULT = "int";
    public static final String PREFERENCES_ATTR1 = "name";
    public static final String PREFERENCES_ATTR2 = "t1";
    public static final String PREFERENCES_ATTR3 = "t2";
    public static final String PREFERENCES_IMG = "img";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        mDbHelper = new DBHelper(this);

        TextView main = (TextView) findViewById(R.id.bhc);
        ImageView img = (ImageView) findViewById(R.id.imageView2);
        TextView t1 = (TextView) findViewById(R.id.location);
        TextView t2 = (TextView) findViewById(R.id.phonenumber);

        Cursor cursor = mDbHelper.getAllUsersByMethod();
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
                R.layout.activity_restaurant_detail, cursor, new String[]{

                UserContract.Users.KEY_NAME,
                UserContract.Users.KEY_ADDRESS,
                UserContract.Users.KEY_PHONE,
                UserContract.Users.KEY_IMGNAME},
                new int[]{R.id.bhc, R.id.location, R.id.phonenumber, R.id.imageView2}, 0);
//        main.setText(((Cursor)adapter.getItem(i)).getString(0));
//        img.setImageResource(((Cursor)adapter.getItem(i)).getString(1));

        Intent intent = getIntent();
        final int num = intent.getIntExtra("int", 0);

        String mPhotoFileName = ((Cursor) adapter.getItem(num - 1)).getString(4);

        // mPhotoFileName = "IMG"+currentDateFormat()+".jpg";
        File mPhotoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName);
        img.setImageURI(Uri.fromFile(mPhotoFile));
        main.setText(((Cursor) adapter.getItem(num - 1)).getString(1));
        t1.setText(((Cursor) adapter.getItem(num - 1)).getString(2));
        t2.setText(((Cursor) adapter.getItem(num - 1)).getString(3));

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
                startActivity(new Intent(this, MenuDetailRegister.class));

            default:
                return super.onOptionsItemSelected(item);
        }


    }
}