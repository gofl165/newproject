package com.example.msi.newproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RestaurantDetail extends AppCompatActivity implements FrgRestaurantDetail.OnTitleSelectedListener{
    static MyAdapter adapter;
    SimpleCursorAdapter adapter2;

    private DBHelper mDbHelper;

    int num;
    int superint;
    ImageView img2;
    TextView m1;
    TextView m2;
    String txt1,txt2,txt3;
    MyAdapter adapter3;
    File mPhotoFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        mDbHelper = new DBHelper(this);

        TextView main = (TextView) findViewById(R.id.bhc);
        ImageView img = (ImageView) findViewById(R.id.imageView2);
        TextView t1 = (TextView) findViewById(R.id.location);
        TextView t2 = (TextView) findViewById(R.id.phonenumber);

       img2=(ImageView)findViewById(R.id.imageView) ;
        m1= (TextView) findViewById(R.id.text1);
        m2 = (TextView) findViewById(R.id.text2);
//        ********************************어뎁터*************************

        ArrayList<MyItem> data = new ArrayList<MyItem>();



            Cursor cursor3 = mDbHelper.getAllUsersByMethod2();
            while(cursor3.moveToNext()){

                txt3=cursor3.getString(4);

                data.add(new MyItem(txt3, cursor3.getString(1), cursor3.getString(2)));
            }
            cursor3.close();

        //어댑터 생성
        adapter3 = new MyAdapter(this, R.layout.item, data);

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
        adapter2 = new SimpleCursorAdapter(getApplicationContext(),
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

//        File mPhotoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName);
        mPhotoFile =getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File f=new File(mPhotoFile,mPhotoFileName);
        img.setImageURI(Uri.fromFile(f));

        main.setText(((Cursor) adapter.getItem(num - 1)).getString(1));
        t1.setText(((Cursor) adapter.getItem(num - 1)).getString(2));
        t2.setText(((Cursor) adapter.getItem(num - 1)).getString(3));
//        *********************************************************************


        ListView lv = (ListView)findViewById(R.id.listView);

//              lv.setAdapter(adapter2);
                lv.setAdapter(adapter3);
//아래메뉴 누르면 MenuDetail으로 Activity convert
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter adapter = adapterView.getAdapter();
                if (getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_LANDSCAPE) {
                    FrgMenuDetail detailsFragment = new FrgMenuDetail();
                    detailsFragment.setSelection(i);
                    superint=i;
                    //http://jizard.tistory.com/66     참조(Bundle쓰는법)
                    Bundle bundle=new Bundle(3);
                    bundle.putString("menu2",((Cursor)adapter2.getItem(i)).getString(1));
                    bundle.putString("price2",((Cursor)adapter2.getItem(i)).getString(2));
                    bundle.putString("detail2",((Cursor)adapter2.getItem(i)).getString(3));
                    bundle.putString("imgname",((Cursor)adapter2.getItem(i)).getString(4));
                    detailsFragment.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction().replace(R.id.details, detailsFragment).commit();
                } else {
                    Intent intent = new Intent(getApplicationContext(), MenuDetail.class);
                    intent.putExtra("menu",((Cursor)adapter2.getItem(i)).getString(1));
                    intent.putExtra("img", ((Cursor)adapter2.getItem(i)).getString(4));
                    intent.putExtra("price", ((Cursor)adapter2.getItem(i)).getString(2));
                    intent.putExtra("detail",((Cursor)adapter2.getItem(i)).getString(3));

                    startActivity(intent);
                }


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




//*************************oncreate끝*********************
    }
    //***********************Fragment********************
    public void onTitleSelected(int i) {

    }

//    *************************************

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
////    **********************************************보류******************************
    //혹시몰라 남겨놓음
//    //https://www.androidpub.com/1583212 랑 customAdapterView강의자료참고  (사진안나와서 Custom SimpleCursorAdapter찾아봄)
//    class CustomAdapter extends SimpleCursorAdapter {
//        private Cursor cur;
//        private Context mContext;
//        private int mResource;
//        public CustomAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
//            super(context,layout, c, from, to, flags);
//            mContext=context;
//            mResource = layout;
//        }
//
//
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            if (convertView == null) {
//                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = inflater.inflate(mResource, parent,false);
//            }
//
//
//
////            ImageView img2=(ImageView)findViewById(R.id.imageView) ;
////            String mPhotoFileName2 = ((Cursor) adapter.getItem(position)).getString(4);
////            File mPhotoFile2 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName2);
////            img2.setImageURI(Uri.fromFile(mPhotoFile2));
////            ***********
////            m1.setText(txt1);
////            m2.setText(txt2);
////            m3.setText(getItem(position).toString());
//
//
//            return convertView;
//        }
//    }
////************************************************************************





    }


