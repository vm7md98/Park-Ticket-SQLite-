package com.example.parkTicket;

public class ClassBooking {

    private int booking_num;
    private int park_num;
    private int id;
    private int number_of_ticket;
    private double total_price;

    //default constructor
    public ClassBooking() {
    }

    // constructor with parameter
    public ClassBooking(int booking_num, int park_num, int id, int number_of_ticket, double total_price) {
        this.booking_num = booking_num;
        this.park_num = park_num;
        this.id = id;
        this.number_of_ticket = number_of_ticket;
        this.total_price = total_price;
    }

    //setter and getter

    public int getBooking_num() {
        return booking_num;
    }

    public void setBooking_num(int booking_num) {
        this.booking_num = booking_num;
    }

    public int getPark_num() {
        return park_num;
    }

    public void setPark_num(int park_num) {
        this.park_num = park_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber_of_ticket() {
        return number_of_ticket;
    }

    public void setNumber_of_ticket(int number_of_ticket) {
        this.number_of_ticket = number_of_ticket;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    //function to string

    @Override
    public String toString() {
        return "ClassBooking{" +
                "booking_num=" + booking_num +
                ", park_num=" + park_num +
                ", id=" + id +
                ", number_of_ticket=" + number_of_ticket +
                ", total_price=" + total_price +
                '}';
    }
}
