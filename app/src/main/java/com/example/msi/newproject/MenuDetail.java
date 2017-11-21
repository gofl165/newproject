package com.example.msi.newproject;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuDetail extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView menu = (TextView)findViewById(R.id.text1);
        TextView price = (TextView)findViewById(R.id.text2);
        ImageView img = (ImageView)findViewById(R.id.imageView);

        Intent intent = getIntent(); // 보내온 Intent를 얻는다

        FrgMenuDetail details = new FrgMenuDetail();
        details.setSelection(getIntent().getIntExtra("index",-1));
        details.add(intent.getStringExtra("menu"),intent.getStringExtra("price"),
                intent.getStringExtra("img"),intent.getStringExtra("detail"));
        getSupportFragmentManager().beginTransaction().replace(R.id.details, details).commit();
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }


    }




}
