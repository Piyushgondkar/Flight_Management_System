package com.company;
public class RegularTicket extends Ticket {
    private boolean food;
    private boolean water;
    private boolean snacks;

    public RegularTicket(Passenger passenger, Flight flight, int seat, String deptDate, int deptTime, boolean food,
                         boolean water, boolean snacks) {
        super(passenger, flight, seat, deptDate, deptTime);
        this.food = food;
        this.water = water;
        this.snacks = snacks;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isSnacks() {
        return snacks;
    }

    public void setSnacks(boolean snacks) {
        this.snacks = snacks;
    }

    public void describeTicket() {
        System.out.println("Your ticket status is :" + getStatus());
        System.out.println("Ticket PRN : " + getPrnNumber() + "\nSeat number : " + getSeat());
        passenger.describePassenger();
        flight.describeFlight();

        if (food)
            System.out.println("Food facility opted ");
        if (water)
            System.out.println("Water facility opted ");
        if (snacks)
            System.out.println("Snacks facility opted ");

    }
}