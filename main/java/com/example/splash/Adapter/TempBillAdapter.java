package com.example.splash.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.splash.Model.TempBillModel;
import com.example.splash.R;

import java.util.ArrayList;

public class TempBillAdapter extends ArrayAdapter<TempBillModel> {

    public TempBillAdapter(Context context, ArrayList<TempBillModel> tempBillModels) {
        super(context, 0,tempBillModels);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.row_tempbill,parent,false);
        }
        TempBillModel tempBillModel=getItem(position);
        TextView tpName=view.findViewById(R.id.txtTempName);
        TextView tCatName=view.findViewById(R.id.txtTempCat);
        TextView tpQty=view.findViewById(R.id.textTempViewQty);
        TextView tprice=view.findViewById(R.id.txtTempPrice);
        Button btnRemove=view.findViewById(R.id.editeTempbtn);
        return  view;
    }
}
