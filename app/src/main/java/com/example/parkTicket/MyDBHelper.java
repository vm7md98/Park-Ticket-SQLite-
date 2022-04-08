package com.example.parkTicket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {


    // Database variables
    public static final String DB_NAME = "ParkTicDB"; //ParkTicketDB
    public static final int DB_VERSION = 1;

    //Varibales table Members
    public static final String TBL_MEMBERS = "Members";

    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    //Variables table Parks
    public static final String TBL_PARKS = "parks";

    public static final String PARK_NUM = "park_num";
    public static final String LOCATION_NAME = "location_name";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String TIME = "time";
    public static final String PRICE_OF_TICKET = "price_of_ticket";
    public static final String DISCOUNT = "discount";
    public static final String TAX = "tax";
    public static final String PHONE = "phone";
    public static final String DESCRIPTION = "description";

    //Variables table Bokking
    public static final String TBL_BOOKING = "booking";

    public static final String BOOKING_NUM = "booking_num";
    public static final String NUM_OF_TICKET = "num_of_ticket";
    public static final String TOTAL_AMOUNT = "total_amount";






    // Table variables

    public MyDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // table for members
        String tblMembers = " create table " + TBL_MEMBERS + " ( " +
                ID + " Integer primary key autoincrement, "+
                USERNAME + " text," +
                EMAIL + " text , " +
                PASSWORD + " text ) " ;





        // table for parks
        String tblParks = " create table " + TBL_PARKS + " ( " +
                PARK_NUM + " Integer primary key autoincrement,"+
                LOCATION_NAME + " text, " +
                LATITUDE + " text, "+
                LONGITUDE + " text, "+
                TIME + " text, " +
                PRICE_OF_TICKET + " numeric, "+
                DISCOUNT + " integer, "+
                TAX + " integer, " +
                PHONE + " text, "+
                DESCRIPTION +" text )";


        // table for bookings
        String tblBookings = " create table " + TBL_BOOKING + " ( " +
                BOOKING_NUM + " Integer primary key autoincrement,"+
                PARK_NUM + " Integer, " +
                ID + " Integer, "+
                NUM_OF_TICKET + " Integer, "+
                TOTAL_AMOUNT + " numerice) " ;


        //execute
        db.execSQL(tblMembers);
        db.execSQL(tblParks);
        db.execSQL(tblBookings);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*****
     function for members:
     * 1- add member ( addMember(Member m) )
     * 2- get all member ( getMembers() )
     * 3- update member ( updateMember(int id, String username, String email , String password)  )
     * 4- delete member member ( deleteMember(int id) )
     * 5- get member by id return list (  getMemberByID(int id) )
     * 6- get member by email ( getMemberByEmail(String email) )
     * 7- check member by email ( checkMemberByEmail(String email , String password) )
     * 8- check state table members ( checkMemberByEmail(String email , String password) )
     * 9- get member by id return member class ( getDataMemberByID(int id) )
     * 10-check email member ( checkEmailMember(String email) )

    ****/

    // 1-  add member
    public boolean addMember(Member m)
    {
        //get reference to the database
        SQLiteDatabase db = this.getWritableDatabase();

        //Construct the values we want to insert
        ContentValues cv = new ContentValues();
        cv.put(USERNAME , m.getUsername());
        cv.put(EMAIL, m.getEmail());
        cv.put(PASSWORD, m.getPassword());

        //insert to table
        long l = db.insert(TBL_MEMBERS, null, cv);

        //close database
        db.close();

        //check insert data or not
        if ( l > 0 )
            return  true;
        else
            return false;

    }


    // 2- get all members
     public List<Member> getMembers()
     {

         List<Member> members = new ArrayList<Member>();


         //build your query
         String query = "Select * from " + TBL_MEMBERS;

         //get reference to the database
         SQLiteDatabase db = this.getReadableDatabase();

         //execute the query
         Cursor cursor = db.rawQuery(query, null);

         //process the result
         if (cursor.moveToFirst()) {
             do {

                 Member m = new Member();
                 m.setId(cursor.getInt(0));
                 m.setUsername(cursor.getString(1));
                 m.setEmail(cursor.getString(2));
                 m.setPassword(cursor.getString(3));
                 Log.d("Member information == ", m.toString());

                 members.add(m);

             } while (cursor.moveToNext());

         }

         return members;

     }

     // 3- update member
     public void updateMember(int id, String username, String email , String password) {

         //get reference to writable DB
         SQLiteDatabase db = this.getWritableDatabase();

         //create ContentValues to add key "column"/value
         ContentValues cv = new ContentValues();
         cv.put(USERNAME, username);
         cv.put(EMAIL, email);
         cv.put(PASSWORD,password);

         //update the table
         db.update(TBL_MEMBERS, cv,  ID + "=?", new String[] { String.valueOf(id) });
     }

    // 4- delete member
    public void deleteMember(int id) {

        //get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //delete member from the table
        db.delete(TBL_MEMBERS,   ID + "=?", new String[] { String.valueOf(id) });

    }


    // 5- get member by id
    public List<Member> getMemberByID(int id)
    {


        List<Member> members = new ArrayList<Member>();

        //build your query
        String query = "Select * from " + TBL_MEMBERS + " where " + ID + " =  "+ id ;

        //get reference to the database
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query
        Cursor cursor = db.rawQuery(query, null);

        //process the result
        if (cursor.moveToFirst())
        {
            Member m = new Member();
            m.setId(cursor.getInt(0));
            m.setUsername(cursor.getString(1));
            m.setEmail(cursor.getString(2));
            m.setPassword(cursor.getString(3));
            Log.d("Member information == ", m.toString());
            members.add(m);

            return members;
        } else
        {
            return null;
        }




    }

    // 6- get member by email
    public Member getMemberByEmail(String email)
    {

        //build your query
        String query = "Select * from " + TBL_MEMBERS + " where " + EMAIL + " = '"+ email+"'";

        //get reference to the database
        SQLiteDatabase db = this.getReadableDatabase();

        //xecute the query
        Cursor cursor = db.rawQuery(query, null);

        //process the result
        if (cursor.moveToFirst())
        {
            Member m = new Member();
            m.setId(cursor.getInt(0));
            m.setUsername(cursor.getString(1));
            m.setEmail(cursor.getString(2));
            m.setPassword(cursor.getString(3));
            Log.d("Member information == ", m.toString());
            ;

            return m;
        } else
        {
            return null;
        }


    }


    // 7-check info of member in DB by emile and password
    public boolean checkMemberByEmail(String email , String password)
    {

        //build your query
        String query = "Select * from " + TBL_MEMBERS + " where " + EMAIL + " = '"+ email + "' and " + PASSWORD + " = '" + password + "' ";

        //get reference to the database
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query
        Cursor cursor = db.rawQuery(query, null);

        //IF STATEMENT FOR CHECK IF THERE ARE ROW IN TABLE OR NOT
        // IF THERE ARE ROW RETURN TRUE
        // IF NOT , WILL RETURN FALSE AND THAT MEAN TABLE IS EMPTY
        if (cursor.moveToFirst())
        {

            return true;
        } else
        {
            return false;
        }




    }


    // 8-check state table members
    public boolean checkStateTblMembers(int id)
    {

        //get reference to the database
        SQLiteDatabase db = this.getReadableDatabase();

        //build your query
        String query = "Select * from " + TBL_MEMBERS + " where " + ID + " =  "+ id ;


        //execute the query
        Cursor cursor = db.rawQuery(query, null);

        //process the result
        if (cursor.moveToFirst())
        {

            return true;
        } else
        {
            return false;
        }
    }

    // 9- get member by id return member class
    public Member getDataMemberByID(int id)
    {

        //build your query
        String query = "Select * from " + TBL_MEMBERS + " where " + ID + " =  "+ id ;


        //get reference to the database
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query
        Cursor cursor = db.rawQuery(query, null);

        //process the result
        if (cursor.moveToFirst())
        {
            Member m = new Member();
            m.setId(cursor.getInt(0));
            m.setUsername(cursor.getString(1));
            m.setEmail(cursor.getString(2));
            m.setPassword(cursor.getString(3));
            Log.d("Member information == ", m.toString());

            return m;
        } else
        {
            return null;
        }




    }


    // 10-check email member
    public boolean checkEmailMember(String email)
    {

        //build your query

        String query = "Select * from " + TBL_MEMBERS + " where " + EMAIL + " = '"+ email + "'";

        //get reference to the database   //READ OR WIRTE
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query
        Cursor cursor = db.rawQuery(query, null);

        //IF STATEMENT FOR CHECK IF THIS EMAIL IS IN TABLE OR NOT
        // IF THERE ARE RETURN TRUE
        // IF NOT , WILL RETURN FALSE AND THAT MEAN HE CAN REGISTER BY THIS EMAIL
        if (cursor.moveToFirst())
        {
            return true;
        } else
        {
            return false;
        }

    }


    /*******
     * parks:
     * 1- add park ( addPark(Park p) )
     * 2- get all parks ( getParks() )
     * 3- update park ( updatePark(int pknum, String location_name, String time , double price , int discount , int tax, String phone, String description) )
     * 4- delete park ( deletePark(int park_num) )
     * 5- get park by park number return list ( getParkByID(int park_num) )
     * 6- check state table member ( checkStateTblParks(int park_num) )
     * 7- get park by park number return park class ( getDataParkByPkNum(int pknum) )
     */

    // 1-  add park
    public boolean addPark(Park p)
    {
        //get reference to the database
        SQLiteDatabase db = this.getWritableDatabase();

        //Construct the values we want to insert
        ContentValues cv = new ContentValues();
        cv.put(LOCATION_NAME , p.getLocation_name());
        cv.put(LATITUDE , p.getLatitude());
        cv.put(LONGITUDE , p.getLongitude());
        cv.put(TIME, p.getTime());
        cv.put(PRICE_OF_TICKET, p.getPrice_of_ticket());
        cv.put(DISCOUNT, p.getDiscount());
        cv.put(TAX, p.getTax());
        cv.put(PHONE, p.getPhone());
        cv.put(DESCRIPTION, p.getDescription());

        //insert to table park
        long l = db.insert(TBL_PARKS, null, cv);

        //close database
        db.close();

        //check if insert data or not
        if ( l > 0 )
            return  true;
        else
            return false;

    }

    // 2- get all park
    public List<Park> getParks()
    {

        List<Park> parks = new ArrayList<Park>();


        //build your query
        String query = "Select * from " + TBL_PARKS;

        //get reference to the database
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query
        Cursor cursor = db.rawQuery(query, null);

        //process the result
        if (cursor.moveToFirst()) {
            do {

                Park p = new Park();
                p.setPark_num(cursor.getInt(0));
                p.setLocation_name(cursor.getString(1));
                p.setLatitude(cursor.getString(2));
                p.setLongitude(cursor.getString(3));
                p.setTime(cursor.getString(4));
                p.setPrice_of_ticket(cursor.getDouble(5));
                p.setDiscount(cursor.getInt(6));
                p.setTax(cursor.getInt(7));
                p.setPhone(cursor.getString(8));
                p.setDescription(cursor.getString(9));
                Log.d("Park information == ", p.toString());

                parks.add(p);

            } while (cursor.moveToNext());

        }

        return parks;

    }

    // 3- update park
    public void updatePark(int pknum, String location_name, String time , double price , int discount , int tax, String phone, String description) {

        //get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //create ContentValues to add key "column"/value
        ContentValues cv = new ContentValues();
        cv.put(LOCATION_NAME, location_name);
        cv.put(TIME, time);
        cv.put(PRICE_OF_TICKET, price);
        cv.put(DISCOUNT,discount);
        cv.put(TAX,tax);
        cv.put(PHONE,phone);
        cv.put(DESCRIPTION,description);

        // 3. update the table
        db.update(TBL_PARKS, cv,  PARK_NUM + "=?", new String[] { String.valueOf(pknum) });
    }



    // 4- delete park
    public void deletePark(int park_num) {

        //get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //delete from the table
        db.delete(TBL_PARKS,   PARK_NUM + "=?", new String[] { String.valueOf(park_num) });

    }

    // 5- get park by id
    public List<Park> getParkByID(int park_num)
    {


        List<Park> parks = new ArrayList<Park>();

        //build your query
        String query = "Select * from " + TBL_PARKS + " where " + PARK_NUM + " =  "+ park_num ;

        //get reference to the database
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query
        Cursor cursor = db.rawQuery(query, null);

        //process the result
        if (cursor.moveToFirst())
        {
            Park p = new Park();
            p.setPark_num(cursor.getInt(0));
            p.setLocation_name(cursor.getString(1));
            p.setLatitude(cursor.getString(2));
            p.setLongitude(cursor.getString(3));
            p.setTime(cursor.getString(4));
            p.setPrice_of_ticket(cursor.getDouble(5));
            p.setDiscount(cursor.getInt(6));
            p.setTax(cursor.getInt(7));
            p.setPhone(cursor.getString(8));
            p.setDescription(cursor.getString(9));
            Log.d("Park information == ", p.toString());
            parks.add(p);

            return parks;
        } else
        {
            return null;
        }




    }


    // 6-check state table park
    public boolean checkStateTblParks(int park_num)
    {

        //get reference to the database
        SQLiteDatabase db = this.getReadableDatabase();

        //build your query
        String query = "Select * from " + TBL_PARKS + " where " + PARK_NUM + " =  "+ park_num ;


        //execute the query
        Cursor cursor = db.rawQuery(query, null);


        //check table
        if (cursor.moveToFirst())
        {

            return true;
        } else
        {
            return false;
        }
    }


    // 7- get park by pk num return park class
    public Park getDataParkByPkNum(int pknum)
    {

        //build your query
        String query = "Select * from " + TBL_PARKS + " where " + PARK_NUM + " =  "+ pknum ;


        //get reference to the database
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query
        Cursor cursor = db.rawQuery(query, null);


        //process result
        if (cursor.moveToFirst())
        {
            Park p = new Park();
            p.setPark_num(cursor.getInt(0));
            p.setLocation_name(cursor.getString(1));
            p.setLatitude(cursor.getString(2));
            p.setLongitude(cursor.getString(3));
            p.setTime(cursor.getString(4));
            p.setPrice_of_ticket(cursor.getDouble(5));
            p.setDiscount(cursor.getInt(6));
            p.setTax(cursor.getInt(7));
            p.setPhone(cursor.getString(8));
            p.setDescription(cursor.getString(9));
            Log.d("Park information == ", p.toString());

            return p;
        } else
        {
            return null;
        }




    }


    /*******
     * booking:
     * 1- add booking ( addBooking(ClassBooking cb) )
     * 2- get all bookings ( getBookings() )
     * 3- update booking ( updateBooking(int bknum, int pknum , int id, int numTic , double total) )
     * 4- delete booking ( deleteBooking(int booking_num) )
     * 5- get booking by park num and id return ClassBooking class( getDataBooking(int parknum , int id) )
     * 6- get booking data by bk num return classbooking class ( getDataBookingByBkNum(int bknum) )
     */
    // 1-  add booking
    public boolean addBooking(ClassBooking cb)
    {
        //get reference to the database
        SQLiteDatabase db = this.getWritableDatabase();

        //Construct the values we want to insert
        ContentValues cv = new ContentValues();
        cv.put(PARK_NUM , cb.getPark_num());
        cv.put(ID, cb.getId());
        cv.put(NUM_OF_TICKET, cb.getNumber_of_ticket());
        cv.put(TOTAL_AMOUNT, cb.getTotal_price());

        //insert to table booking
        long l = db.insert(TBL_BOOKING, null, cv);

        //close database
        db.close();

        //check if insert data or not
        if ( l > 0 )
            return  true;
        else
            return false;

    }


    // 2- get all booking
    public List<ClassBooking> getBookings()
    {

        List<ClassBooking> bookings = new ArrayList<ClassBooking>();


        //build your query
        String query = "Select * from " + TBL_BOOKING;

        //get reference to the database
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query
        Cursor cursor = db.rawQuery(query, null);

        //process the result
        if (cursor.moveToFirst()) {
            do {

                ClassBooking cb = new ClassBooking();;
                cb.setBooking_num(cursor.getInt(0));
                cb.setPark_num(cursor.getInt(1));
                cb.setId(cursor.getInt(2));
                cb.setNumber_of_ticket(cursor.getInt(3));
                cb.setTotal_price(cursor.getDouble(4));
                Log.d("Booking information == ", cb.toString());

                bookings.add(cb);

            } while (cursor.moveToNext());

        }

        return bookings;

    }

    // 3- update booking
    public void updateBooking(int bknum, int pknum , int id, int numTic , double total)
    {

        //get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //create ContentValues to add key "column"/value
        ContentValues cv = new ContentValues();
        cv.put(PARK_NUM, pknum);
        cv.put(ID, id);
        cv.put(NUM_OF_TICKET,numTic);
        cv.put(TOTAL_AMOUNT,total);

        // 3. update the table
        db.update(TBL_BOOKING, cv,  BOOKING_NUM + "=?", new String[] { String.valueOf(bknum) });
    }



    // 4- delete booking
    public void deleteBooking(int booking_num) {

        //get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //delete from the table
        db.delete(TBL_BOOKING,   BOOKING_NUM + "=?", new String[] { String.valueOf(booking_num) });

    }

    // 4- get park by id
    public List<ClassBooking> getBookingByNum(int booking_num)
    {


        List<ClassBooking> bookings = new ArrayList<ClassBooking>();

        //build your query
        String query = "Select * from " + TBL_BOOKING + " where " + BOOKING_NUM + " =  "+ booking_num ;

        //get reference to the database
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query
        Cursor cursor = db.rawQuery(query, null);

        //process the result
        if (cursor.moveToFirst())
        {
            ClassBooking cb = new ClassBooking();
            cb.setBooking_num(cursor.getInt(0));
            cb.setPark_num(cursor.getInt(1));
            cb.setId(cursor.getInt(2));
            cb.setNumber_of_ticket(cursor.getInt(3));
            cb.setTotal_price(cursor.getDouble(4));
            Log.d("Booking information == ", cb.toString());
            bookings.add(cb);

            return bookings;
        } else
        {
            return null;
        }




    }

    // 5- get booking data by pk num and id return classbooking class
    public ClassBooking getDataBooking(int parknum , int id)
    {

        //build your query
        String query = "Select * from " + TBL_BOOKING + " where " + PARK_NUM + " =  "+ parknum + " and " + ID + " = " + id;


        //get reference to the database
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst())
        {
            ClassBooking cb = new ClassBooking();
            cb.setBooking_num(cursor.getInt(0));
            cb.setPark_num(cursor.getInt(1));
            cb.setId(cursor.getInt(2));
            cb.setNumber_of_ticket(cursor.getInt(3));
            cb.setTotal_price(cursor.getDouble(4));

            Log.d("Booking information == ", cb.toString());

            return cb;
        } else
        {
            return null;
        }




    }


    // 6- get booking data by bk num return classbooking class
    public ClassBooking getDataBookingByBkNum(int bknum)
    {

        // 1 - build your query
        String query = "Select * from " + TBL_BOOKING + " where " + BOOKING_NUM + " =  "+ bknum;


        // 2 - get reference to the database
        SQLiteDatabase db = this.getReadableDatabase();

        // 3 -  execute the query
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst())
        {
            ClassBooking cb = new ClassBooking();
            cb.setBooking_num(cursor.getInt(0));
            cb.setPark_num(cursor.getInt(1));
            cb.setId(cursor.getInt(2));
            cb.setNumber_of_ticket(cursor.getInt(3));
            cb.setTotal_price(cursor.getDouble(4));

            Log.d("Booking information == ", cb.toString());

            return cb;
        } else
        {
            return null;
        }




    }



}

