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

import com.example.splash.Adapter.CategoryAdapter;
import com.example.splash.Model.CategoryModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Category extends AppCompatActivity {
    DatabaseReference db;
    CardView AddCategorybtn;
    EditText edCategory;
    ListView lst;
    String personId;
    Button btn;
    TextView textViewHome;
    Animation animRight,animLeft,animTop;
    ArrayList<CategoryModel> persons=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportActionBar().hide();

        findWidgets();
        textViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminHome.class);
                startActivity(intent);
            }
        });
        textViewHome.setAnimation(animTop);
        db = FirebaseDatabase.getInstance().getReference("category");

        AddCategorybtn.setOnClickListener(new View.OnClickListener() {
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
                Intent intent=new Intent(getApplicationContext(),AddCategory.class);
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
        AddCategorybtn=findViewById(R.id.AddCategoryCardbtn);
        lst=findViewById(R.id.listView);
//        EditCategorybtn=findViewById(R.id.EditCategorybtn);
        textViewHome=findViewById(R.id.textViewHomeCard);
        animTop= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        animRight= AnimationUtils.loadAnimation(this,R.anim.right_animation);
        animLeft= AnimationUtils.loadAnimation(this,R.anim.left_animation);
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
                    persons.add(p);
                }
                CategoryAdapter adp=new CategoryAdapter(getApplicationContext(), persons);
                lst.setAdapter(adp);
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}
