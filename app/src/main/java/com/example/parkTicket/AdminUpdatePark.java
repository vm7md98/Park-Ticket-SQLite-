package com.example.parkTicket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminUpdatePark extends AppCompatActivity {
    //latitude
    //longitude

    EditText edtlocation,edtlatitude,edtlongitude,edttime,edtprice,edtdiscount,edttax,edtphone,edtdes;
    TextView txvpknum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_park);

        edtlocation = (EditText)findViewById(R.id.edtpknum);
        edtlatitude = (EditText)findViewById(R.id.edtLatitude);
        edtlongitude = (EditText)findViewById(R.id.edtLongitude);
        edttime = (EditText)findViewById(R.id.edtid);
        edtprice = (EditText)findViewById(R.id.edt_numtic);
        edtdiscount = (EditText)findViewById(R.id.edtdiscount);
        edttax = (EditText)findViewById(R.id.edttax);
        edtphone = (EditText)findViewById(R.id.edtphone);
        edtdes = (EditText)findViewById(R.id.edtdescription);
        txvpknum = (TextView)findViewById(R.id.txv_idbknum);

        Intent intent = getIntent();
        int pknum = intent.getIntExtra("ParkNum",0);
        txvpknum.setText(String.valueOf(pknum));



        //get all data for update
        MyDBHelper myhelper = new MyDBHelper(this);
        int Pknum = Integer.parseInt(txvpknum.getText().toString());
        Park p  = myhelper.getDataParkByPkNum(Pknum);

        String msg = "";

        if (p != null) {
            // display on the screen
            msg = "Park Found";
            edtlocation.setText(p.getLocation_name());
            edtlatitude.setText(p.getLatitude());
            edtlongitude.setText(p.getLongitude());
            edttime.setText(p.getTime());
            edtprice.setText(""+ p.getPrice_of_ticket());
            edtdiscount.setText(""+ p.getDiscount());
            edttax.setText(""+ p.getTax());
            edtphone.setText(p.getPhone());
            edtdes.setText(p.getDescription());


        } else
        {
            edtlocation.setText("");
            edtlatitude.setText("");
            edtlongitude.setText("");
            edttime.setText("");
            edtprice.setText("");
            edtdiscount.setText("");
            edttax.setText("");
            edtphone.setText("");
            edtdes.setText("");
            // display parl not found
            msg = "Park Does Not Exist";
        }

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void updatePark(View view)
    {
        // 1 - get all inputs
        int pknum = Integer.parseInt(txvpknum.getText().toString());
        String location_name = edtlocation.getText().toString();
        String time = edttime.getText().toString();
        double price = Double.parseDouble(edtprice.getText().toString());
        int discount = Integer.parseInt(edtdiscount.getText().toString());
        int tax = Integer.parseInt(edttax.getText().toString());
        String phone = edtphone.getText().toString();
        String description = edtdes.getText().toString();


        // 2 - call the database helper class to update

        MyDBHelper myhelper = new MyDBHelper(this);
        myhelper.updatePark(pknum,location_name,time,price,discount,tax,phone,description);

        String msg = "Park updated";

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(AdminUpdatePark.this,AdminPark.class);
        startActivity(intent);
    }

    //back to home
    public void Back(View view)
    {
        Intent intent = new Intent(this,AdminPark.class);
        startActivity(intent);
    }

}