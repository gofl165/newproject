package com.example.msi.newproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
//    private DBHelper mDbHelper;

    public static final String PREFERENCES_1 = "info";

    public static final String PREFERENCES_ATTR1 = "name";
    public static final String PREFERENCES_ATTR2 = "t1";
    public static final String PREFERENCES_ATTR3 = "t2";
    public static final String PREFERENCES_IMG = "img";
    SharedPreferences setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
//        mDbHelper = new DBHelper(this);
//        ***********************과제1*****************************
//        // 데이터 원본 준비
//        data.add(new MyItem(R.drawable.sweet, "스윗츄~", "17000"));
//        data.add(new MyItem(R.drawable.boom, "붐바스틱", "18000"));
//        data.add(new MyItem(R.drawable.bring, "뿌링클", "17000"));
//        data.add(new MyItem(R.drawable.matco, "맛초킹", "17000"));
//
//
//        //어댑터 생성
//        adapter = new MyAdapter(this, R.layout.item, data);
//
//        //어댑터 연결
//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(adapter);
//        //아래메뉴 누르면 MenuDetail으로 Activity convert
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View vClicked,
//                                    int position, long id) {
//
//
//
//
//                Intent intent = new Intent(getApplicationContext(), MenuDetail.class);
//
//
//
//                intent.putExtra("menu", data.get(position).nMenu);
//                intent.putExtra("img", data.get(position).mIcon);
//                intent.putExtra("price", data.get(position).nPrice);
//
//
//                startActivity(intent);
//
//            }
//        });
//        //통화버튼누르면 Dail 연결
//        ImageButton btn = (ImageButton) findViewById(R.id.dial);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent implicit_intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-908-8005"));
//                startActivity(implicit_intent);
//            }
//
//
//
//        });
//        ******************************************************************
        TextView main=(TextView)findViewById(R.id.bhc);
        ImageView img=(ImageView)findViewById(R.id.imageView2);
        TextView t1=(TextView)findViewById(R.id.location);
        TextView t2=(TextView)findViewById(R.id.phonenumber);
        TextView t3=(TextView)findViewById(R.id.whattime);




//        **************************data받기*******************
        setting = getSharedPreferences(PREFERENCES_1, MODE_PRIVATE);
          String nameText = setting.getString(PREFERENCES_ATTR1, "");
        String nameText2 = setting.getString(PREFERENCES_ATTR2, "");
        String nameText3 = setting.getString(PREFERENCES_ATTR3, "");
        String image=setting.getString(PREFERENCES_IMG,"");
        File mPhotoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), image);

        main.setText(nameText);
        t1.setText(nameText2);
        t2.setText(nameText3);
        img.setImageURI(Uri.fromFile(mPhotoFile));






//        ******************************************************

//        Cursor cursor = mDbHelper.getAllUsersByMethod();
//        main.setText(((Cursor)adapter.getItem(i)).getString(0));
//        img.setImageResource(((Cursor)adapter.getItem(i)).getString(1));
//        File mPhotoFile;
//        String mPhotoFileName;
//
//        mPhotoFileName = "IMG"+currentDateFormat()+".jpg";
//        mPhotoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName);
//        img.setImageURI(Uri.fromFile(mPhotoFile));
//        t1.setText(((Cursor)adapter.getItem(i)).getString(2));
//        t2.setText(((Cursor)adapter.getItem(i)).getString(2));
//        t3.setText(((Cursor)adapter.getItem(i)).getString(2));


        //통화버튼누르면 Dail 연결
        ImageButton btn = (ImageButton) findViewById(R.id.dial);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText3 = setting.getString(PREFERENCES_ATTR3, "");
                Intent implicit_intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+nameText3));
                startActivity(implicit_intent);
            }



        });

        }

//    private String currentDateFormat(){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
//        String  currentTimeStamp = dateFormat.format(new Date());
//        return currentTimeStamp;
//    }
//
//
//    private void viewAllToListView() {
//
//        Cursor cursor = mDbHelper.getAllUsersByMethod();
//
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
//                R.layout.item, cursor, new String[]{
//                UserContract.Users.KEY_NAME,
//                UserContract.Users.KEY_ADDRESS,
//                UserContract.Users.KEY_PHONE},
//                new int[]{ R.id.name,R.id.address, R.id.phone}, 0);
//
//        ListView lv = (ListView)findViewById(R.id.listView);
//        lv.setAdapter(adapter);
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Adapter adapter = adapterView.getAdapter();
//
//                mId.setText(((Cursor)adapter.getItem(i)).getString(0));
//                mName.setText(((Cursor)adapter.getItem(i)).getString(1));
//                mPhone.setText(((Cursor)adapter.getItem(i)).getString(2));
//            }
//        });
//        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//    }

}