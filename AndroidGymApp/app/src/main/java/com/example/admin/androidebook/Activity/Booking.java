package com.example.admin.androidebook.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.androidebook.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Booking extends AppCompatActivity implements OnClickListener {
    Button button,button2;
    EditText mail,ngaytap,matkhau;
    DatabaseReference mData;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        mData=FirebaseDatabase.getInstance().getReference();
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        mail=(EditText)findViewById(R.id.editText);
        ngaytap=(EditText)findViewById(R.id.editText2);
        ngaytap.setOnClickListener(this);
        matkhau=(EditText)findViewById(R.id.editText3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String a=mail.getText().toString();
                final String b=ngaytap.getText().toString();
                final String c=matkhau.getText().toString();
                LichTap lichtap=new LichTap(a,b,c);
                mData.child("LichTap").push().setValue(lichtap);
                Toast.makeText(getApplicationContext(),"Lịch tập đã được đặt",Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Booking.this,MainActivity.class));
            }
        });
    }

    public void onClick(View view) {
        if (view == ngaytap) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            ngaytap.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}

