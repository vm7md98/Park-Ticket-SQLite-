package com.example.parkTicket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminBooking extends AppCompatActivity {


    EditText edtbknum;
    Alter alert = new Alter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booking);

        edtbknum = (EditText)findViewById(R.id.edt_bknum);
    }

    //get all booking
    public void getAllBooking(View view)
    {
        Intent intent = new Intent(AdminBooking.this,AllBooking.class);
        intent.putExtra("showall","showall");
        startActivity(intent);
    }

    // find booking
    public void find(View view)
    {
        String bknum = edtbknum.getText().toString();

        if(bknum.isEmpty())
        {
            alert.sendMsg("Error","Fix the errors on the screen",AdminBooking.this);
            edtbknum.setError("Field cannot be empty");
        }
        else
        {
            Intent intent = new Intent(AdminBooking.this,AllBooking.class);
            int Bknum = Integer.parseInt(edtbknum.getText().toString());
            intent.putExtra("findbknum",Bknum);
            startActivity(intent);
        }

    }

    //delete booking
    public void deleteBookingByBkNum(View view)
    {
        String edbknum = edtbknum.getText().toString();

        if(edbknum.isEmpty())
        {
            alert.sendMsg("Error","Fix the errors on the screen",AdminBooking.this);
            edtbknum.setError("Field cannot be empty");
        }
        else
        {
            // 1 - get input
            int booking_num = Integer.parseInt(edtbknum.getText().toString());
            // 2 - call the database helper class to deletemember from the database
            MyDBHelper myhelper = new MyDBHelper(this);
            myhelper.deleteBooking(booking_num);

            // 3-show message
            String msg = "Booking Deleted";
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }

    }

    //update booking
    public void updateBookingByBknum(View view)
    {
        String edbknum = edtbknum.getText().toString();

        if(edbknum.isEmpty())
        {
            alert.sendMsg("Error","Fix the errors on the screen",AdminBooking.this);
            edtbknum.setError("Field cannot be empty");
        }
        else
        {
            int id = Integer.parseInt(edtbknum.getText().toString());
            Intent intent = new Intent(AdminBooking.this,AdminUpdateBooking.class);
            intent.putExtra("idBooking",id);
            startActivity(intent);
        }


    }

    //back to home
    public void Back(View view)
    {
        Intent intent = new Intent(this,AdminHome.class);
        startActivity(intent);
    }
}