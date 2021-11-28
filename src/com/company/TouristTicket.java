package com.company;
import javax.tools.DocumentationTool.Location;

public class TouristTicket extends Ticket {
    private String hotelName;
    private String[] touristLocation;

    public TouristTicket(Passenger passenger, Flight flight, int seat, String deptDate, int deptTime, String hotelName,
                         String[] touristLocation) {
        super(passenger, flight, seat, deptDate, deptTime);
        this.hotelName = hotelName;
        this.touristLocation = touristLocation;

    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String[] getTouristLocation() {
        return touristLocation;
    }

    public void setTouristLocation(String[] touristLocation) {
        this.touristLocation = touristLocation;
    }

    public void describeTicket() {
        System.out.println("Your ticket status is :" + getStatus());
        System.out.println("Ticket PRN : " + getPrnNumber() + "\nSeat number : " + getSeat());
        passenger.describePassenger();
        flight.describeFlight();

        System.out.println("Your are staying at hotel " + hotelName);
        System.out.println("Any your visit locations are ... ");
        for (String loc : touristLocation) {
            System.out.println("  > " + loc);
        }
    }

}