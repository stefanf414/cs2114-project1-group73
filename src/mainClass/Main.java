package mainClass;

import java.util.*;

/**
 * Main application class handling continuous interactive menu operations,
 * separate status trackers for Booths, Window, and Regular tables,
 * table clearing functionality, party removal, and dynamic wait times.
 *
 * @author Megha Dabbeeru
 * @author Stefan Fernandez
 * @version 2026.09.21
 */
public class Main {
    // ~ Constants .............................................................

    /** Maximum allowed seating capacity per individual party. */
    private static final int MAX_PARTY_SIZE = 8;

    /** Total number of booth tables in the layout. */
    private static final int MAX_BOOTHS = 5;

    /** Total number of window tables in the layout. */
    private static final int MAX_WINDOW_TABLES = 5;

    /** Total number of standard/regular tables in the layout. */
    private static final int MAX_REGULAR_TABLES = 5;

    // ~ Main Method ...........................................................

    /**
     * Program entry point. Executes the system menu loop and manages options.
     *
     * @param args
     *            command line arguments (unused)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Waitlist waitlist = new Waitlist();
        TableManager tableManager = initializeRestaurantLayout();

        boolean running = true;

        while (running) {
            System.out.println("\n===== RESTAURANT RESERVATION SYSTEM =====");
            displayTableStatus(tableManager);
            System.out.println("1. Add a Party to Waitlist");
            System.out.println("2. View Waitlist");
            System.out.println("3. Seat Next Party");
            System.out.println("4. Clear/Free an Occupied Table");
            System.out.println("5. Remove/Cancel Party from Waitlist");
            System.out.println("6. Check Party Wait Time");
            System.out.println("7. Exit");
            System.out.print("Select an option (1-7): ");

            if (!scanner.hasNextInt()) {
                System.out.println(
                    "Invalid choice. Please enter a number between 1 and 7.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear scanner buffer

            switch (choice) {
                case 1:
                    addPartyPrompt(scanner, waitlist, tableManager);
                    break;
                case 2:
                    System.out.println("\nCurrent Waitlist:");
                    waitlist.displayWaitlist();
                    break;
                case 3:
                    seatPartyPrompt(waitlist, tableManager);
                    break;
                case 4:
                    clearTablePrompt(scanner, tableManager);
                    break;
                case 5:
                    removePartyPrompt(scanner, waitlist);
                    break;
                case 6:
                    checkWaitTimePrompt(scanner, waitlist, tableManager);
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println(
                        "Invalid option. Please choose between 1 and 7.");
            }
        }

        scanner.close();
    }

    // ~ Private Helper Methods ................................................


    /**
     * Displays individual open/total trackers for Booths, Window, and Regular
     * tables.
     *
     * @param tableManager
     *            manager tracking table availability
     */
    private static void displayTableStatus(TableManager tableManager) {
        int openBooths = tableManager.getAvailableCountByType("booth");
        int totalBooths = tableManager.getTotalCountByType("booth");

        int openWindow = tableManager.getAvailableCountByType("window");
        int totalWindow = tableManager.getTotalCountByType("window");

        int openRegular = tableManager.getAvailableCountByType("regular");
        int totalRegular = tableManager.getTotalCountByType("regular");

        System.out.println("--- Table Availability ---");
        System.out.println("Booths:  " + openBooths + "/" + totalBooths
            + " open");
        System.out.println("Window:  " + openWindow + "/" + totalWindow
            + " open");
        System.out.println("Regular: " + openRegular + "/" + totalRegular
            + " open");
        System.out.println("Total:   " + tableManager.getAvailableTableCount()
            + "/" + tableManager.getTotalTableCount() + " open");
    }


    /**
     * Prompts the user to clear/free an occupied table either by table ID or
     * table type.
     *
     * @param scanner
     *            active Scanner object for console input
     * @param tableManager
     *            manager tracking table availability
     */
    private static void clearTablePrompt(
        Scanner scanner,
        TableManager tableManager) {
        System.out.println("\nClear Table Options:");
        System.out.println("1. Clear by Table Type (booth, window, regular)");
        System.out.println("2. Clear by Specific Table ID");
        System.out.print("Select choice (1-2): ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid choice.");
            scanner.nextLine();
            return;
        }

