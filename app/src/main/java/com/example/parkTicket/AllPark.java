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

public class AllPark extends AppCompatActivity {

    List pks;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_park);

        listView = findViewById(R.id.lst_bookings);
        Intent intent = getIntent();

        String showall = intent.getStringExtra("showall");
        int showid = intent.getIntExtra("findid",0);

        if(showall != null)
        {
            displayAll();
        }

        if(showid > 0)
        {
            find(showid);
        }
    }


    public void displayAll()
    {
        MyDBHelper myhelper = new MyDBHelper(this);
        pks  = myhelper.getParks();

        //Toast.makeText(this, mbrs.toString(), Toast.LENGTH_LONG).show();

        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        // android.R.layout.simple_list_item_1, android.R.id.text1, m);

        ArrayAdapter<String> parks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pks);

        listView.setAdapter(parks);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                Park value= (Park) pks.get(position);

                // Create an intent an pass the object to the detail screen

                Toast.makeText(getApplicationContext(),value.getLocation_name(),Toast.LENGTH_SHORT).show();

            }
        });

    }

    // find data of park
    public void find(int id)
    {

        MyDBHelper myhelper = new MyDBHelper(this);
        List    p  = myhelper.getParkByID(id);

        ArrayAdapter<String> parks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, p);

        listView.setAdapter(parks);

    }

    //back to home
    public void Back(View view)
    {
        Intent intent = new Intent(this,AdminPark.class);
        startActivity(intent);
    }
}