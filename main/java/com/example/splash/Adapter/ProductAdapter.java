package com.example.splash.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splash.AddProduct;
import com.example.splash.Model.CategoryModel;
import com.example.splash.Model.ProductModel;
import com.example.splash.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductAdapter  extends ArrayAdapter<ProductModel> {

    public ProductAdapter(Context context, ArrayList<ProductModel> productList){
        super(context,0,productList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View currentItemView=convertView;

        if(currentItemView==null){
            currentItemView= LayoutInflater.from(getContext()).inflate(R.layout.row_product,parent,false);
        }
        ProductModel p=getItem(position);
        TextView txtproductname=currentItemView.findViewById(R.id.txtpName);
        TextView txtproductcategory=currentItemView.findViewById(R.id.txtpCat);
        TextView txtproductPrice=currentItemView.findViewById(R.id.txtpPrice);
        TextView txtproductQty=currentItemView.findViewById(R.id.textViewQty);
        txtproductname.setText(p.getPname());
        String categoryname=p.getCatid();
        //txtproductcategory.setText(p.getCatid());

            FirebaseDatabase.getInstance().getReference("category").
                        orderByChild("id").
                        equalTo(categoryname).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for(DataSnapshot postSnapshot: snapshot.getChildren()){
                            CategoryModel c=postSnapshot.getValue(CategoryModel.class);
                            if(p==null){
                               // Toast.makeText(ProductAdapter.this, "name found", Toast.LENGTH_SHORT).show();
                                return;
                            }else{

                                txtproductcategory.setText(c.getName());
//                                pcatidtxtvie.setText(p.getId());


                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });

        txtproductPrice.setText(String.valueOf(p.getPprice()));
        txtproductQty.setText(String.valueOf(p.getProductqty()));
        Button btn=currentItemView.findViewById(R.id.editepbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AddProduct.class);
                intent.putExtra("id",p.getPid());
                intent.putExtra("name",p.getPname());
                intent.putExtra("category",p.getCatid());
                intent.putExtra("price",p.getPprice());
                intent.putExtra("Qty",p.getProductqty());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);

            }
        });
        return currentItemView;

    }
}



