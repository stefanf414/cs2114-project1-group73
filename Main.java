package mainClass;

import java.util.*;

public class Main
{
    // ~ Fields ................................................................
    private static String partyName;
    private static int amountInParty;
    private static String tableType;
    private static int arrivalTime;

    // ~ Constructors ..........................................................
    public static void main(String[] args)
    {

        Scanner scanner = new Scanner(System.in);
        
        Waitlist waitlist = new Waitlist();

        // setting the name of the party
        System.out.println(
            "Please enter the name of the party (individual making the reservation): ");
        partyName = scanner.nextLine();
        System.out.println("Party Name: " + partyName);

        // checking and setting the amount in the party
        System.out.println("Please enter the amount of people in the party: ");

        while (!scanner.hasNextInt())
        {
            System.out.println("Invalid type, please enter a whole number:");
            scanner.nextLine();
        }

        amountInParty = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Amount in Party: " + amountInParty);

        // checking table type
        System.out.println("Please enter the table type: ");
        String enteredVal = "";
        String[] acceptedVals =
            { "booth", "Booth", "window", "Window", "regular", "Regular" };
        boolean correctTypeFound = false;
        while (!correctTypeFound)
        {
            enteredVal = scanner.nextLine();
            if (Arrays.asList(acceptedVals).contains(enteredVal))
            {
                correctTypeFound = true;
                tableType = enteredVal;
                break;
            }
            System.out.println(
                "Please enter the table type from the following options:"
                    + "[\"booth\", \"Booth\", \"window\", \"Window\", "
                    + "\"regular\", " + "\"Regular\"] : ");
        }
        System.out.println("table type:  " + tableType);

        // checking and setting the time
        while (true)
        {
            System.out.println(
                "Please enter the arrival time in military time,"
                    + " just numbers (0000): ");
            while (!scanner.hasNextInt())
            {
                System.out
                    .println("Invalid type, please enter a whole number:");
                scanner.nextLine();
            }
            int arrivalTimeTest = scanner.nextInt();
            if (Integer.toString(arrivalTimeTest).length() == 4
                && checkArrivalTimeValidity(arrivalTimeTest))
            {
                arrivalTime = arrivalTimeTest;
                break;
            }
            System.out.println(
                "Invalid arrival time. Please enter exactly 4 digits (0000)"
                    + " or check if the time entered is in operating hours:");
        }
        System.out.println("arrival time:  " + arrivalTime);
        
        Party party = new Party(partyName,arrivalTime,amountInParty);
        waitlist.addParty(party);
        System.out.println();
        System.out.println("Party added to waitlist:");
        System.out.println("Party Name: " + party.getName());
        System.out.println("Amount in Party: " + party.getSize());
        System.out.println("Arrival Time: " + party.getArrivalTime());
        System.out.println("Table Type: " + enteredVal);

        System.out.println();
        System.out.println("Current Waitlist:");
        waitlist.displayWaitlist();
        scanner.close();
        

    }


    // ~Public Methods ........................................................
   


    private static boolean checkArrivalTimeValidity(int arrivalTime)
    {
        if (800 <= arrivalTime && arrivalTime < 2200)
        {
            int arrivalMinute = arrivalTime % 100;
            if (arrivalMinute >= 0 && arrivalMinute < 60)
            {
                return true;
            }
        }
        return false;
    }

}
