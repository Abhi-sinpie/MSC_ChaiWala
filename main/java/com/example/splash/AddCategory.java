package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class AddCategory extends AppCompatActivity {
//ALL COME FROM ADD_CATEGORY
    Button btnAddCategory,btnUpdate;
 EditText edCategory;
 CardView ViewCategory;
 TextView textViewHome;
    Animation animRight,animLeft,animTop,animVibrate;
    String id="";
    boolean isFound;
    DatabaseReference db;
    ArrayList<Category> persons=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        getSupportActionBar().hide();
        findWidged();

        textViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminHome.class);
                startActivity(intent);
            }
        });

        id=getIntent().getStringExtra("id");
        ViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Category.class);
                startActivity(intent);
            }
        });
        if(id!=null) {
            //get name

                String name = getIntent().getStringExtra("name");
                //change title bar title
                getSupportActionBar().setTitle("Edit Category");
                edCategory.setText(name);

                btnAddCategory.setText("Update");
                btnAddCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edCategory.getText().toString())) {
                            edCategory.setAnimation(animVibrate);
                            EmptyMsg();

                            return;
                        } else {
                            CategoryModel person = new CategoryModel(id, edCategory.getText().toString());
                            FirebaseDatabase.getInstance().getReference("category").child(id).setValue(person).
                                    addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Intent intent = new Intent(getApplicationContext(), Category.class);

                                                startActivity(intent);
                                                Toast.makeText(AddCategory.this, "Category Updated", Toast.LENGTH_SHORT).show();
                                                clearsWidgets();
                                            } else {
                                                Toast.makeText(AddCategory.this, "Unable to save", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }
                });
            }

        else {
            getSupportActionBar().setTitle("Add Category");
            btnAddCategory.setText("Save");
            btnAddCategory.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(edCategory.getText().toString())) {

                        EmptyMsg();
                        edCategory.setAnimation(animVibrate);
                        return;
                    } else {
                        if (!CheckCategory(edCategory.getText().toString())) {
                            //  clearsWidgets();
                            String id = db.push().getKey();
                            CategoryModel category = new CategoryModel(id, edCategory.getText().toString().toLowerCase());
                            FirebaseDatabase.getInstance().getReference("category").child(id).setValue(category).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AddCategory.this, "Save category", Toast.LENGTH_SHORT).show();
                                        clearsWidgets();
                                    } else {
                                        Toast.makeText(AddCategory.this, "Not Save category", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });
                        } else {
                            Toast.makeText(AddCategory.this, "Category Already Exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
        db = FirebaseDatabase.getInstance().getReference("category");
    }

    private void EmptyMsg() {
        Toast.makeText(AddCategory.this, "Enter  Category", Toast.LENGTH_SHORT).show();
        edCategory.requestFocus();
    }

    private  boolean CheckCategory(String name)
    {
        isFound = false;
        FirebaseDatabase.getInstance().getReference("category")
                .orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
               if(snapshot.exists())
               {
                        isFound=true;
                    }
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        return isFound;
    }
    private void clearsWidgets() {
        edCategory.setText("");

        edCategory.requestFocus();
    }
    public void findWidged()
    {

        ViewCategory=findViewById(R.id.ViewCategoryCardbtn);
        textViewHome=findViewById(R.id.textViewHomeaddCategory);
        btnAddCategory=findViewById(R.id.buttonAdd);
        edCategory=findViewById(R.id.edCategory);
        btnUpdate=findViewById(R.id.buttonAdd);
        animTop= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        animRight= AnimationUtils.loadAnimation(this,R.anim.right_animation);
        animLeft= AnimationUtils.loadAnimation(this,R.anim.left_animation);

        animVibrate= AnimationUtils.loadAnimation(this,R.anim.vibrate);
        textViewHome.setAnimation(animTop);


    }




}







