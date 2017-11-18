package com.example.msi.newproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuDetailRegister extends AppCompatActivity {
    private DBHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail_register);
        mDbHelper = new DBHelper(this);

        Intent intent=getIntent();
       final int num=intent.getIntExtra("int",0);

        ImageButton imageCaptureBtn = (ImageButton)findViewById(R.id.menuimg);
        imageCaptureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        Button button = (Button)findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRecord();
                Intent intent =new Intent(getApplicationContext(),RestaurantDetail.class);
                intent.putExtra("int",num);
                intent.putExtra("int2",(int)nOfRows);
                startActivity(intent);
            }
        });
    }
//***********************************






//    ******************************
    long nOfRows;
    private void insertRecord() {
        EditText name = (EditText)findViewById(R.id.mname);
        EditText price = (EditText)findViewById(R.id.mprice);
        EditText detail = (EditText)findViewById(R.id.mdetail);

        nOfRows = mDbHelper.insertUserByMethod2(name.getText().toString(),price.getText().toString(),detail.getText().toString(),mPhotoFileName);

        if (nOfRows >0)
            Toast.makeText(this,nOfRows+" Record Inserted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"No Record Inserted", Toast.LENGTH_SHORT).show();
    }

}
