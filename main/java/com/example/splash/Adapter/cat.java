package com.example.splash.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.splash.AddCategory;
import com.example.splash.Model.CategoryModel;
import com.example.splash.R;

import java.util.ArrayList;

public class cat  extends ArrayAdapter<CategoryModel> {

    public cat(Context context, ArrayList<CategoryModel> personList){
        super(context,0,personList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View currentItemView=convertView;

        if(currentItemView==null){
            currentItemView= LayoutInflater.from(getContext()).inflate(R.layout.row_cat,parent,false);
        }
        CategoryModel p=getItem(position);
        TextView txtCategory=currentItemView.findViewById(R.id.textView2);
        txtCategory.setText(p.getName());



        return currentItemView;

    }
}


