package com.example.parkTicket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Congratulation extends AppCompatActivity {

    TextView userName,txvbknum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);

        userName = (TextView)findViewById(R.id.txv_username);
        txvbknum = (TextView)findViewById(R.id.txv_bknum);

        //receive data from confrim
        Intent intent = getIntent();
        String username = intent.getStringExtra("UserName");
        int pknum = intent.getIntExtra("ParkNum1",0);
        int id = intent.getIntExtra("id",0);

        userName.setText(username);


        //call the database helper class to get number of booking
        MyDBHelper myhelper = new MyDBHelper(this);
        ClassBooking cb  = myhelper.getDataBooking(pknum,id);

        txvbknum.setText(String.valueOf(cb.getBooking_num()));
    }


                                /************** ON CLICK *************/
                                /**
                                 * BackToHome
                                 **/

    public void BackToHome(View view)
    {
        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("id",0);
        String username = userName.getText().toString();
        Intent intent = new Intent(Congratulation.this,Home.class);
        intent.putExtra("UserName",username);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}