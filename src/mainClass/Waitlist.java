package mainClass;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages the restaurant waitlist using an {@link ArrayList}. Parties are
 * automatically
 * ordered chronologically by arrival time. Wait times are calculated based on
 * parties
 * ahead in line requesting the same table type and current table availability.
 *
 * @author Samuel
 * @author Stefan Fernandez
 * @version 2026.09.20
 */
public class Waitlist {
    // ~ Fields ................................................................

    /**
     * The internal list maintaining all active parties currently waiting.
     */
    private ArrayList<Party> waitList = new ArrayList<>();

    // ~ Public Methods ........................................................

    /**
     * Adds a party to the waitlist and automatically re-sorts the list
     * chronologically by arrival time.
     * Appends duplicate counters to identical party names to avoid name
     * collisions.
     *
     * @param party
     *            the Party object to add
     */
    public void addParty(Party party) {
        if (party == null) {
            return;
        }

        // Check for duplicate names and append numerical suffix if necessary
        int count = 0;
        for (int i = 0; i < waitList.size(); i++) {
            if (party.getName().equalsIgnoreCase(waitList.get(i).getName())) {
                count++;
            }
        }

        if (count > 0) {
            party.setName(party.getName() + count);
        }

        waitList.add(party);

        // Maintain chronological order by arrival time
        Collections.sort(waitList);
    }


    /**
     * Removes a specific party from the waitlist by name.
     *
     * @param name
     *            the name of the party to remove
     * @return true if the party was found and removed, false otherwise
     */
    public boolean removeParty(String name) {
        for (int i = 0; i < waitList.size(); i++) {
            if (waitList.get(i).getName().equalsIgnoreCase(name)) {
                waitList.remove(i);
                return true;
            }
        }
        return false;
    }


    /**
     * Determines which party is next to be seated and removes them from the
     * front of the waitlist.
     *
     * @return the next Party to be seated, or null if the waitlist is empty
     */
    public Party seatNextParty() {
        if (waitList.isEmpty()) {
            return null;
        }
        return waitList.remove(0);
    }


    /**
     * Checks whether a party exists in the waitlist matching the given name.
     *
     * @param name
     *            the name of the party to search for
     * @return true if the party is found, false otherwise
     */
    public boolean findParty(String name) {
        for (int i = 0; i < waitList.size(); i++) {
            if (waitList.get(i).getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Retrieves a Party object from the waitlist by name without removing them.
     *
     * @param name
     *            the name of the party to search for
     * @return the matching Party object, or null if not found
     */
    public Party getPartyByName(String name) {
        for (Party party : waitList) {
            if (party.getName().equalsIgnoreCase(name)) {
                return party;
            }
        }
        return null;
    }


    /**
     * Calculates the estimated wait time for a given party in minutes.
     * Subtracts the number of currently open tables of the requested type from
     * preceding
     * parties in line before multiplying by turnover time (45 mins).
     *
     * @param party
     *            the party to calculate wait time for
     * @param tableManager
     *            table manager instance tracking table availability
     * @return estimated wait time in minutes (0 if an open table is available)
     */
    public int getWaitTime(Party party, TableManager tableManager) {
        if (party == null || tableManager == null) {
            return 0;
        }

        int precedingSameTypeCount = 0;

        for (int i = 0; i < waitList.size(); i++) {
            Party current = waitList.get(i);

            // Stop counting once target party is located in queue
            if (current.getName().equalsIgnoreCase(party.getName())) {
                break;
            }

            // Count preceding parties that want the same table type
            if (current.getTableType().equalsIgnoreCase(party.getTableType())) {
                precedingSameTypeCount++;
            }
        }

        // Get how many tables of this type are currently open
        int openTables = tableManager.getAvailableCountByType(party
            .getTableType());

        // Net waiting parties ahead = preceding parties minus currently open
        // tables
        int netWaitingParties = precedingSameTypeCount - openTables + 1;

        if (netWaitingParties <= 0) {
            return 0; // Open table available for immediate seating!
        }

        return netWaitingParties * 45;
    }


    /**
     * Displays the current waitlist in order of arrival time, including party
     * name,
     * arrival time, and table type preference.
     */
    public void displayWaitlist() {
        if (waitList.isEmpty()) {
            System.out.println("The waitlist is empty.");
            return;
        }

        for (int i = 0; i < waitList.size() - 1; i++) {
            Party p = waitList.get(i);
            System.out.print(p.getName() + " (" + p.getArrivalTime() + ", " + p
                .getTableType() + ") -> ");
        }

        Party last = waitList.get(waitList.size() - 1);
        System.out.println(last.getName() + " (" + last.getArrivalTime() + ", "
            + last.getTableType() + ")");
    }


    /**
     * Checks if the waitlist is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return waitList.isEmpty();
    }


    /**
     * Gets the number of parties currently on the waitlist.
     *
     * @return the waitlist size
     */
    public int size() {
        return waitList.size();
    }
}
