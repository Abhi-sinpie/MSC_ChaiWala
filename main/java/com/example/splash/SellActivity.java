
package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.splash.Adapter.TempBillAdapter;
import com.example.splash.Model.CategoryModel;
import com.example.splash.Model.ProductModel;
import com.example.splash.Model.TempBillModel;
import com.example.splash.Model.ValidLogin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SellActivity extends AppCompatActivity {
    EditText edCustName, edCustMobNo, edProductQTY;
    Spinner edCat, edProduct;
    String catid = "";
    String prodid = "";
    List<String> data = new ArrayList<>();
    List<String> Prod = new ArrayList<>();
    ArrayList<CategoryModel> categoryList = new ArrayList<>();
    ArrayList<ProductModel> Pro = new ArrayList<>();
    ListView lst;
    CardView addMoreSellbtn;
    ArrayList<TempBillModel> BillList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        getSupportActionBar().hide();
        edCustName = findViewById(R.id.edCustName);
        edCustMobNo = findViewById(R.id.edCustMobNo);
        edProductQTY = findViewById(R.id.edProductQty);
        edCat = findViewById(R.id.CategorySpinner);
        edProduct = findViewById(R.id.ProductSpinner);
        lst=findViewById(R.id.listView1);
        addMoreSellbtn=findViewById(R.id.addMoreSellbtn);
       edProduct.setPopupBackgroundDrawable(new ColorDrawable(Color.rgb(250,187,155)));
        edCat.setPopupBackgroundDrawable(new ColorDrawable(Color.rgb(250,187,155)));
        addMoreSellbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillList.add(new TempBillModel());//add all data
                //  String pid,pname,catid,catname;
                //    Double qty,price,amt;
            }
        });
        edCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                catid = data.get(position);
                catid=categoryList.get(position).getId();
                loadProducts(catid);
                //samenamecategory
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                catid = "";
            }
        });
        edProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prodid = Prod.get(position);

                FirebaseDatabase.getInstance().getReference("product").
                        orderByChild("pname").
                        equalTo(prodid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            ProductModel p = postSnapshot.getValue(ProductModel.class);
                            if (p == null) {
                                Toast.makeText(SellActivity.this, "name found", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                prodid = p.getPid();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                catid = "";
            }
        });
        
    }

    private void loadProducts(String catid) {
        FirebaseDatabase.getInstance().
                getReference("product").orderByChild("catid").
                equalTo(catid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Prod.clear();
                for (DataSnapshot postSnapShot : snapshot.getChildren()) {
                    ProductModel v = postSnapShot.getValue(ProductModel.class);
                    Prod.add(v.getPname());
                }
                ArrayAdapter<String> adp=new ArrayAdapter<String>(SellActivity.this, android.R.layout.simple_spinner_item,Prod);
                adp.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                edProduct.setAdapter(adp);
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//CategoryList For Spinner
        FirebaseDatabase.getInstance().getReference("category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                data.clear();
                //CategoryModel defaultCat=new CategoryModel();
                //defaultCat.setId("-1");
                //defaultCat.setName("Select");
                //sdata.add("Select");
                categoryList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    CategoryModel p = postSnapshot.getValue(CategoryModel.class);
                    data.add(p.getName());
                    categoryList.add(p);

                }
                ArrayAdapter<String> adp=new ArrayAdapter<String>(SellActivity.this, android.R.layout.simple_spinner_item, data);
                adp.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                edCat.setAdapter(adp);
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        //Custom List View FOr Show Add Product

    }

}