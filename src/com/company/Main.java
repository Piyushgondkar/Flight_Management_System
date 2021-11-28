package com.company;

import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.String;

// A Utility class to hold all available flights fro all cities
class FlightGroup {
    private Flight[] flightList;

    public FlightGroup(String[] cities, String airLine, int capacity) {

        int n = cities.length;
        flightList = new Flight[(n * (n - 1))];

        // Create a complete directional graph from each city
        // to every other city.
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    flightList[k] = new Flight(airLine, cities[i].toLowerCase(), cities[j].toLowerCase(), capacity);
                    k++;
                }
            }
        }
    }// FlightGroup()

    public Flight searchFlight(String departure, String destination) {
        for (int i = 0; i < flightList.length; i++) {
            if (flightList[i].getDeparture().equals(departure) && flightList[i].getDestination().equals(destination)) {
                return flightList[i];
            }
        }
        return null;
    }
}

class TicketGroup {
    private ArrayList<RegularTicket> regularTicketList;
    private ArrayList<TouristTicket> touristTicketList;

    public TicketGroup() {
        regularTicketList = new ArrayList<RegularTicket>();
        touristTicketList = new ArrayList<TouristTicket>();
    }

    public void addRegularTicket(RegularTicket rt) {
        regularTicketList.add(rt);
    }

    public void addTouristTicket(TouristTicket tt) {
        touristTicketList.add(tt);
    }

    public RegularTicket getRegularTicket(int prn) {
        for (RegularTicket rt : regularTicketList) {
            if (rt.getPrnNumber() == prn) {
                return rt;
            }
        }
        return null;
    }

    public TouristTicket getTouristTicket(int prn) {
        for (TouristTicket rt : touristTicketList) {
            if (rt.getPrnNumber() == prn) {
                return rt;
            }
        }
        return null;
    }

}

public class Main {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        TicketGroup allTicketsList = new TicketGroup();
        ArrayList<Passenger> passengerList = new ArrayList<Passenger>();

        String[] cities = { "Pune", "Mumbai", "Delhi" };
        FlightGroup flights = new FlightGroup(cities, "Air India", 5);

