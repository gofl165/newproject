package com.example.msi.newproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        icon.setImageResource(mItems.get(position).mIcon);

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
    int mIcon; // image resource
    String nMenu; // text
    String nPrice;  // text

    MyItem(int aIcon, String aMenu, String aPrice) {
        mIcon = aIcon;
        nMenu = aMenu;
        nPrice = aPrice;
    }

}