package com.example.splash;
//TeaShops
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splash.Model.ValidLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText edName,edPass;
    Button btnLog;
    DatabaseReference db;
    TextView textViewHome;
    boolean isUserFound=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        if(checkLogin()){
            Intent intent = new Intent(getApplicationContext(), AdminHome.class);

            startActivity(intent);
        }

/*
        This code should be done only once To create Database on Firebase
        !! Do this code and run once then comment it !!

        add user to the table
        ValidLogin user=new ValidLogin("admin","admin");
        push to the firebase
        db=FirebaseDatabase.getInstance().getReference("ValidLogin");
        String id=db.push().getKey();
        FirebaseDatabase.getInstance().getReference("ValidLogin").push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        findwidgets();

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(edName.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter  UserName", Toast.LENGTH_SHORT).show();
                    edName.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(edPass.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter  PassWord", Toast.LENGTH_SHORT).show();
                    edPass.requestFocus();
                    return;
                } else {
                    setButton(true);
                    FirebaseDatabase.getInstance().
                            getReference("ValidLogin").orderByChild("username").
                            equalTo(edName.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            isUserFound = false;
                            for (DataSnapshot postSnapShot : snapshot.getChildren()) {
                                ValidLogin v = postSnapShot.getValue(ValidLogin.class);
                                if (v == null) {
                                    setButton(false);
                                    Toast.makeText(MainActivity.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                                    return;
                                } else {
                                    Log.d("edPass", edPass.getText().toString());
                                    Log.d("vpass", v.getPassword());
                                    if (v.getPassword().equals(edPass.getText().toString())) {
                                        isUserFound = true;
                                        //add shared pref
                                        AddSharedPref();
                                        edName.setText("");
                                        edPass.setText("");
                                        edName.requestFocus();
                                        Intent intent = new Intent(getApplicationContext(), AdminHome.class);

                                        startActivity(intent);
                                    } else {
                                        //error
                                        setButton(false);
                                        Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            }

                            if (!isUserFound) {
                                setButton(false);
                                Toast.makeText(MainActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            setButton(false);
                        }
                    });

                }
            }
        });






    }
    private void findwidgets()
    {
        edName=findViewById(R.id.Username);
        edPass=findViewById(R.id.PassWord);
        btnLog=findViewById(R.id.buttonLogin);
        textViewHome=findViewById(R.id.textViewHome);
    }
    private void setButton(boolean isBusy){
        if(isBusy){
            btnLog.setText("Logging...");
            btnLog.setEnabled(false);
        }else{
            btnLog.setText("Log In");
            btnLog.setEnabled(true);
        }
    }
    private void AddSharedPref(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
// write all the data entered by the user in SharedPreference and apply
        myEdit.putInt("islogged", 1);
        myEdit.apply();
    }
    private boolean checkLogin(){
        SharedPreferences sh = getSharedPreferences("MySharedPref",
                MODE_PRIVATE);
        int a = sh.getInt("islogged", 0);
        if(a==0){
            return false;

        }else{
            return true;
        }
    }



}