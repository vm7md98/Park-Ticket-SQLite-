package com.example.parkTicket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class AllBooking extends AppCompatActivity {

    List bks;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_booking);

        listView = findViewById(R.id.lst_bookings);
        Intent intent = getIntent();


        String showall = intent.getStringExtra("showall");
        int showbknum = intent.getIntExtra("findbknum",0);

        if(showall != null)
        {
            displayAll();
        }

        if(showbknum > 0 )
        {
            find(showbknum);
        }
    }

    public void displayAll()
    {
        MyDBHelper myhelper = new MyDBHelper(this);
        bks  = myhelper.getBookings();

        //Toast.makeText(this, mbrs.toString(), Toast.LENGTH_LONG).show();

        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        // android.R.layout.simple_list_item_1, android.R.id.text1, m);

        ArrayAdapter<String> bookings = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bks);

        listView.setAdapter(bookings);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                ClassBooking value= (ClassBooking) bks.get(position);

                // Create an intent an pass the object to the detail screen

                Toast.makeText(getApplicationContext(),value.getBooking_num(),Toast.LENGTH_SHORT).show();

            }
        });

    }

    // find data of park
    public void find(int bknum)
    {

        MyDBHelper myhelper = new MyDBHelper(this);
        List    b  = myhelper.getBookingByNum(bknum);

        ArrayAdapter<String> bookings = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, b);

        listView.setAdapter(bookings);

    }

    //back to home
    public void Back(View view)
    {
        Intent intent = new Intent(this,AdminBooking.class);
        startActivity(intent);
    }
}