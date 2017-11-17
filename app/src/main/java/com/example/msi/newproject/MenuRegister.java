package com.example.msi.newproject;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuRegister extends AppCompatActivity {

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
        setContentView(R.layout.activity_menu_register);
        ImageButton imageCaptureBtn = (ImageButton)findViewById(R.id.imageCaptureBtn);
        imageCaptureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText main=(EditText) findViewById(R.id.name);
                ImageView img=(ImageView)findViewById(R.id.imageCaptureBtn);
                EditText t1=(EditText) findViewById(R.id.address);
                EditText t2=(EditText) findViewById(R.id.phone);

                setting = getSharedPreferences(PREFERENCES_1, MODE_PRIVATE);

                SharedPreferences.Editor editor = setting.edit();

                String name = main.getText().toString();
                editor.putString(PREFERENCES_ATTR1, name);


                String a = t1.getText().toString();
                editor.putString(PREFERENCES_ATTR2, a);


                String b = t2.getText().toString();
                editor.putString(PREFERENCES_ATTR3, b);
//               img bit로 바꿔서 저장하려다 포기
//                Bitmap bit;
//                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
//                bit.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//                byte[] imageBytes=byteArrayOutputStream.toByteArray();
//                Base64.encodeToString(imageBytes,Base64.NO_WRAP);

                String c =mPhotoFileName;
                editor.putString(PREFERENCES_IMG,c);

                editor.commit();


        Intent intent =new Intent(getApplicationContext(),RestaurantDetail.class);
        startActivity(intent);
            }
        });





//        mDbHelper = new DBHelper(this);

    }

    String mPhotoFileName;
    File mPhotoFile;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String currentDateFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String  currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //1. 카메라 앱으로 찍은 이미지를 저장할 파일 객체 생성
            mPhotoFileName = "IMG"+currentDateFormat()+".jpg";
            mPhotoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName);

            if (mPhotoFile !=null) {
                //2. 생성된 파일 객체에 대한 Uri 객체를 얻기
                Uri imageUri = FileProvider.getUriForFile(this, "com.example.msi.newproject", mPhotoFile);
                //authority에 패키지이름 고쳐주기
                //3. Uri 객체를 Extras를 통해 카메라 앱으로 전달
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else
                Toast.makeText(getApplicationContext(), "file null", Toast.LENGTH_SHORT).show();
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (mPhotoFileName != null) {
                mPhotoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName);
                ImageButton imageCaptureBtn = (ImageButton)findViewById(R.id.imageCaptureBtn);

                imageCaptureBtn.setImageURI(Uri.fromFile(mPhotoFile));

            }
        }

    }
//                        아래 database 관련 나중에 쓸것같아서 일단 주석처리
//    private void insertRecord() {
//        EditText name = (EditText)findViewById(R.id.name);
//        EditText address = (EditText)findViewById(R.id.address);
//        EditText phone = (EditText)findViewById(R.id.phone);
//        long nOfRows = mDbHelper.insertUserByMethod(name.getText().toString(),address.getText().toString(),phone.getText().toString());
//        if (nOfRows >0)
//            Toast.makeText(this,nOfRows+" Record Inserted", Toast.LENGTH_SHORT).show();
//        else
//            Toast.makeText(this,"No Record Inserted", Toast.LENGTH_SHORT).show();
//    }




}
