package com.example.msi.newproject;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class FrgMenuDetail extends Fragment {
    static int index=-1;
    String a;String b;String c;String d;String t4;
    public FrgMenuDetail() {
        // Required empty public constructor
    }
    public void setSelection(int i) { index = i; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_frg_menu_detail, container, false);


        TextView menu = (TextView)view.findViewById(R.id.text1);
        TextView price = (TextView)view.findViewById(R.id.text2);
        TextView detail=(TextView)view.findViewById(R.id.text3);
        ImageView img = (ImageView)view.findViewById(R.id.imageView);
        menu.setText(a);
        price.setText(b);
        //img.setImageResource(c);

        detail.setText(d);
        if (index >=0) {
            String t1=getArguments().getString("menu2");
            String t2=getArguments().getString("price2");
            String t3=getArguments().getString("detail2");
            String t4=getArguments().getString("imgname");
            menu.setText(t1);
            price.setText(t2);
            detail.setText(t3);
            File F=new File("storage/emulated/0/Android/data/com.example.msi.newproject/files/Pictures",t4);
            img.setImageURI(Uri.fromFile(F));
//            img.setImageResource(R.drawable.boom);

        }
        else{
            File F=new File("storage/emulated/0/Android/data/com.example.msi.newproject/files/Pictures",c);
            img.setImageURI(Uri.fromFile(F));
        }


        return view;
    }
    //MenuDetail 에서 인텐트로받은 String,int값
    public void add(String aa,String bb,String cc,String dd){
        a=aa;
        b=bb;
        c=cc;
        d=dd;
    }

}
