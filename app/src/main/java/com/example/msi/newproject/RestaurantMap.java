package com.example.msi.newproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RestaurantMap extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener{
    final int REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION=0;
    private FusedLocationProviderClient mFusedLocationClient;
    Location mCurrentLocation;
    GoogleMap mGoogleMap;
//    TextView addressTextView;
    DBHelper mDbHelper;
    //a마지막 geocoder 이용해서 input이용해서 이름에 매칭되는 addresses리스트 리턴 그중에서 첫번째get0을 가져와 bestReult가져오고 위도경도 얻어옴
//그결과값 바탕으로 마커로 표시하고 그위치로 지도를 이동

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_map);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mDbHelper = new DBHelper(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (!checkLocationPermissions()) {
            requestLocationPermissions(REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION);
        } else {
            getLastLocation();
        }
        Button btn=(Button)findViewById(R.id.button);
        final EditText txt=(EditText)findViewById(R.id.edit_text) ;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (txt.getText().equals("한성대입구역")) {
//
//                    Toast.makeText(getApplicationContext(),"한성대입구역을 선택하셨습니다", Toast.LENGTH_SHORT).show();
//                }
                getAddress();


            }
        });







    }
//    ---------Menu---------
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quick_action1:

                Cursor cursor = mDbHelper.getAllUsersByMethod();

//                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
//                    buffer.append(cursor.getInt(0)+" \t");
//                    buffer.append(cursor.getString(1)+" \t");
//                    buffer.append(cursor.getString(2)+"\n");//주소
                  //주소
                    Log.i("adad",cursor.getString(2));
                    try {
                        Geocoder geocoder = new Geocoder(this, Locale.KOREA);
                        List<Address> addresses = geocoder.getFromLocationName( cursor.getString(2),1);
                        if (addresses.size() >0) {
                            Address bestResult = (Address) addresses.get(0);

                            LatLng location = new LatLng(bestResult.getLatitude(),bestResult.getLongitude());
                            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15));
                            mGoogleMap.addMarker(
                                    new MarkerOptions().
                                            position(location).
                                            title(cursor.getString(2)));
                            mGoogleMap.setOnMarkerClickListener(this);
                        }
                    } catch (IOException e) {
                        Log.e(getClass().toString(),"Failed in using Geocoder.", e);

                    }
                }
                cursor.close();
                return true;
            case R.id.km1:
                //https://www.androidpub.com/978358 2,3km에서 1km 할때 마커 안지워지는거 해결하기 위해 구글맵.clear하는법 알아옴
              mGoogleMap.clear();
                //////////////////////
                Cursor cursor2 = mDbHelper.getAllUsersByMethod();
                item.setChecked(true);
//                StringBuffer buffer = new StringBuffer();
                while (cursor2.moveToNext()) {
                    try {
                        Geocoder geocoder = new Geocoder(this, Locale.KOREA);
                        List<Address> addresses = geocoder.getFromLocationName( cursor2.getString(2),1);
                        if (addresses.size() >0) {
                            Address bestResult = (Address) addresses.get(0);
                            LatLng location = new LatLng(bestResult.getLatitude(),bestResult.getLongitude());
                           Location location1=new Location("L1");
                           location1.setLatitude(bestResult.getLatitude());
                           location1.setLongitude(bestResult.getLongitude());
                           double a=location1.distanceTo(mCurrentLocation);
                           if(a<1000) {
                               mGoogleMap.addMarker(
                                       new MarkerOptions().
                                               position(location).
                                               title(cursor2.getString(2)));
                               mGoogleMap.setOnMarkerClickListener(this);
                           }
                        }
                    } catch (IOException e) {
                        Log.e(getClass().toString(),"Failed in using Geocoder.", e);

                    }
                }
                cursor2.close();
                return true;
            case R.id.km2:
                mGoogleMap.clear();
                Cursor cursor3 = mDbHelper.getAllUsersByMethod();
                item.setChecked(true);
//                StringBuffer buffer = new StringBuffer();
                while (cursor3.moveToNext()) {
                    try {
                        Geocoder geocoder = new Geocoder(this, Locale.KOREA);
                        List<Address> addresses = geocoder.getFromLocationName( cursor3.getString(2),1);
                        if (addresses.size() >0) {
                            Address bestResult = (Address) addresses.get(0);
                            LatLng location = new LatLng(bestResult.getLatitude(),bestResult.getLongitude());
                            Location location1=new Location("L1");
                            location1.setLatitude(bestResult.getLatitude());
                            location1.setLongitude(bestResult.getLongitude());
                            double a=location1.distanceTo(mCurrentLocation);
                            Log.i("distance", String.valueOf(a));
                            if(a<2000) {
                                mGoogleMap.addMarker(
                                        new MarkerOptions().
                                                position(location).
                                                title(cursor3.getString(2)));
                                mGoogleMap.setOnMarkerClickListener(this);
                            }
                        }
                    } catch (IOException e) {
                        Log.e(getClass().toString(),"Failed in using Geocoder.", e);

                    }
                }
                cursor3.close();
                return true;
            case R.id.km3:
                Cursor cursor4 = mDbHelper.getAllUsersByMethod();
                item.setChecked(true);
