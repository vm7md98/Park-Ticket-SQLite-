package com.example.parkTicket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

/*****
 * GOAL OF THIS PAGE:
 * INSERT DATA TO TABLES
 */
public class SplashScreen extends AppCompatActivity {

    //variable
    TextView txv;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //variable
        txv = (TextView)findViewById(R.id.txv_welcome);
        animation = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        txv.setAnimation(animation);


                    /************************ INSERT TO TABLES*************************/
                    /*
                                        TABLES:  MEMBERS , PARKS
                     */


        /**
         * BEFORE INSERT TO MEMBER IT'S WILL CHECK IF THERE ARE DATA IN TABLE OR NOT
         * IF THERE ARE DATA IN TABLE , WILL NOT INSERT DATA
         * IF NOT , IT'S WILL INSERT DATA
         */

                                    /******** MEMBERS *******/

        //call the database helper class to check table member
        MyDBHelper myDBHelper = new MyDBHelper(this);
        boolean bM = myDBHelper.checkStateTblMembers(1);

        if(bM)
        {
            Toast.makeText(this, "you have Member Table", Toast.LENGTH_SHORT).show();
        }
        else {
            String[] names = {"ali","fahad","abdullah","muhammad"};
            String[] emails = {"ali@hct.com","fahad@maf.com","abdullah@maf.com","muhammad@maf.com"};
            String[] passwords = {"Alihct1@","Fahad2@","Abdullah3@","Mohammad4@"};


            for (int i = 0; i < names.length; i++) {
                Member m1 = new Member(0,names[i],emails[i],passwords[i]);
                myDBHelper.addMember(m1);
            }


            Toast.makeText(this, "insert Data in Member Table", Toast.LENGTH_SHORT).show();
        }

                                    /******** PARKS *******/


        //check parks table
        boolean bP = myDBHelper.checkStateTblParks(1);

        if(bP)
        {
            Toast.makeText(this, "you have Park Table", Toast.LENGTH_SHORT).show();
        }
        else {

            String[] location_name = {
                    "Sharjah Desert Park",
                    "Sharjah National Park",
                    "Al Montazah Parks",
                    "Al Majaz Splash Park"};
            String[] latitude = {
                    "25.284129099585606",
                    "25.313598962826045",
                    "25.343587569762644",
                    "25.329029871750148"};
            String[] longitude = {
                    "55.69712023806279",
                    "55.537808269319385",
                    "55.38020548465968",
                    "55.38908596136124"};
            String[] time= {
                    "09:00AM – 05:30PM",
                    "10:00AM – 10:00PM",
                    "08:00AM – 10:00PM",
                    "Open 24 hours"};
            double[] price_of_ticket = {
                    15.00,
                    2.00,
                    15.00,
                    30.00};
            int[] discount = {
                    0,
                    0,
                    0,
                    0};
            int[] tax = {
                    5,
                    5,
                    5,
                    5};
            String[] phone= {
                    "06 531 1999",
                    "06 545 8996",
                    "06 511 0555",
                    "06 511 7000"};
            String[] description= {
                    "Family-oriented visitor center with a petting zoo, botanical gardens & natural history museum."
                    ,"Sharjah National Park is a park in Sharjah, the United Arab Emirates. The park is the largest in Sharjah, covering approximately 630,000 m².",
                    "Popular water park offering a variety of rides, slides & pools, plus a cafe & Ferris wheel.",
                    "Majaz Waterfront offers a variety of fun-filled activities, recreational facilities, games, competitions and art workshops specifically tailored for children."};


            for (int i = 0; i < location_name.length; i++) {
                Park p1 = new Park(0,location_name[i],latitude[i],longitude[i],time[i],price_of_ticket[i],discount[i],tax[i],phone[i],description[i]);
                myDBHelper.addPark(p1);
            }


            Toast.makeText(this, "insert Data in Park Table", Toast.LENGTH_SHORT).show();
        }


                          /***************************** ANIMATION ************************/

        // to wait few time then going next page
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 6000  ); //Time for progressbar (Loading)
    }
}