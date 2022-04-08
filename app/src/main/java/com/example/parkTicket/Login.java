package com.example.parkTicket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    // Declare variable
    EditText email , password;
    Button btnlogin;
    CheckBox chbremember;
    Alter alert = new Alter();

                    /*************************** ON CREATE *******************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        email = (EditText)findViewById(R.id.edt_email);
        password = (EditText)findViewById(R.id.edt_password);
        btnlogin = (Button)findViewById(R.id.btn_login);
        chbremember = (CheckBox)findViewById(R.id.chb_remember);

        // receive from home
        Intent intent1 = getIntent();
        String logout = intent1.getStringExtra("LOGOUT");


                /*************************** SHARE PREFERENCES *******************/



        if(logout == null)
        {

            // Get reference
            SharedPreferences mypref = getSharedPreferences("MyPref" , Context.MODE_PRIVATE);
            String usrEmail = mypref.getString("UserEmail",null);

            if(usrEmail != null)
            {
                MyDBHelper myhelper = new MyDBHelper(this);
                Member m  = myhelper.getMemberByEmail(usrEmail);

                //send to home
                Intent intent = new Intent(Login.this,Home.class);
                intent.putExtra("UserName",m.getUsername());
                intent.putExtra("id",m.getId());
                startActivity(intent);


                Toast.makeText(this, "Welcome to Park Ticket!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                setContentView(R.layout.activity_login);
            }

        }
        else
        {
            setContentView(R.layout.activity_login);


        }

    }



                 /*************************** ON CLICK ***********************/
                 /*
                    * Login
                    * Signup
                  */

    public void login(View view) {

        //get input
        email = (EditText)findViewById(R.id.edt_email);
        password = (EditText)findViewById(R.id.edt_password);
        String userEmails = email.getText().toString();
        String userPassw = password.getText().toString();

        //check input if admin or user
        if(userEmails.matches("admin") && userPassw.matches("admin"))
        {
            Toast.makeText(this, "Welcome Admin!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this,AdminHome.class);
            startActivity(intent);
        }
        else
        {
            //validation of input
            if ((!validateEmail()) | (!validatePassword())) {


                alert.sendMsg("Error", "Fix the errors on the screen", Login.this);
            }
            else
            {

                chbremember = (CheckBox)findViewById(R.id.chb_remember);
                //get input
                String usrEmail = email.getText().toString();
                String usrPass = password.getText().toString();
                boolean boolIsChecked = chbremember.isChecked();



                // call the database helper class to check email and password in correct or incorrect
                MyDBHelper myhelper = new MyDBHelper(this);
                boolean    state  = myhelper.checkMemberByEmail(usrEmail , usrPass);

                String msg = "";

                if (state) {

                    //get data of member by email
                    Member m  = myhelper.getMemberByEmail(usrEmail);

                    msg = "Welcome to Park Ticket!";
                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

                    // SEND
                    Intent intent = new Intent(this,Home.class);
                    intent.putExtra("UserName",m.getUsername());
                    intent.putExtra("id",m.getId());
                    startActivity(intent);
                } else
                {
                    // display member not found
                    msg = "Member Does Not Exist";
                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

                    alert.sendMsg("Error", "Incorrect email or password", Login.this);
                    email.setError("Please, make sure your email");
                    password.setError("Please, make sure your password");
                }




                // if click check  to remember email and password , and in next time don't need input email and passwor directly going to home

                if(boolIsChecked)
                {

                    SharedPreferences mypref = getSharedPreferences("MyPref" , Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mypref.edit();

                    editor.putString("UserEmail",usrEmail);
                    editor.putString("UserPass",usrPass);
                    editor.commit();
                }

            }

        }


    }




    public void toSignup(View view)
    {
        Intent intent = new Intent(Login.this,SingUp.class);
        startActivity(intent);
    }



                        /*************************** VALIDATION *******************/



    private Boolean validateEmail() {
        String val = email.getText().toString();
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})+$";
        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);
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

        String val = password.getText().toString().trim();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(PasswordPattern)) {
            password.setError("Please, make sure your password , password too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }







}