package com.example.parkTicket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {


    TextView userName,txvlocation1,txvlocation2,txvlocation3,txvlocation4;
    TextView txvd1,txvd2,txvd3,txvd4;
    Alter alert = new Alter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //receive from login
        userName = (TextView)findViewById(R.id.txv_username);
        Intent intent = getIntent();
        String username = intent.getStringExtra("UserName");
        userName.setText(username);

        //txv location
        txvlocation1 = (TextView)findViewById(R.id.txv_lp_one);
        txvlocation2 = (TextView)findViewById(R.id.txv_lp_two);
        txvlocation3 = (TextView)findViewById(R.id.txv_lp_three);
        txvlocation4 = (TextView)findViewById(R.id.txv_lp_four);

        //underline for location
        txvlocation1.setPaintFlags(txvlocation1.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        txvlocation2.setPaintFlags(txvlocation2.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        txvlocation3.setPaintFlags(txvlocation3.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        txvlocation4.setPaintFlags(txvlocation4.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);


        //txv description
        txvd1 = (TextView)findViewById(R.id.txvd1);
        txvd2 = (TextView)findViewById(R.id.txvd2);
        txvd3 = (TextView)findViewById(R.id.txvd3);
        txvd4 = (TextView)findViewById(R.id.txvd4);

        //underline for description
        txvd1.setPaintFlags(txvd1.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        txvd2.setPaintFlags(txvd2.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        txvd3.setPaintFlags(txvd3.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        txvd4.setPaintFlags(txvd4.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);





    }

                                    /***************** ON CLICK ******************/
                                    /**
                                     * CHECK CARD HAVE 3 ON CLICK :
                                     * booking()
                                     * description()
                                     * map()
                                     * ********
                                     * exitApp3
                                     **/


    // for card 1
    public void booking1(View view)
    {
        //recive id member from home or booking
        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("id",0);

        String username = userName.getText().toString();
        String location = "Sharjah Desert Park";

        //send username and location and id member
        Intent intent = new Intent(Home.this,Booking.class);
        intent.putExtra("UserName",username);
        intent.putExtra("Location",location);
        intent.putExtra("id",id);
        startActivity(intent);



    }


    public void description1(View view)
    {
        //call the database helper class to get data of park
        MyDBHelper myhelper = new MyDBHelper(this);
        Park p  = myhelper.getDataParkByPkNum(1);

        String msg = "";

        if (p != null) {
            // display on the screen as alert
            msg = p.getDescription();

        } else
        {

            msg = "Don't have description";
        }


        alert.sendMsg("Description",msg,Home.this);
    }

    public void map1(View view)
    {

        //call the database helper class to get data of park
        MyDBHelper myhelper = new MyDBHelper(this);
        Park p  = myhelper.getDataParkByPkNum(1);


        String latitude = p.getLatitude();
        String longitude = p.getLongitude();

        if (latitude != null && longitude != null) {
            // display map
            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=15"));
            startActivity(google_Map);

        } else
        {

            alert.sendMsg("Erorr","Don't have latitude and longitude",this);
        }



    }


    // for card 2

    public void booking2(View view)
    {
        //recive id member
        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("id",0);

        String username = userName.getText().toString();
        String location = "Sharjah National Park";

        //send username and location and id member
        Intent intent = new Intent(Home.this,Booking.class);
        intent.putExtra("UserName",username);
        intent.putExtra("Location",location);
        intent.putExtra("id",id);
        startActivity(intent);



    }


    public void description2(View view)
    {
        //call the database helper class to get data of park
        MyDBHelper myhelper = new MyDBHelper(this);
        Park p  = myhelper.getDataParkByPkNum(2);

        String msg = "";

        if (p != null) {
            // display on the screen as alert
            msg = p.getDescription();

        } else
        {

            msg = "Don't have description";
        }


        alert.sendMsg("Description",msg,Home.this);
    }

    public void map2(View view)
    {

        //call the database helper class to get data of park
        MyDBHelper myhelper = new MyDBHelper(this);
        Park p  = myhelper.getDataParkByPkNum(2);


        String latitude = p.getLatitude();
        String longitude = p.getLongitude();

        if (latitude != null && longitude != null) {
            // display map
            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=15"));
            startActivity(google_Map);

        } else
        {

            alert.sendMsg("Erorr","Don't have latitude and longitude",this);
        }



    }


    // for card 3

    public void booking3(View view)
    {

        //recive id member
        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("id",0);

        String username = userName.getText().toString();
        String location = "Al Montazah Parks";

        //send username and location and id member
        Intent intent = new Intent(Home.this,Booking.class);
        intent.putExtra("UserName",username);
        intent.putExtra("Location",location);
        intent.putExtra("id",id);
        startActivity(intent);


    }


    public void description3(View view)
    {
        //call the database helper class to get data of park
        MyDBHelper myhelper = new MyDBHelper(this);
        Park p  = myhelper.getDataParkByPkNum(3);

        String msg = "";

        if (p != null) {
            // display on the screen as alert
            msg = p.getDescription();

        } else
        {

            msg = "Don't have description";
        }


        alert.sendMsg("Description",msg,Home.this);
    }

    public void map3(View view)
    {

        //call the database helper class to get data of park
        MyDBHelper myhelper = new MyDBHelper(this);
        Park p  = myhelper.getDataParkByPkNum(3);


        String latitude = p.getLatitude();
        String longitude = p.getLongitude();

        if (latitude != null && longitude != null) {
            // display map
            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=18"));
            startActivity(google_Map);

        } else
        {

            alert.sendMsg("Erorr","Don't have latitude and longitude",this);
        }



    }

    // for card 4
    public void booking4(View view)
    {

        //recive id member
        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("id",0);


        String username = userName.getText().toString();
        String location = "Al Majaz Splash Park";

        //send username and location and id member
        Intent intent = new Intent(Home.this,Booking.class);
        intent.putExtra("UserName",username);
        intent.putExtra("Location",location);
        intent.putExtra("id",id);
        startActivity(intent);

    }


    public void description4(View view)
    {
        //call the database helper class to get data of park
        MyDBHelper myhelper = new MyDBHelper(this);
        Park p  = myhelper.getDataParkByPkNum(4);

        String msg = "";

        if (p != null) {
            // display on the screen as alert
            msg = p.getDescription();

        } else
        {

            msg = "Don't have description";
        }


        alert.sendMsg("Description",msg,Home.this);
    }

    public void map4(View view)
    {

        //call the database helper class to get data of park
        MyDBHelper myhelper = new MyDBHelper(this);
        Park p  = myhelper.getDataParkByPkNum(4);


        String latitude = p.getLatitude();
        String longitude = p.getLongitude();

        if (latitude != null && longitude != null) {
            // display map
            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=15"));
            startActivity(google_Map);

        } else
        {

            alert.sendMsg("Erorr","Don't have latitude and longitude",this);
        }



    }

    public void exitApp3(View view)
    {
        Intent intent = new Intent(Home.this,Login.class);
        intent.putExtra("LOGOUT","logout");
        startActivity(intent);
        finish();
    }


}