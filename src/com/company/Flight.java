package com.company;

public class Flight {
    static private int flightCounter = 100;

    private String airLine;
    private int flightNumber;
    private int capacity;
    private int bookedSeatCounter;
    private String departure;
    private String destination;



    Flight(String airLine, String departure, String destination,  int capacity){
        this.airLine = airLine;
        this.flightNumber = ++flightCounter;
        this.capacity = capacity;
        this.departure = departure;
        this.destination = destination;
    }


    public String getAirLine() {
        return airLine;
    }

    public void setAirLine(String airLine) {
        this.airLine = airLine;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }


    public boolean isFlightFull() {
        return (capacity - bookedSeatCounter) == 0;
    }

    public int bookSeat(){
        return ++bookedSeatCounter;
    }

    public void describeFlight()
    {
        System.out.println("Flight number : " + getFlightNumber() + "\nfrom " + getDeparture()+ "\nto"+ getDestination()   + "\nAirline : " + getAirLine());
    }
}
