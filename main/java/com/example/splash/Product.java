package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.splash.Adapter.ProductAdapter;
import com.example.splash.Model.ProductModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Product extends AppCompatActivity {
    DatabaseReference db;
    CardView AddProductbtn;
    EditText edProductName,edProductCat,edProductPrice;
    ListView lstproduct;
    String personId;
    Button btn;
    TextView textViewHome,textViewpname,textViewpcat,textViewpprice;
    Animation animRight,animLeft,animTop;
    ArrayList<ProductModel> persons=new ArrayList<>();
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().hide();

        findWidgets();
        textViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminHome.class);
                startActivity(intent);
            }
        });
//        textViewHome.setAnimation(animTop);
        db = FirebaseDatabase.getInstance().getReference("product");

        AddProductbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String id = db.push().getKey();
//                Category category = new Category(id, edCategory.getText().toString());
//                FirebaseDatabase.getInstance().getReference("category").child(id).setValue(category).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(MainActivity.this, "Category Saved", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(MainActivity.this, "Unable to save", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                startActivity(intent);
            }
        });
//   lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                CategoryModel p=(CategoryModel)parent.getItemAtPosition(position);
//                edCategory.setText(p.getName());
//                personId=p.getId();
//            }
//        });


    }
    private void findWidgets(){
        //edCategory=findViewById(R.id.editTextTextPersonName);
        AddProductbtn=findViewById(R.id.AddProductCardbtn);
        lstproduct=findViewById(R.id.listViewProduct);
//        EditCategorybtn=findViewById(R.id.EditCategorybtn);
        textViewHome=findViewById(R.id.textViewHomeCardp);
        textViewpname=findViewById(R.id.txtpName);
        textViewpcat=findViewById(R.id.txtpCat);
        textViewpprice=findViewById(R.id.txtpPrice);
        animTop= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        animRight= AnimationUtils.loadAnimation(this,R.anim.right_animation);
        animLeft= AnimationUtils.loadAnimation(this,R.anim.left_animation);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseDatabase.getInstance().getReference("product").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                persons.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    ProductModel p = postSnapshot.getValue(ProductModel.class);
                    persons.add(p);
                }
                ProductAdapter adp=new ProductAdapter(getApplicationContext(),persons);
                lstproduct.setAdapter(adp);
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}
