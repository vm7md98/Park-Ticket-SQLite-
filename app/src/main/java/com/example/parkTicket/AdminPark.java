package com.example.parkTicket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminPark extends AppCompatActivity {

    EditText edtpknum;
    Alter alert = new Alter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_park);

        edtpknum = (EditText)findViewById(R.id.edt_bknum);
    }

    // get all parks
    public void getAllParks(View view)
    {

        Intent intent = new Intent(AdminPark.this,AllPark.class);
        intent.putExtra("showall","showall");
        startActivity(intent);
    }


    // find park by id
    public void findParkByParkNum(View view)
    {
        String edid = edtpknum.getText().toString();

        if(edid.isEmpty())
        {
            alert.sendMsg("Error","Fix the errors on the screen",AdminPark.this);
            edtpknum.setError("Field cannot be empty");
        }
        else
        {
            Intent intent = new Intent(AdminPark.this,AllPark.class);
            int id = Integer.parseInt(edtpknum.getText().toString());
            intent.putExtra("findid",id);
            startActivity(intent);
        }

    }

    //delete park
    public void deleteParkByParkNum(View view)
    {
        String edid = edtpknum.getText().toString();

        if(edid.isEmpty())
        {
            alert.sendMsg("Error","Fix the errors on the screen",AdminPark.this);
            edtpknum.setError("Field cannot be empty");
        }
        else
        {
            // 1 - get input
            int park_num = Integer.parseInt(edtpknum.getText().toString());
            // 2 - call the database helper class to deletemember from the database
            MyDBHelper myhelper = new MyDBHelper(this);
            myhelper.deletePark(park_num);

            // 3-show message
            String msg = "Park Deleted";
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }

    }

    //update park
    public void updateParkByParkNum(View view)
    {
        String edid = edtpknum.getText().toString();

        if(edid.isEmpty())
        {
            alert.sendMsg("Error","Fix the errors on the screen",AdminPark.this);
            edtpknum.setError("Field cannot be empty");
        }
        else
        {
            int park_num = Integer.parseInt(edtpknum.getText().toString());
            Intent intent = new Intent(AdminPark.this,AdminUpdatePark.class);
            intent.putExtra("ParkNum",park_num);
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