package com.example.parkTicket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminMember extends AppCompatActivity {

    EditText edtid;
    Alter alert = new Alter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_member);

        edtid = (EditText)findViewById(R.id.edt_bknum);
    }



                            /************************* ON CLICK *************************/
                            /**
                             * getAllMembers
                             * findMemberByID
                             * deleteMemberByID
                             * updateMemberByID
                             * Back
                             **/

    // get all members
    public void getAllMembers(View view)
    {

        //send
        Intent intent = new Intent(AdminMember.this,AllMember.class);
        intent.putExtra("showall","showall");
        startActivity(intent);
    }

    // find member by id
    public void findMemberByID(View view)
    {
        String edid = edtid.getText().toString();

        //validation input
        if(edid.isEmpty())
        {
            alert.sendMsg("Error","Fix the errors on the screen",AdminMember.this);
            edtid.setError("Field cannot be empty");
        }
        else
        {
            //send
            Intent intent = new Intent(AdminMember.this,AllMember.class);
            int id = Integer.parseInt(edtid.getText().toString());
            intent.putExtra("findid",id);
            startActivity(intent);
        }

    }


    //delete member
    public void deleteMemberByID(View view)
    {
        String edid = edtid.getText().toString();

        //validation input
        if(edid.isEmpty())
        {
            alert.sendMsg("Error","Fix the errors on the screen",AdminMember.this);
            edtid.setError("Field cannot be empty");
        }
        else
        {
            // 1 - get input
            int id = Integer.parseInt(edtid.getText().toString());

            // 2 - call the database helper class to delete member
            MyDBHelper myhelper = new MyDBHelper(this);
            myhelper.deleteMember(id);

            // 3-show message
            String msg = "Member Deleted";
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }

    }

    //update member
    public void updateMemberByID(View view)
    {
        String edid = edtid.getText().toString();

        //validation input
        if(edid.isEmpty())
        {
            alert.sendMsg("Error","Fix the errors on the screen",AdminMember.this);
            edtid.setError("Field cannot be empty");
        }
        else
        {
            //send
            int id = Integer.parseInt(edtid.getText().toString());
            Intent intent = new Intent(AdminMember.this,AdminUpdateMember.class);
            intent.putExtra("idMemeber",id);
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