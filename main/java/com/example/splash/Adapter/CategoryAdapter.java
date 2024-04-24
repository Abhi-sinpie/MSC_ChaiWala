package com.example.splash.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.splash.AddCategory;
import com.example.splash.Model.CategoryModel;
import com.example.splash.R;

import java.util.ArrayList;

public class CategoryAdapter  extends ArrayAdapter<CategoryModel> {

    public CategoryAdapter(Context context, ArrayList<CategoryModel> personList){
        super(context,0,personList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View currentItemView=convertView;

        if(currentItemView==null){
            currentItemView= LayoutInflater.from(getContext()).inflate(R.layout.row_category,parent,false);
        }
        CategoryModel p=getItem(position);
        TextView txtCategory=currentItemView.findViewById(R.id.textView2);
        txtCategory.setText(p.getName());
        Button btn=currentItemView.findViewById(R.id.EditCategorybtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AddCategory.class);
                intent.putExtra("id",p.getId());
                intent.putExtra("name",p.getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);

            }
        });
        return currentItemView;

    }
}