        int choice = 1;
        String ans;
        int userPRN;
        // Programs Main Loop.
        while (choice != 0) {
            // reset rr adn tt

            choice = displayMenu("********* Main Menu *********", "Book a Flight", "Update details",
                    "Check Flight Status", "Cancel Flight", "\0", "Exit");

            RegularTicket rt;
            TouristTicket tt;

            switch (choice) {
                case 1:

                    // Ask user if it is already a registered passenger or not
                    while (true) {
                        System.out.print(" > New Passenger? (Y/n)");
                        ans = scan.nextLine().toLowerCase();

                        if (ans == "" || ans.charAt(0) == 'y' || ans.charAt(0) == 'n') {
                            break;
                        }
                    }

                    // create a new passenger object and keep it null for now.
                    Passenger p = null;

                    // if its new registeration, create the new passenger object
                    if (ans == "" || ans.charAt(0) == 'y') {
                        p = createNewPassenger();
                        passengerList.add(p);
                        System.out.println("Registered , Passenger id : " + p.getPassengerId());
                    }

                    // if user was registered before, search the existing passengers
                    // for given passenger ID
                    else {
                        // ask for passenger id from user
                        System.out.println("What is your Passenger id : ");
                        int pid = scan.nextInt();

                        // iterate over the passenger list
                        for (Passenger x : passengerList) {
                            if (x.getPassengerId() == pid) {
                                p = x;
                                break;
                            }
                        }

                        // if passenger not found in list, p reff will be still null
                        // in that case create new passenger
                        if (p == null) {
                            System.out.println(" Sorry, passenger not found. Register new Passenger.");
                            p = createNewPassenger();
                            passengerList.add(p);
                            System.out.println("Registered, Passenger id : " + p.getPassengerId());
                        }
                        // else if Passenger found in list, ack it.
                        else {
                            System.out.println("Passenger found in System.");
                        }
                    }

                    System.out.println(p.getContact());
                    // once details of Passenger are acquired, move to flight details

                    System.out.println("Please Give your journey details next... \n");
                    System.out.println(" > Enter your departure date: ");
                    String deptDate = scan.nextLine();
                    System.out.print(" > Enter your departure time: ");
                    int deptTime = Integer.parseInt(scan.nextLine());
                    System.out.print(" > Enter your departure city: ");
                    String departureCity = scan.nextLine().toLowerCase();
                    System.out.print("\n > Enter your Destination city: ");
                    String destinationCity = scan.nextLine().toLowerCase();
                    System.out.println(departureCity);
                    System.out.println(destinationCity);

                    System.out.println("Searching a flight for you...");
                    Flight f = flights.searchFlight(departureCity, destinationCity);

                    if (f == null) {
                        System.out.println("Sorry, flight not available for those cities.");
                        //break;
                    }

                    // if flight is available, check if the flight is not full
                    if (f.isFlightFull()) {
                        System.out.println("Sorry, flight is completely booked.");
                        //break;
                    }

                    int seatNbr = f.bookSeat();
                    System.out.println("Flight available, seat number " + seatNbr + " booked.");

                    int ticketType = displayMenu("Please select your ticket type ... ", "Regular ticket", "Tourist ticket");

                    switch (ticketType) {
                        case 1:
                            Boolean food, water, snacks;
                            System.out.println("Please input 'y' if you want the following service in Flight...");
                            System.out.print(" Food? ");
                            food = (scan.next().toLowerCase().charAt(0) == 'y');

                            System.out.print(" Water? ");
                            water = (scan.next().toLowerCase().charAt(0) == 'y');

                            System.out.print(" Snacks? ");
                            snacks = (scan.next().toLowerCase().charAt(0) == 'y');

                            rt = new RegularTicket(p, f, seatNbr, deptDate, deptTime, food, water, snacks);
                            allTicketsList.addRegularTicket(rt);

                            System.out.println("\n Your Ticket has been booked. Details...");
                            rt.describeTicket();

                            break;

                        case 2:
                            String hotelName;
                            String[] locations = new String[5];

                            System.out.print("\nPlease enter your hotel name: ");
                            hotelName = scan.nextLine();

                            System.out.print("\nPlease give up to 5 location to visit (-1 to finish input): ");
                            for (int i = 0; i < 5; i++) {
                                String loc = scan.nextLine();

                                if (loc.equals("-1")) {
                                    break;
                                }

                                locations[i] = loc;
                            }

                            tt = new TouristTicket(p, f, seatNbr, deptDate, deptTime, hotelName, locations);
                            allTicketsList.addTouristTicket(tt);

                            System.out.println("\n Your Ticket has been booked. Details...");
                            tt.describeTicket();

                            break;
                    }

                    break;

                case 2:
                    // Ask user for their ticket PRN
                    System.out.print("\n > Enter your ticket PRN: ");
                    userPRN = Integer.parseInt(scan.nextLine());
                    System.out.println(userPRN);
                    // search ticket using the given prn
                    rt = allTicketsList.getRegularTicket(userPRN);
                    tt = allTicketsList.getTouristTicket(userPRN);

                    // check if ticket was found
                    if (rt == null && tt == null) {
                        System.out.println(" Ticket not found. ");
                        break;
                    }

                    // if ticket found move ahead with that ticket type related
                    // update question

                    int opt;
                    // Update menu for the regular ticket type
                    if (rt instanceof RegularTicket) {
                        // if ticket is for regular flight ask for changes in
                        // facilites
                        if(rt.getStatus().equals("Cancelled")) {
                            System.out.println("Cannot update , Ticket Cancelled");
                            break;
                        }
                        opt = displayMenu("  Options for REGULAR ticket User  ", "Add Facilities", "Remove Facilities");

                        String mode;
                        if (opt == 1)
                            mode = "add";
                        else
                            mode = "remove";

                        while (opt != 0) {
                            opt = displayMenu("Select the ones which you want to" + mode, "Food", "Water", "Snacks", "\0",
                                    "Back to main menu");

                            switch (opt) {
                                case 1:
                                    rt.setFood(mode == "add");
                                    break;
                                case 2:
                                    rt.setWater(mode == "add");
                                    break;
                                case 3:
                                    rt.setSnacks(mode == "add");
                                    break;

                                case 0:
                                    break;

                                default:
                                    System.out.println(" Invalid option, try again.");
                            }
                        }

                    } else if (tt instanceof TouristTicket) {
                        if(tt.getStatus().equals("Cancelled")) {
                            System.out.println("Cannot update , Ticket Cancelled");
                            break;
                        }
                        opt = 1;

                        while (opt != 0) {
                            opt = displayMenu(" What do you want to update?", "Hotel Name", "Tourists Locations", "\0",
                                    "Back to Main menu");

                            switch (opt) {
                                case 1:
                                    System.out.println(" > Enter new hotel name: ");
                                    String newHotel = scan.nextLine();
                                    tt.setHotelName(newHotel);
                                    break;

                                case 2:

                                    String[] locations = new String[5];

                                    System.out.print("\nPlease give up to 5 location to visit (-1 to finish input): ");
                                    for (int i = 0; i < 5; i++) {
                                        String loc = scan.nextLine();

                                        if (loc.equals("-1")) {
                                            break;
                                        }

                                        locations[i] = loc;
                                    }
                                    tt.setTouristLocation(locations);
                                    break;

                                case 0:
                                    break;

                                default:
                                    System.out.println("Invalid choice, try again.");
                                    break;
                            }
                        }
                    }

                    break;
                case 3:
                    // Ask user for their ticket PRN
                    System.out.print("\n > Enter your ticket PRN: ");
                    userPRN = Integer.parseInt(scan.nextLine());

                    // search ticket using the given prn
                    rt = allTicketsList.getRegularTicket(userPRN);
                    tt = allTicketsList.getTouristTicket(userPRN);

                    // check if ticket was found
                    if (rt == null && tt == null) {
                        System.out.println(" Ticket not found. ");
                        break;
                    }

                    System.out.println(" Ticket found in system. ");
                    if (rt instanceof RegularTicket) {
                        rt.describeTicket();

                    } else if (tt instanceof TouristTicket) {
                        tt.describeTicket();
                    }

                    break;
                case 4:
                    // Ask user for their ticket PRN
                    System.out.print("\n > Enter your ticket PRN: ");
                    userPRN = Integer.parseInt(scan.nextLine());

                    // search ticket using the given prn
                    rt = allTicketsList.getRegularTicket(userPRN);
                    tt = allTicketsList.getTouristTicket(userPRN);

                    // check if ticket was found
                    if (rt == null && tt == null) {
                        System.out.println(" Ticket not found. ");
                        break;
                    }

                    System.out.println(" Ticket found in system. ");
                    if (rt instanceof RegularTicket) {
                        rt.cancelTicket();

                    } else if (tt instanceof TouristTicket) {
                        tt.cancelTicket();
                    }
                    System.out.println("Ticket cancelled successfully ");
                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n Invalid Option, try again.");
            }
        }

    }

    static int displayMenu(String menuTitle, String... options) {
        System.out.println("\n" + menuTitle);

        int i = 1;
        for (String option : options) {
            if (option == "\0") {
                i = 0;
                continue;
            }

            System.out.println(i + ". " + option);
            i++;
        }

        System.out.print("  > Enter your choice: ");

        return Integer.parseInt(scan.nextLine());
    }

    static Passenger createNewPassenger() {
        String name, city, state, street, email;
        long phn;
        System.out.println("Enter your name : ");
        name = scan.nextLine();
        System.out.println("Enter your city : ");
        city = scan.nextLine();
        System.out.println("Enter your state : ");
        state = scan.nextLine();
        System.out.println("Enter your street : ");
        street = scan.nextLine();
        System.out.println("Enter your emailId : ");
        email = scan.nextLine();
        System.out.println("Enter your phone number : ");
        phn = Long.parseLong(scan.nextLine());

        return new Passenger(name, street, city, state, phn, email);
    }
}