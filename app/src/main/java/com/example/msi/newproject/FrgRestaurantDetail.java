package com.example.msi.newproject;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FrgRestaurantDetail extends Fragment {
    int mCurCheckPosition = -1;
    private DBHelper mDbHelper;
    public interface OnTitleSelectedListener {
        public void onTitleSelected(int i);
    }

    public FrgRestaurantDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_frg_restaurant_detail, container, false);
    }

}
