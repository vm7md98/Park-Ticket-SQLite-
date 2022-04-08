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

public class AllMember extends AppCompatActivity {


    List mbrs;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_member);


        listView = findViewById(R.id.lst_bookings);

        //receive from admin member
        Intent intent = getIntent();
        String showall = intent.getStringExtra("showall"); // click button show all member
        int showid = intent.getIntExtra("findid",0); // click button find by id

        if(showall != null)
        {
            displayAll();
        }

        if(showid > 0)
        {
            find(showid);
        }

    }

                                /*********************** FUNCTION ***********************/
                                /**
                                 * displayAll()
                                 *  find(int id)
                                 **/


    public void displayAll()
    {
        MyDBHelper myhelper = new MyDBHelper(this);
        mbrs  = myhelper.getMembers();


        ArrayAdapter<String> members = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mbrs);

        listView.setAdapter(members);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                Member value= (Member) mbrs.get(position);

                // Create an intent an pass the object to the detail screen

                Toast.makeText(getApplicationContext(),value.getUsername(),Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void find(int id)
    {

        MyDBHelper myhelper = new MyDBHelper(this);
        List    m  = myhelper.getMemberByID(id);

        ArrayAdapter<String> members = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, m);

        listView.setAdapter(members);

    }

    //back to home
    public void Back(View view)
    {
        Intent intent = new Intent(this,AdminMember.class);
        startActivity(intent);
    }
}