//                StringBuffer buffer = new StringBuffer();
                while (cursor4.moveToNext()) {
                    try {
                        Geocoder geocoder = new Geocoder(this, Locale.KOREA);
                        List<Address> addresses = geocoder.getFromLocationName( cursor4.getString(2),1);
                        if (addresses.size() >0) {
                            Address bestResult = (Address) addresses.get(0);
                            LatLng location = new LatLng(bestResult.getLatitude(),bestResult.getLongitude());
                            Location location1=new Location("L1");
                            location1.setLatitude(bestResult.getLatitude());
                            location1.setLongitude(bestResult.getLongitude());
                            double a=location1.distanceTo(mCurrentLocation);
                            if(a<3000) {
                                mGoogleMap.addMarker(
                                        new MarkerOptions().
                                                position(location).
                                                title(cursor4.getString(2)));
                                mGoogleMap.setOnMarkerClickListener(this);
                            }
                        }
                    } catch (IOException e) {
                        Log.e(getClass().toString(),"Failed in using Geocoder.", e);

                    }
                }
                cursor4.close();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }}
///////////////////////////////////////////////////
    private boolean checkLocationPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermissions(int requestCode) {
        ActivityCompat.requestPermissions(
                RestaurantMap.this,            // MainActivity 액티비티의 객체 인스턴스를 나타냄
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},        // 요청할 권한 목록을 설정한 String 배열
                requestCode    // 사용자 정의 int 상수. 권한 요청 결과를 받을 때
        );
    }




    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastLocation();
                    // 권한 획득 후 수행할 일: 예, getLastLocation();
                } else {
                    Toast.makeText(this, "Permission required", Toast.LENGTH_SHORT);
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        Task task = mFusedLocationClient.getLastLocation();       // Task<Location> 객체 반환
        task.addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                Log.i("ddd","fonud");
                if (location != null) {
                    mCurrentLocation = location;
                    Toast.makeText(getApplicationContext(),
                            "위도"+mCurrentLocation.getLatitude(),
                            Toast.LENGTH_SHORT)
                            .show();
                    //  updateUI();
                    LatLng newLocation = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                    if (mGoogleMap != null)
                        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation,15));
                } else
                    Toast.makeText(getApplicationContext(),
                            "No Location Detected",
                            Toast.LENGTH_SHORT)
                            .show();
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
//        LatLng hansung = new LatLng(37.5817891, 127.009854);

//
//        // move the camera
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hansung,15));

//        googleMap.addMarker(new MarkerOptions().position(hansung).title("한성대학교"));
//        // move the camera
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(hansung));
    }
    //******************************************주소얻기******************************
    private void getAddress() {
//        addressTextView = (TextView) findViewById(R.id.result);
        EditText address = (EditText) findViewById(R.id.edit_text);
        try {
            Geocoder geocoder = new Geocoder(this, Locale.KOREA);
            List<Address> addresses = geocoder.getFromLocationName(address.getText().toString(),1);
            if (addresses.size() >0) {
                Address bestResult = (Address) addresses.get(0);

//                addressTextView.setText(String.format("[ %s , %s ]",
//                        bestResult.getLatitude(),
//                        bestResult.getLongitude()));
                LatLng location = new LatLng(bestResult.getLatitude(),bestResult.getLongitude());
                Log.i("aa", String.valueOf(bestResult.getLatitude()));
                Log.i("aa", String.valueOf(bestResult.getLongitude()));
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15));
                mGoogleMap.addMarker(
                        new MarkerOptions().
                                position(location).
                                title(address.getText().toString()));
                mGoogleMap.setOnMarkerClickListener(this);
            }
        } catch (IOException e) {
            Log.e(getClass().toString(),"Failed in using Geocoder.", e);
            return;
        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        //마커클릭하면 알람창 띄우기위해서 일단 GoogleMap.OnMarkerClickListener implements하고
//        구글맵객체에 setOnMarkerClickListener(this);설정 그리고 onMarkerClick()에서 클릭하면
//                AlertDialog를 통해 알림창이 뜨도록 한다.

        //        ----------------------알림창------------------------
//        참고: http://mainia.tistory.com/2017
//        AlertDialog쓰는법 참고함
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        if (okok()) {
            Intent intent3 = new Intent(getApplicationContext(), MenuRegister.class);
            startActivity(intent3);
            return  true;
        } else {

// 제목셋팅
            alertDialogBuilder.setTitle("맛집 등록");

// AlertDialog 셋팅
            alertDialogBuilder
                    .setMessage("새로운 맛집으로 등록하시겠습니까?")
                    .setCancelable(false)
                    .setPositiveButton("예",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    EditText address = (EditText) findViewById(R.id.edit_text);
                                    Intent intent = new Intent(getApplicationContext(), MenuRegister.class);
                                    intent.putExtra("markInfo", address.getText().toString());
                                    startActivity(intent);

                                }
                            })
                    .setNegativeButton("아니오",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    // 다이얼로그를 취소한다
                                    dialog.cancel();
                                }
                            });
// 다이얼로그 생성
            AlertDialog alertDialog = alertDialogBuilder.create();

// 다이얼로그 보여주기
            alertDialog.show();
            return true;
        }
    }

    private boolean okok(){
        EditText address = (EditText) findViewById(R.id.edit_text);
        Cursor cursor2 = mDbHelper.getAllUsersByMethod();
//                StringBuffer buffer = new StringBuffer();
        while (cursor2.moveToNext()) {
            Log.i("wwww",address.getText().toString());
            Log.i("wwaa",cursor2.getString(2));
            String a=address.getText().toString().trim();
            String b=cursor2.getString(2).trim();

            if(a.equals(b)){
                cursor2.close();
                return true;
            }


        }
//                result.setText(buffer);
        cursor2.close();
        return false;
    }
}

