package com.example.msi.newproject;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by soo on 2017-10-17.
 */

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private int mResource;
    private ArrayList<MyItem> mItems = new ArrayList<MyItem>();

    public MyAdapter(Context context, int resource, ArrayList<MyItem> items) {
        mContext = context;
        mResource = resource;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent,false);
        }

        // Set Icon
        ImageView icon = (ImageView) convertView.findViewById(R.id.imageView);
//        File mPhotoFile =mItems.get(position).npath;
//        File f=new File(mPhotoFile,mItems.get(position).mIcon);
//        icon.setImageURI(Uri.fromFile(f));
        Log.i("logloglog",mItems.get(position).mIcon);
File F=new File("storage/emulated/0/Android/data/com.example.msi.newproject/files/Pictures",mItems.get(position).mIcon);
icon.setImageURI(Uri.fromFile(F));
//        icon.setImageResource(mItems.get(position).mIcon);

        // Set Text 1
        TextView menu = (TextView) convertView.findViewById(R.id.text1);
        menu.setText(mItems.get(position).nMenu);

        // Set Text 2
        TextView price = (TextView) convertView.findViewById(R.id.text2);
        price.setText(mItems.get(position).nPrice);

        return convertView;
    }

}
class MyItem {
    String mIcon; // image resource
    String nMenu; // text
    String nPrice;  // text
//    File npath;

    MyItem(String aIcon, String aMenu, String aPrice) {
        mIcon = aIcon;
        nMenu = aMenu;
        nPrice = aPrice;
//        npath=path;
    }

}