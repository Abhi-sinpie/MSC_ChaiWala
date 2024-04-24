package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.firebase.database.core.Repo;

public class AdminHome extends AppCompatActivity {
Animation animRight,animLeft,animTop;
CardView Category,Product,Report,Bill;
TextView Title;
CardView Logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        getSupportActionBar().hide();

        findWidged();

        Category.setAnimation(animLeft);
        Bill.setAnimation(animLeft);
        Product.setAnimation(animRight);
        Report.setAnimation(animRight);
        Title.setAnimation(animTop);
        Bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SellActivity.class);
                startActivity(intent);
            }
        });
        Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Category.class);
                startActivity(intent);
            }
        });
        Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Product.class);
                finish();

                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
       Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences =
                        getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
// write all the data entered by the user in SharedPreference and apply
                myEdit.putInt("islogged", 0);
                myEdit.apply();
               Intent intent=new Intent(getApplicationContext(),MainActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

               startActivity(intent);
            }
        });

    }
    public void findWidged()
    {
        animTop= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        animRight= AnimationUtils.loadAnimation(this,R.anim.right_animation);
        animLeft= AnimationUtils.loadAnimation(this,R.anim.left_animation);
        Category=findViewById(R.id.Card1);
        Product=findViewById(R.id.Card2);
        Report=findViewById(R.id.Car4);
        Bill=findViewById(R.id.Card3);
        Title=findViewById(R.id.textViewHome);
        Logout=findViewById(R.id.logoutCard);

          }
}