package com.company;
import java.lang.String;
public class Passenger
{
    private String name;
    private static int idCounter;
    private int passengerId;
    Address address;
    Contact contact;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    private static class Address
    {
        private  String city;
        private String street ;
        private String state;

        public Address(String city, String street, String state) {
            this.city = city;
            this.street = street;
            this.state = state;
        }

        @Override
        public String toString() {
            return  "Address : "+ street + ", " + city + ", " + state + ".";

        }

    }
    private static class Contact
    {
        private long phoneNumber;
        private String email;


        public Contact(long phoneNumber, String email)
        {
            this.phoneNumber = phoneNumber;
            this.email = email;
        }


        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "Phone Number : " + phoneNumber +
                    "\nEmail :" + email;
        }
    }
    private Address ad;

    private Contact c;

    Passenger(String name, String street ,String city, String state,long phoneNumber, String email) {

        idCounter++ ;
        passengerId=idCounter ;
        this.name=name;
        ad = new Address(city, street, state);
        c = new Contact(phoneNumber, email);
    }

    public String getContact() {
        return c.toString();
    }

    int getPassengerCount()
    {
        return idCounter;
    }

    public Passenger(String name, int passengerId) {
        this.name = name;
        this.passengerId = passengerId;
    }

    public void describePassenger()
    {
        System.out.println("\nPassenger name : "+ getName() + "\nContact number :" + getContact());

    }
}