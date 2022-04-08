package com.example.parkTicket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminUpdateMember extends AppCompatActivity {

    EditText edtusername,edtemail,edtpassword;
    TextView txv_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_member);

        txv_id = (TextView)findViewById(R.id.txv_idbknum);
        edtusername = (EditText)findViewById(R.id.edtpknum);
        edtemail = (EditText)findViewById(R.id.edtid);
        edtpassword = (EditText)findViewById(R.id.edt_numtic);

        //receive from admin member
        Intent intent = getIntent();
        int id = intent.getIntExtra("idMemeber",0);
        txv_id.setText(String.valueOf(id));



        //call the database helper class to det data of member
        MyDBHelper myhelper = new MyDBHelper(this);
        int Id = Integer.parseInt(txv_id.getText().toString());
        Member    m  = myhelper.getDataMemberByID(Id);

        String msg = "";

        if (m != null) {
            // display on the screen
            msg = "Member Found";
            edtusername.setText(m.getUsername());
            edtemail.setText(m.getEmail());
            edtpassword.setText(""+ m.getPassword());


        } else
        {
            edtusername.setText("");
            edtemail.setText("");
            edtemail.setText("");
            // display member not found
            msg = "Member Does Not Exist";
        }

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();


    }

                        /************************* ON CLICK *************************/
                        /**
                         * updateMember
                         * Back
                         **/
    public void updateMember(View view)
    {
        // 1 - get all inputs
        int id = Integer.parseInt(txv_id.getText().toString());
        String username = edtusername.getText().toString();
        String email = edtemail.getText().toString();
        String password = edtpassword.getText().toString();


        // 2 - call the database helper class to update member

        MyDBHelper myhelper = new MyDBHelper(this);
        myhelper.updateMember(id, username, email,password);

        String msg = "Member updated";

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

        // back to admin member
        Intent intent = new Intent(AdminUpdateMember.this,AdminMember.class);
        startActivity(intent);
    }

    //back to admin member
    public void Back(View view)
    {
        Intent intent = new Intent(this,AdminMember.class);
        startActivity(intent);
    }
}