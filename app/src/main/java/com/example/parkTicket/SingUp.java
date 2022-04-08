package com.example.parkTicket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class  SingUp extends AppCompatActivity {

    // Declear variable
    EditText edtusername , edtemail , edtpassword;
    Button btnsignup;
    Alter alert = new Alter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        edtusername = (EditText)findViewById(R.id.edt_username);
        edtemail = (EditText)findViewById(R.id.edt_email);
        edtpassword = (EditText)findViewById(R.id.edt_password);
        btnsignup = (Button)findViewById(R.id.btn_signup);

    }


                    /***************************** VLIDATION ***************************/

    private Boolean validateEmail() {
        String val = edtemail.getText().toString();
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})+$";
        if (val.isEmpty()) {
            edtemail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            edtemail.setError("Invalid email address");
            return false;
        } else {
            edtemail.setError(null);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = edtusername.getText().toString();
        String noWhiteSpace = "\\A\\w{4,10}\\z";
        if (val.isEmpty()) {
            edtusername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 10) {
            edtusername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            edtusername.setError("White Spaces are not allowed");
            return false;
        } else {
            edtusername.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String PasswordPattern =
                ("^" +
                        "(?=.*[0-9])" +            //at least 1 digit
                        "(?=.*[a-z])" +           //at least 1 lower case letter
                        "(?=.*[A-Z])" +           //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +       //any letter
                        "(?=.*[@#$%^&+=])" +     //at least 1 special character
                        "(?=\\S+$)" +           //no white spaces
                        ".{4,}" +               //at least 4 characters
                        "$");

        String val = edtpassword.getText().toString().trim();
        if (val.isEmpty()) {
            edtpassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(PasswordPattern)) {
            edtpassword.setError("Password too weak");
            return false;
        } else {
            edtpassword.setError(null);
            return true;
        }
    }


                        /********************** ON CLICK *******************/
                        /**
                            * signup
                            * signin
                         **/


    public void signup(View view)
    {

        // Validate the input

        if ( (!validateUsername()) | (!validateEmail()) | (!validatePassword())) {


            alert.sendMsg("Error","Fix the errors on the screen",SingUp.this);
        }
        else
        {


            // get all inputs

            String username = edtusername.getText().toString();
            String email = edtemail.getText().toString();
            String password = edtpassword.getText().toString();

            //call the database helper class check email if there are same email in member table or not
            MyDBHelper myhelper = new MyDBHelper(this);
            boolean bck = myhelper.checkEmailMember(email);

            if(bck)
            {
                alert.sendMsg("Error","Already registered by this Email",this);
            }
            else
            {
                //Create the object
                Member m = new Member(0,username,email,password);
                Log.d("Member Info ",  m.toString());

                // insert into member table
                boolean b = myhelper.addMember(m);

                String msg = "";

                if (b)
                {
                    msg = "Hooray a new member was added";

                    edtusername.setText("");
                    edtemail.setText("");
                    edtpassword.setText("");

                } else
                {
                    msg = "opps we could not add the member";
                }

                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            }




        }


    }


    public void signin(View view)
    {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

}