package com.example.parkTicket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ConfirmPrice extends AppCompatActivity {

    TextView userName,txvprice,txvnumtic,txvdiscount,txvtax,txvtotal;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_price);

        userName = (TextView)findViewById(R.id.txv_username);
        txvprice = (TextView)findViewById(R.id.txv_priceticket);
        txvnumtic = (TextView)findViewById(R.id.txv_numtic);
        txvdiscount = (TextView)findViewById(R.id.txv_discount);
        txvtax = (TextView)findViewById(R.id.txv_tax);
        txvtotal = (TextView)findViewById(R.id.txv_total);
        img = (ImageView)findViewById(R.id.imageView);


        // receive data from booking
        Intent intent = getIntent();
        String username = intent.getStringExtra("UserName");
        String location = intent.getStringExtra("Location");
        double price = intent.getDoubleExtra("PriceOfTic",0);
        int numtic = intent.getIntExtra("NumOfTic",0);
        int discount = intent.getIntExtra("Discount",0);
        int tax = intent.getIntExtra("Tax",0);
        double total = intent.getDoubleExtra("Total",0);

        // put data to textView which you receive from booking
        userName.setText(username);
        txvprice.setText(String.valueOf(price));
        txvnumtic.setText(String.valueOf(numtic));
        txvdiscount.setText(String.valueOf(discount));
        txvtax.setText(String.valueOf(tax));
        txvtotal.setText(String.valueOf(total));



        // change image depend on variable location

        //park 1
        if(location.matches("Sharjah Desert Park"))
        {
            img.setImageResource(R.drawable.sharjahdesertpark);
        }

        //park 2
        if(location.matches("Sharjah National Park"))
        {
            img.setImageResource(R.drawable.sharjahnationalpark);
        }

        //park 3
        if(location.matches("Al Montazah Parks"))
        {
            img.setImageResource(R.drawable.almontazahparks);
        }

        //park 4
        if(location.matches("Al Majaz Splash Park"))
        {
            img.setImageResource(R.drawable.almajazsplashpark);
        }


    }

                        /****************** ON CLICK ****************/
                        /**
                         * confrim
                         * back
                         **/

    public void confirm(View view)
    {
        String username = userName.getText().toString();

        //receive from booking
        Intent intent = getIntent();
        int pknum = intent.getIntExtra("ParkNum",0);
        int id = intent.getIntExtra("id",0);

        // send
        Intent intent1 = new Intent(ConfirmPrice.this,Congratulation.class);
        intent1.putExtra("UserName",username);
        intent1.putExtra("ParkNum1",pknum);
        intent1.putExtra("id",id);
        startActivity(intent1);
    }

    //back
    public void Back(View view)
    {
        String username = userName.getText().toString();

        //receive from booking
        Intent intent1 = getIntent();
        String location = intent1.getStringExtra("Location");
        int id = intent1.getIntExtra("id",0);

        //send
        Intent intent = new Intent(ConfirmPrice.this,Booking.class);
        intent.putExtra("UserName",username);
        intent.putExtra("Location",location);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}