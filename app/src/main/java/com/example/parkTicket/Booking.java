package com.example.parkTicket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Booking extends AppCompatActivity {

    EditText edtcount;
    TextView userName,txvtime,txvloc,txvphone,txvprice;
    ImageView img;
    Alter alert = new Alter();

                    /************************* ON CREATE *************************/
                    /**
                     * 1- RECEIVE DATA FROM HOME
                     * 2- FOR EACH PARK CHANGE IMAGE AND GET DATA FROM DB
                     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        edtcount = (EditText)findViewById(R.id.edt_count);
        txvtime = (TextView)findViewById(R.id.txv_time);
        txvloc = (TextView)findViewById(R.id.txv_location);
        txvphone = (TextView)findViewById(R.id.txv_phone);
        txvprice = (TextView)findViewById(R.id.txv_priceofticket);
        img = (ImageView)findViewById(R.id.imageView);

        //underline for location
        txvloc.setPaintFlags(txvloc.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        /** 1 **/

        //receive data from home
        userName = (TextView)findViewById(R.id.txv_username);
        Intent intent = getIntent();
        String username = intent.getStringExtra("UserName");
        userName.setText(username);

        // receive location from home or confirm price
        String location = intent.getStringExtra("Location");

        /** 2 **/

        //PARK 1
        if(location.matches("Sharjah Desert Park"))
        {
            //change image
            img.setImageResource(R.drawable.sharjahdesertpark);

            //call the database helper class to get data of park
            MyDBHelper myhelper = new MyDBHelper(this);
            Park p  = myhelper.getDataParkByPkNum(1);

            if (p != null ) {
                // display on the screen

                txvtime.setText(p.getTime());
                txvloc.setText(p.getLocation_name());
                txvphone.setText(p.getPhone());
                txvprice.setText(""+p.getPrice_of_ticket());

            } else
            {
                // display on the screen
                alert.sendMsg("Erorr","Don't have data",this);
            }
        }

        //PARK 2
        if(location.matches("Sharjah National Park"))
        {
            //change image
            img.setImageResource(R.drawable.sharjahnationalpark);

            //call the database helper class to get data of park
            MyDBHelper myhelper = new MyDBHelper(this);
            Park p  = myhelper.getDataParkByPkNum(2);

            if (p != null ) {
                // display on the screen

                txvtime.setText(p.getTime());
                txvloc.setText(p.getLocation_name());
                txvphone.setText(p.getPhone());
                txvprice.setText(""+p.getPrice_of_ticket());

            } else
            {

                alert.sendMsg("Erorr","Don't have data",this);
            }
        }


        //PARK 3
        if(location.matches("Al Montazah Parks"))
        {
            //change image
            img.setImageResource(R.drawable.almontazahparks);

            //call the database helper class to get data of park
            MyDBHelper myhelper = new MyDBHelper(this);
            Park p  = myhelper.getDataParkByPkNum(3);

            if (p != null ) {
                // display on the screen

                txvtime.setText(p.getTime());
                txvloc.setText(p.getLocation_name());
                txvphone.setText(p.getPhone());
                txvprice.setText(""+p.getPrice_of_ticket());

            } else
            {

                alert.sendMsg("Erorr","Don't have data",this);
            }
        }

        //PARK 4
        if(location.matches("Al Majaz Splash Park"))
        {
            //change image
            img.setImageResource(R.drawable.almajazsplashpark);

            //call the database helper class to get data of park
            MyDBHelper myhelper = new MyDBHelper(this);
            Park p  = myhelper.getDataParkByPkNum(4);

            if (p != null ) {
                // display on the screen

                txvtime.setText(p.getTime());
                txvloc.setText(p.getLocation_name());
                txvphone.setText(p.getPhone());
                txvprice.setText(""+p.getPrice_of_ticket());

            } else
            {

                alert.sendMsg("Erorr","Don't have data",this);
            }
        }



    }

                    /************************* ON CLICK *************************/
                    /**
                     * 1- map() using for txv location
                     * 2- ToBooking()
                     * 3- inccount() for increase number of ticket
                     * 4- deccount() for increase number of ticket
                     * 5- Back() for back to home
                     **/

      /** 1 **/
    public void map(View view)
    {


        //receive location from home
        Intent intent = getIntent();
        String location = intent.getStringExtra("Location");

        //call the database helper class
        MyDBHelper myhelper = new MyDBHelper(this);

        // park 1
        if(location.matches("Sharjah Desert Park"))
        {
            //get data of park
            Park p  = myhelper.getDataParkByPkNum(1);

            String latitude = p.getLatitude();
            String longitude = p.getLongitude();


            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=15"));
            startActivity(google_Map);
        }


        //park 2
        if(location.matches("Sharjah National Park"))
        {
            //get data of park
            Park p  = myhelper.getDataParkByPkNum(2);

            String latitude = p.getLatitude();
            String longitude = p.getLongitude();


            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=15"));
            startActivity(google_Map);
        }


        //park 3
        if(location.matches("Al Montazah Parks"))
        {
            //get data of park
            Park p  = myhelper.getDataParkByPkNum(3);

            String latitude = p.getLatitude();
            String longitude = p.getLongitude();


            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=18"));
            startActivity(google_Map);
        }


        //park 4
        if(location.matches("Al Majaz Splash Park"))
        {
            //get data of park
            Park p  = myhelper.getDataParkByPkNum(4);


            String latitude = p.getLatitude();
            String longitude = p.getLongitude();


            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=15"));
            startActivity(google_Map);
        }

    }



    /** 2 **/
    public void ToBooking(View view)
    {

        String locatoin = txvloc.getText().toString();

        // park 1
        if(locatoin.matches("Sharjah Desert Park"))
        {


            //call the database helper class
            MyDBHelper myhelper = new MyDBHelper(this);

            //get data of park depend of location you choose
            Park p = myhelper.getDataParkByPkNum(1);

            // get data price and number of ticket
            int numOfTic = Integer.parseInt(edtcount.getText().toString());
            String price = txvprice.getText().toString();
            double Ticprice = Double.parseDouble(price);

            //calculation
            double subtotal = Ticprice * numOfTic;
            double tax = (subtotal * p.getTax())/100;
            double totalwithtax = subtotal + tax;
            double discount = (subtotal*p.getDiscount())/100;
            double total = totalwithtax - discount;

            //receive id member from home
            Intent intent1 = getIntent();
            int id = intent1.getIntExtra("id",0);

            //get data of member
            Member m = myhelper.getDataMemberByID(id);


            // create object
            ClassBooking bo = new ClassBooking(0,p.getPark_num(),m.getId(),numOfTic,total);
            Log.d("Booking Info ",  m.toString());


            // insert object to database
            boolean b = myhelper.addBooking(bo);

            String msg = "";

            if (b)
            {
                //receive location from home
                String location = intent1.getStringExtra("Location");

                msg = "new booking was added";
                String username = userName.getText().toString();

                //send
                Intent intent = new Intent(Booking.this,ConfirmPrice.class);
                intent.putExtra("UserName",username);
                intent.putExtra("PriceOfTic",p.getPrice_of_ticket());
                intent.putExtra("NumOfTic",numOfTic);
                intent.putExtra("Discount",p.getDiscount());
                intent.putExtra("Tax",p.getTax());
                intent.putExtra("Total",total);
                intent.putExtra("ParkNum",p.getPark_num());
                intent.putExtra("id",m.getId());
                intent.putExtra("Location",location);
                startActivity(intent);

            } else
            {
                msg = "opps we could not add the booking";
            }

            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();



        }


        // park 2
        if(locatoin.matches("Sharjah National Park"))
        {


            //call the database helper class
            MyDBHelper myhelper = new MyDBHelper(this);

            //get data of park depend of location you choose
            Park p = myhelper.getDataParkByPkNum(2);


            // get data price and number of ticket
            int numOfTic = Integer.parseInt(edtcount.getText().toString());
            String price = txvprice.getText().toString();
            double Ticprice = Double.parseDouble(price);



            //calculation
            double subtotal = Ticprice * numOfTic;
            double tax = (subtotal * p.getTax())/100;
            double totalwithtax = subtotal + tax;
            double discount = (subtotal*p.getDiscount())/100;
            double total = totalwithtax - discount;

            //receive id member
            Intent intent1 = getIntent();
            int id = intent1.getIntExtra("id",0);


            ///get data of member
            Member m = myhelper.getDataMemberByID(id);


            // create object
            ClassBooking bo = new ClassBooking(0,p.getPark_num(),m.getId(),numOfTic,total);
            Log.d("Booking Info ",  m.toString());


            // insert object to database
            boolean b = myhelper.addBooking(bo);

            String msg = "";


            if (b)
            {

                //receive location from home
                String location = intent1.getStringExtra("Location");

                msg = "new booking was added";
                String username = userName.getText().toString();

                //send
                Intent intent = new Intent(Booking.this,ConfirmPrice.class);
                intent.putExtra("UserName",username);
                intent.putExtra("PriceOfTic",p.getPrice_of_ticket());
                intent.putExtra("NumOfTic",numOfTic);
                intent.putExtra("Discount",p.getDiscount());
                intent.putExtra("Tax",p.getTax());
                intent.putExtra("Total",total);
                intent.putExtra("ParkNum",p.getPark_num());
                intent.putExtra("id",m.getId());
                intent.putExtra("Location",location);
                startActivity(intent);

            } else
            {
                msg = "opps we could not add the booking";
            }

            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();



        }


        // park 3
        if(locatoin.matches("Al Montazah Parks"))
        {

            //call the database helper class
            MyDBHelper myhelper = new MyDBHelper(this);

            //get data of park depend of location you choose
            Park p = myhelper.getDataParkByPkNum(3);


            // get data price and number of ticket
            int numOfTic = Integer.parseInt(edtcount.getText().toString());
            String price = txvprice.getText().toString();
            double Ticprice = Double.parseDouble(price);

            //calculation
            double subtotal = Ticprice * numOfTic;
            double tax = (subtotal * p.getTax())/100;
            double totalwithtax = subtotal + tax;
            double discount = (subtotal*p.getDiscount())/100;
            double total = totalwithtax - discount;


            //receive id member
            Intent intent1 = getIntent();
            int id = intent1.getIntExtra("id",0);


            ///get data of member
            Member m = myhelper.getDataMemberByID(id);


            // create object
            ClassBooking bo = new ClassBooking(0,p.getPark_num(),m.getId(),numOfTic,total);
            Log.d("Booking Info ",  m.toString());


            // insert object to database
            boolean b = myhelper.addBooking(bo);

            String msg = "";

            if (b)
            {
                //receive location from home
                String location = intent1.getStringExtra("Location");

                msg = "new booking was added";
                String username = userName.getText().toString();

                //send
                Intent intent = new Intent(Booking.this,ConfirmPrice.class);
                intent.putExtra("UserName",username);
                intent.putExtra("PriceOfTic",p.getPrice_of_ticket());
                intent.putExtra("NumOfTic",numOfTic);
                intent.putExtra("Discount",p.getDiscount());
                intent.putExtra("Tax",p.getTax());
                intent.putExtra("Total",total);
                intent.putExtra("ParkNum",p.getPark_num());
                intent.putExtra("id",m.getId());
                intent.putExtra("Location",location);
                startActivity(intent);

            } else
            {
                msg = "opps we could not add the booking";
            }

            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();



        }


        // park 4
        if(locatoin.matches("Al Majaz Splash Park"))
        {

            //call the database helper class
            MyDBHelper myhelper = new MyDBHelper(this);

            //get data of park depend of location you choose
            Park p = myhelper.getDataParkByPkNum(4);


            // get data price and number of ticket
            int numOfTic = Integer.parseInt(edtcount.getText().toString());
            String price = txvprice.getText().toString();
            double Ticprice = Double.parseDouble(price);

            //calculation
            double subtotal = Ticprice * numOfTic;
            double tax = (subtotal * p.getTax())/100;
            double totalwithtax = subtotal + tax;
            double discount = (subtotal*p.getDiscount())/100;
            double total = totalwithtax - discount;


            //receive id member
            Intent intent1 = getIntent();
            int id = intent1.getIntExtra("id",0);


            ///get data of member
            Member m = myhelper.getDataMemberByID(id);


            // create object
            ClassBooking bo = new ClassBooking(0,p.getPark_num(),m.getId(),numOfTic,total);
            Log.d("Booking Info ",  m.toString());


            // insert object to database
            boolean b = myhelper.addBooking(bo);

            String msg = "";

            if (b)
            {
                //receive location from home
                String location = intent1.getStringExtra("Location");

                msg = "new booking was added";
                String username = userName.getText().toString();

                //send
                Intent intent = new Intent(Booking.this,ConfirmPrice.class);
                intent.putExtra("UserName",username);
                intent.putExtra("PriceOfTic",p.getPrice_of_ticket());
                intent.putExtra("NumOfTic",numOfTic);
                intent.putExtra("Discount",p.getDiscount());
                intent.putExtra("Tax",p.getTax());
                intent.putExtra("Total",total);
                intent.putExtra("ParkNum",p.getPark_num());
                intent.putExtra("id",m.getId());
                intent.putExtra("Location",location);
                startActivity(intent);

            } else
            {
                msg = "opps we could not add the booking";
            }

            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();



        }

    }

    /** 3 **/
    public void inccount(View view)
    {
        int count = Integer.parseInt(edtcount.getText().toString());

        count = count +1;

        edtcount.setText(String.valueOf(count));



    }

    /** 4 **/
    public void deccount(View view)
    {

        int count = Integer.parseInt(edtcount.getText().toString());


        if(count > 1)
        {
            count = count -1;

            edtcount.setText(String.valueOf(count));
        }
        else
        {

            Toast.makeText(this, "should be more than one ", Toast.LENGTH_SHORT).show();
        }



    }


    /** 5 **/
    public void Back(View view)
    {
        //receive id member from home
        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("id",0);

        //send
        String username = userName.getText().toString();
        Intent intent = new Intent(Booking.this,Home.class);
        intent.putExtra("UserName",username);
        intent.putExtra("id",id);
        startActivity(intent);
    }




}