        int subChoice = scanner.nextInt();
        scanner.nextLine();

        if (subChoice == 1) {
            System.out.print(
                "Enter table type to clear (booth, window, regular): ");
            String type = scanner.nextLine().trim().toLowerCase();

            boolean cleared = tableManager.clearFirstOccupiedByType(type);
            if (cleared) {
                System.out.println("Success: A " + type
                    + " table has been cleared and is now open!");
            }
            else {
                System.out.println("No currently occupied " + type
                    + " tables found to clear.");
            }
        }
        else if (subChoice == 2) {
            System.out.print("Enter Table ID number to clear: ");
            if (scanner.hasNextInt()) {
                int tableId = scanner.nextInt();
                scanner.nextLine();

                boolean cleared = tableManager.clearTableById(tableId);
                if (cleared) {
                    System.out.println("Success: Table #" + tableId
                        + " has been cleared and is now open!");
                }
                else {
                    System.out.println("Table #" + tableId
                        + " was either already open or not found.");
                }
            }
            else {
                System.out.println("Invalid Table ID.");
                scanner.nextLine();
            }
        }
        else {
            System.out.println("Invalid selection.");
        }
    }


    /**
     * Prompts the user to enter party details, party size, table preference,
     * and arrival time,
     * then adds them to the waitlist and reports their dynamic wait time based
     * on table availability.
     *
     * @param scanner
     *            active Scanner object for console input
     * @param waitlist
     *            waitlist instance to add the party to
     * @param tableManager
     *            table manager tracking availability
     */
    private static void addPartyPrompt(
        Scanner scanner,
        Waitlist waitlist,
        TableManager tableManager) {
        System.out.print("Please enter the name of the party: ");
        String partyName = scanner.nextLine().trim();

        int amountInParty = 0;
        while (true) {
            System.out.print("Please enter amount of people in party (1-"
                + MAX_PARTY_SIZE + "): ");
            if (scanner.hasNextInt()) {
                amountInParty = scanner.nextInt();
                scanner.nextLine();

                if (amountInParty > 0 && amountInParty <= MAX_PARTY_SIZE) {
                    break;
                }
                System.out.println(
                    "Party size exceeds maximum seat capacity of "
                        + MAX_PARTY_SIZE + ".");
            }
            else {
                System.out.println(
                    "Invalid input. Please enter a whole number.");
                scanner.nextLine();
            }
        }

        String tableType = "";
        List<String> validTypes = Arrays.asList("booth", "window", "regular");
        while (true) {
            System.out.print(
                "Please enter table type preference (booth, window, regular): ");
            tableType = scanner.nextLine().trim().toLowerCase();

            if (validTypes.contains(tableType)) {
                break;
            }
            System.out.println(
                "Invalid type. Choose from: booth, window, or regular.");
        }

        int arrivalTime = 0;
        while (true) {
            System.out.print(
                "Please enter arrival time in military format (e.g., 0800-2159): ");
            if (scanner.hasNextInt()) {
                int inputTime = scanner.nextInt();
                scanner.nextLine();

                if (checkArrivalTimeValidity(inputTime)) {
                    arrivalTime = inputTime;
                    break;
                }
            }
            else {
                scanner.nextLine();
            }
            System.out.println(
                "Invalid arrival time. Must be operating hours (0800 to 2159).");
        }

        Party party = new Party(partyName, arrivalTime, amountInParty,
            tableType);
        waitlist.addParty(party);

        int calculatedWaitTime = waitlist.getWaitTime(party, tableManager);

        System.out.println("\nSuccess: Party added to waitlist!");
        if (calculatedWaitTime == 0) {
            System.out.println("Estimated wait time for a " + tableType
                + ": 0 minutes (Table currently available!).");
        }
        else {
            System.out.println("Estimated wait time for a " + tableType + ": "
                + calculatedWaitTime + " minutes.");
        }
    }


    /**
     * Seats the next party on the waitlist if an appropriate table matching
     * their size
     * and preference is currently available.
     *
     * @param waitlist
     *            waitlist instance managing active parties
     * @param tableManager
     *            table manager instance tracking restaurant tables
     */
    private static void seatPartyPrompt(
        Waitlist waitlist,
        TableManager tableManager) {
        if (waitlist.isEmpty()) {
            System.out.println("No parties currently on the waitlist.");
            return;
        }

        Party next = waitlist.seatNextParty();

        if (tableManager.assignTable(next.getSize(), next.getTableType())) {
            System.out.println("Seated party '" + next.getName() + "' at a "
                + next.getTableType() + " table (Size: " + next.getSize()
                + ").");
        }
        else {
            System.out.println("No available " + next.getTableType()
                + " table for party size " + next.getSize()
                + ". Re-adding to waitlist.");
            waitlist.addParty(next);
        }
    }


    /**
     * Prompts user for a party name to remove directly from the waitlist
     * without seating them.
     *
     * @param scanner
     *            active Scanner object for console input
     * @param waitlist
     *            waitlist instance to remove party from
     */
    private static void removePartyPrompt(Scanner scanner, Waitlist waitlist) {
        if (waitlist.isEmpty()) {
            System.out.println("The waitlist is currently empty.");
            return;
        }

        System.out.print("Enter the name of the party to remove: ");
        String partyName = scanner.nextLine().trim();

        boolean removed = waitlist.removeParty(partyName);

        if (removed) {
            System.out.println("Party '" + partyName
                + "' was successfully removed from the waitlist.");
        }
        else {
            System.out.println("Party '" + partyName
                + "' was not found on the waitlist.");
        }
    }


    /**
     * Prompts the user for a party name and displays their current estimated
     * wait time
     * based on preceding parties seeking the same table type and current open
     * tables.
     *
     * @param scanner
     *            active Scanner object for console input
     * @param waitlist
     *            waitlist instance containing active parties
     * @param tableManager
     *            table manager tracking available tables
     */
    private static void checkWaitTimePrompt(
        Scanner scanner,
        Waitlist waitlist,
        TableManager tableManager) {
        if (waitlist.isEmpty()) {
            System.out.println("The waitlist is currently empty.");
            return;
        }

        System.out.print("Enter the name of the party to check wait time: ");
        String partyName = scanner.nextLine().trim();

        Party party = waitlist.getPartyByName(partyName);

        if (party != null) {
            int waitTime = waitlist.getWaitTime(party, tableManager);
            System.out.println("Party '" + party.getName() + "' (" + party
                .getTableType() + ") has an estimated wait time of " + waitTime
                + " minutes.");
        }
        else {
            System.out.println("Party '" + partyName
                + "' was not found on the waitlist.");
        }
    }


    /**
     * Creates and populates initial restaurant tables with separate booth,
     * window, and regular counts.
     *
     * @return initialized TableManager instance with populated tables
     */
    private static TableManager initializeRestaurantLayout() {
        TableManager manager = new TableManager();

        // Add Booth tables (IDs 1-5, capacity 4)
        for (int i = 1; i <= MAX_BOOTHS; i++) {
            manager.addTable(new BoothTable(i, 4));
        }

        // Add Window tables (IDs 6-10, capacity 6)
        for (int i = 6; i <= 5 + MAX_WINDOW_TABLES; i++) {
            manager.addTable(new WindowTable(i, 6));
        }

        // Add Regular tables (IDs 11-15, capacity 8)
        for (int i = 11; i <= 10 + MAX_REGULAR_TABLES; i++) {
            manager.addTable(new Table(i, 8));
        }

        return manager;
    }


    /**
     * Validates if arrival military time is within restaurant operating hours
     * (0800 to 2159).
     *
     * @param arrivalTime
     *            integer representation of 4-digit military time
     * @return true if time is valid and within range, false otherwise
     */
    private static boolean checkArrivalTimeValidity(int arrivalTime) {
        if (800 <= arrivalTime && arrivalTime < 2200) {
            int arrivalMinute = arrivalTime % 100;
            return arrivalMinute >= 0 && arrivalMinute < 60;
        }
        return false;
    }
}
