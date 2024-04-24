package com.example.splash;

import android.content.Intent;
import android.widget.Toast;

import com.example.splash.Model.CategoryModel;
import com.example.splash.Model.TokenManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonFun {
    private static boolean isSaved=false;
    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
    public static int getTokenNumber(){
    String curDate=getCurrentDate();
    TokenManager tm=getTokenByDate(curDate);
    Boolean isChecked=false;
    if(tm==null)
    {
        isChecked=saveTokenNumber(curDate);
        if(isChecked==true)
        {
            return 1;
        }
        else {
            return -1;
        }
    }
    else {
        int no=tm.getCurrentToken()+1;
        isChecked=updateToken(no,curDate);
        if(isChecked==true){
            return no;
        }
        else {
            return -1;
        }
    }
    }
    public static boolean saveTokenNumber(String curDate){
        TokenManager tokenManager=new TokenManager();
        tokenManager.setDate(curDate);
        tokenManager.setCurrentToken(1);

        FirebaseDatabase.getInstance().getReference("TokenManager").child(curDate).setValue(tokenManager).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if(task.isSuccessful()){
                            isSaved=true;
                        }else{
                            isSaved=false;
                        }
                    }
                });
        return isSaved;
    }
    public static Boolean updateToken(int no,String curDate){
        TokenManager tokenManager=new TokenManager();
        tokenManager.setCurrentToken(no);
        tokenManager.setDate(curDate);
        FirebaseDatabase.getInstance().getReference("TokenManager").child(curDate).setValue(tokenManager).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if(task.isSuccessful()){
                            isSaved=true;

                        }else{
                            isSaved=false;

                        }
                    }
                });
        return isSaved;
    }
    public static TokenManager getTokenByDate(String curDate)
    {
        TokenManager p=new TokenManager();
        FirebaseDatabase.getInstance().getReference("TokenManager").
                orderByChild("curDate").
                equalTo(curDate).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot: snapshot.getChildren()){
                    TokenManager p=postSnapshot.getValue(TokenManager.class);
                    break;
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    return p;
    }
}
