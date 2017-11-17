package com.example.msi.newproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantDetail extends AppCompatActivity {
    static MyAdapter adapter;
    ArrayList<MyItem> data = new ArrayList<MyItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        // 데이터 원본 준비


        data.add(new MyItem(R.drawable.sweet, "스윗츄~", "17000"));
        data.add(new MyItem(R.drawable.boom, "붐바스틱", "18000"));
        data.add(new MyItem(R.drawable.bring, "뿌링클", "17000"));
        data.add(new MyItem(R.drawable.matco, "맛초킹", "17000"));


        //어댑터 생성
        adapter = new MyAdapter(this, R.layout.item, data);

        //어댑터 연결
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        //아래메뉴 누르면 MenuDetail으로 Activity convert
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View vClicked,
                                    int position, long id) {




                Intent intent = new Intent(getApplicationContext(), MenuDetail.class);



                intent.putExtra("menu", data.get(position).nMenu);
                intent.putExtra("img", data.get(position).mIcon);
                intent.putExtra("price", data.get(position).nPrice);


                startActivity(intent);

            }
        });
        //통화버튼누르면 Dail 연결
        ImageButton btn = (ImageButton) findViewById(R.id.dial);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent implicit_intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-908-8005"));
                startActivity(implicit_intent);
            }



        });
        


        }
}