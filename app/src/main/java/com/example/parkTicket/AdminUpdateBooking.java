package com.example.parkTicket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminUpdateBooking extends AppCompatActivity {

    EditText edtpknum,edtid,edtnumtic,edttotal;
    TextView txvbknum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_booking);

        edtpknum = (EditText)findViewById(R.id.edtpknum);
        edtid = (EditText)findViewById(R.id.edtid);
        edtnumtic = (EditText)findViewById(R.id.edt_numtic);
        edttotal = (EditText)findViewById(R.id.edt_total);
        txvbknum = (TextView)findViewById(R.id.txv_idbknum);

        //recevie data from admin booking
        Intent intent = getIntent();
        int id = intent.getIntExtra("idBooking",0);
        txvbknum.setText(String.valueOf(id));

        //get data from DB
        MyDBHelper myhelper = new MyDBHelper(this);
        int bknum = Integer.parseInt(txvbknum.getText().toString());
        ClassBooking b = myhelper.getDataBookingByBkNum(bknum);

        String msg = "";

        if (b != null) {
            // display on the screen
            msg = "Booking Found";
            edtpknum.setText(""+b.getPark_num());
            edtid.setText(""+b.getId());
            edtnumtic.setText(""+b.getNumber_of_ticket());
            edttotal.setText(""+ b.getTotal_price());


        } else
        {
            edtpknum.setText("");
            edtnumtic.setText("");
            edttotal.setText("");
            // display member not found
            msg = "Booking Does Not Exist";
        }

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


                    /******************** BUTTON *****************/
    public void updateBooking(View view)
    {
        // 1 - get all inputs
        int bknum = Integer.parseInt(txvbknum.getText().toString());
        int pknum = Integer.parseInt(edtpknum.getText().toString());
        int id = Integer.parseInt(edtid.getText().toString());
        int numTic = Integer.parseInt(edtnumtic.getText().toString());
        double total = Double.parseDouble(edttotal.getText().toString());



        // 2 - call the database helper class to insert into the database

        MyDBHelper myhelper = new MyDBHelper(this);
        myhelper.updateBooking(bknum,pknum,id,numTic,total);

        String msg = "Member updated";

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(AdminUpdateBooking.this,AdminBooking.class);
        startActivity(intent);
    }

    //back to home
    public void Back(View view)
    {
        Intent intent = new Intent(this,AdminBooking.class);
        startActivity(intent);
    }

}