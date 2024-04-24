package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class Report extends AppCompatActivity {


    private DatePickerDialog datePickerDialog;
    Button dateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        initDatePicker();
        dateButton=findViewById(R.id.ToDateButton);

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String  date=makeDateString(dayOfMonth,month,year);

                dateButton.setText(getTodaydate());
            }
        };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
    }

    private String getTodaydate() {
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        month=month+1;
        int day=cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }


    private String makeDateString(int dayOfMonth, int month, int year) {
        return getMonthFormat(month)+" "+dayOfMonth+" "+year;
    }

    private String getMonthFormat(int month) {
        if(month==1){
            return "jan";
        }
        if(month==2){
            return "Feb";
        }
        if(month==3){
            return "Mar";
        }
        if(month==4){
            return "Apl";
        }
        if(month==5){
            return "May";
        }
        if(month==6){
            return "Jun";
        }
        if(month==7){
            return "Jul";
        }
        if(month==8){
            return "Aug";
        }
        if(month==9){
            return "Sep";
        }
        if(month==10){
            return "Oct";
        }
        if(month==11){
            return "Nov";
        }
        if(month==12){
            return "Dec";
        }
        return "Jan";
    }

    public void FromDate(View view) {
        datePickerDialog.show();
    }

    public void ToDatebtn(View view) {
        datePickerDialog.show();
    }
}