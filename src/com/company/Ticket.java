package com.company;
public class Ticket {
    protected Passenger passenger;
    protected Flight flight;
    private int prnNumber;
    private int seat;
    private String deptDate;
    private int deptTime;
    private String arrivalDate;
    private int arrivalTime;
    private boolean check;
    private static int prnCounter;
    private String status;

    public Ticket(Passenger passenger, Flight flight, int seat, String deptDate, int deptTime) {
        this.passenger = passenger;
        this.flight = flight;
        this.prnNumber = ++prnCounter;
        this.seat = seat;
        this.deptDate = this.arrivalDate = deptDate;
        this.deptTime = deptTime;
        this.arrivalTime = deptTime + 3;
        this.status = "Booked";
    }

    public int getPrnNumber() {
        return prnNumber;
    }

    public void setPrnNumber(int prnNumber) {
        this.prnNumber = prnNumber;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getDeptDate() {
        return deptDate;
    }

    public void setDeptDate(String deptDate) {
        this.deptDate = deptDate;
    }

    public int getDeptTime() {
        return deptTime;
    }

    public void setDeptTime(int deptTime) {
        this.deptTime = deptTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getStatus() {
        return status;
    }

    public void cancelTicket() {
        status = "Cancelled";
    }
}