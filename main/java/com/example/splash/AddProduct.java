package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splash.Model.CategoryModel;
import com.example.splash.Model.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddProduct extends AppCompatActivity {
EditText edProductName,edProductcat,edProductPrice,edProductQty;
    Spinner sp;
    Button buttonAddProduct;
    CardView ViewProductCardbtn;
    TextView textViewHome,pcatidtxtvie;
    Animation animRight,animLeft,animTop;
    String id="";
    String catid="";
    boolean isFound;
    DatabaseReference db;
    List<String> data=new ArrayList<>();

    ArrayList<CategoryModel> persons=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        getSupportActionBar().hide();
        findWidged();
        sp.setPopupBackgroundDrawable(new ColorDrawable(Color.rgb(250,187,155)));
        textViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminHome.class);
                startActivity(intent);
            }
        });
        id=getIntent().getStringExtra("id");
        db = FirebaseDatabase.getInstance().getReference("product");

        ViewProductCardbtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Product.class);
                startActivity(intent);
            }
        });

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                catid=data.get(position);
                FirebaseDatabase.getInstance().getReference("category").
                        orderByChild("name").
                        equalTo(catid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for(DataSnapshot postSnapshot: snapshot.getChildren()){
                            CategoryModel p=postSnapshot.getValue(CategoryModel.class);
                            if(p==null){
                                Toast.makeText(AddProduct.this, "name found", Toast.LENGTH_SHORT).show();
                                return;
                            }else{

catid=p.getId();
//                                pcatidtxtvie.setText(p.getId());


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
                catid="";
            }
        });



        if(id!=null)
        {
            //get name
            String name=getIntent().getStringExtra("name");
            String id=getIntent().getStringExtra("id");
            String category=getIntent().getStringExtra("category");

            int price=getIntent().getIntExtra("price",0);
            int Qty=getIntent().getIntExtra("Qty",0);
            //change title bar title
            getSupportActionBar().setTitle("Edit Product");
            edProductName.setText(name);

            catid=category;
            edProductPrice.setText(String.valueOf(price));
            edProductQty.setText(String.valueOf(Qty));


            buttonAddProduct.setText("Update");
            buttonAddProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (TextUtils.isEmpty(edProductName.getText().toString())) {
                        Toast.makeText(AddProduct.this, "Enter  Product Name", Toast.LENGTH_SHORT).show();
                        edProductName.requestFocus();
                        return;
                    } else if (TextUtils.isEmpty(edProductQty.getText().toString())) {
                        Toast.makeText(AddProduct.this, "Enter  QTY", Toast.LENGTH_SHORT).show();
                        edProductQty.requestFocus();
                        return;
                    } else if (TextUtils.isEmpty(edProductPrice.getText().toString())) {
                        Toast.makeText(AddProduct.this, "Enter  Price", Toast.LENGTH_SHORT).show();
                        edProductPrice.requestFocus();
                        return;

                    } else if (TextUtils.isEmpty(catid)) {
                        Toast.makeText(AddProduct.this, "Select Category", Toast.LENGTH_SHORT).show();
                        sp.requestFocus();
                        return;
                    } else {


                        //Integer.parseInt(edProductQty.getText().toString()),Integer.parseInt(edProductPrice.getText()
                        ProductModel person = new ProductModel(id, edProductName.getText().toString(),
                                catid, Integer.parseInt(edProductQty.getText().toString()), Integer.parseInt(edProductPrice.getText().toString()));
                        FirebaseDatabase.getInstance().getReference("product").child(id).setValue(person).
                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), Product.class);

                                            startActivity(intent);
                                            Toast.makeText(AddProduct.this, "Product Updated", Toast.LENGTH_SHORT).show();
                                            //clearsWidgets();
                                        } else {
                                            Toast.makeText(AddProduct.this, "Unable to save", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            });
        }
        else{
            getSupportActionBar().setTitle("Add Product");
            buttonAddProduct.setText("Save");
            buttonAddProduct.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (TextUtils.isEmpty(edProductName.getText().toString())) {
                        Toast.makeText(AddProduct.this, "Enter  Product Name", Toast.LENGTH_SHORT).show();
                        edProductName.requestFocus();
                        return;
                    }
                    else if (TextUtils.isEmpty(edProductQty.getText().toString())) {
                        Toast.makeText(AddProduct.this, "Enter  QTY", Toast.LENGTH_SHORT).show();
                        edProductQty.requestFocus();
                        return;
                    }
                    else  if (TextUtils.isEmpty(edProductPrice.getText().toString())) {
                    Toast.makeText(AddProduct.this, "Enter  Price", Toast.LENGTH_SHORT).show();
                    edProductPrice.requestFocus();
                    return;
              }
                    else if (TextUtils.isEmpty(catid)) {
                        Toast.makeText(AddProduct.this, "Select Category", Toast.LENGTH_SHORT).show();
                        sp.requestFocus();
                        return;
                    }

                    else {

                        //  clearsWidgets();
                        String id = db.push().getKey();
                        ProductModel category = new ProductModel(id, edProductName.getText().toString().toLowerCase(),
                                catid,Integer.parseInt(edProductQty.getText().toString()),Integer.parseInt(edProductPrice.getText().toString()));
                        FirebaseDatabase.getInstance().getReference("product").child(id).setValue(category).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(AddProduct.this, "Save Product", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Product.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(AddProduct.this, "Not Save Product", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
                    }
                }

            });
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseDatabase.getInstance().getReference("category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                persons.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    CategoryModel p = postSnapshot.getValue(CategoryModel.class);
                    data.add(p.getName());
                }
                ArrayAdapter<String> adp=new ArrayAdapter<String>(AddProduct.this, android.R.layout.simple_spinner_item, data);
                adp.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                sp.setAdapter(adp);
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    private  void findWidged()
    {
        buttonAddProduct=findViewById(R.id.buttonAddProduct);
        edProductName=findViewById(R.id.edProduct);
        edProductPrice=findViewById(R.id.edProductPrice);
        ViewProductCardbtn=findViewById(R.id.ViewProductCardbtn);
        textViewHome=findViewById(R.id.textViewHomeaddProduct);
        animTop= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        animRight= AnimationUtils.loadAnimation(this,R.anim.right_animation);
        animLeft= AnimationUtils.loadAnimation(this,R.anim.left_animation);
        textViewHome.setAnimation(animTop);
        pcatidtxtvie=findViewById(R.id.pcatidtxtvie);
        sp=findViewById(R.id.spinner);
        edProductQty=findViewById(R.id.edProductQty);
    }

}