package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends AppCompatActivity {
Animation topanim,bottomnim;
ImageView img;
TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        topanim= AnimationUtils.loadAnimation(this,R.anim.top_animation);

        bottomnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        img=findViewById(R.id.imageView);
        tv1=findViewById(R.id.textView);
        tv2=findViewById(R.id.textView2);

        img.setAnimation(topanim);
        tv1.setAnimation(bottomnim);
        tv2.setAnimation(bottomnim);


      new CountDownTimer(6000,1000)
      {
          @Override
          public void onTick(long millisUntilFinished) {

          }

          @Override
          public void onFinish() {
              Intent intent=new Intent(getApplicationContext(),MainActivity.class);

           finish();
              startActivity(intent);
          }
      } .start();

    }
}