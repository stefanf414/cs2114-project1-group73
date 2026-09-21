package mainClass;

import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * This class creates an ArrayList that holds all the parties in the waitlist
 * and calculates the wait time.
 *
 * @author Samuel
 * @version Sep 14, 2026
 */
// -------------------------------------------------------------------------
/**
 * Creates the class to hold and perform all methods with the waiting list.
 */
public class Waitlist
{
    /**
     * The list of parties currently waiting.
     */
    private ArrayList<Party> waitList = new ArrayList<>();


    // ----------------------------------------------------------
    /**
     * Adds a party to the waitlist.
     *
     * @param party
     *            party object needed to add to the waitlist
     */
    public void addParty(Party party)
    {
        if (party == null)
        {
            return;
        }

        int count = 0;

        for (int i = 0; i < waitList.size(); i++)
        {
            if (party.getName().equals(waitList.get(i).getName()))
            {
                count++;
            }
        }

        if (count > 0)
        {
            party.setName(party.getName() + count);
        }

        waitList.add(party);
    }


    // ----------------------------------------------------------
    /**
     * Removes a specific party from the waitlist by name.
     *
     * @param name
     *            name of the party
     * @return true if the removal was successful, false otherwise
     */
    public boolean removeParty(String name)
    {
        for (int i = 0; i < waitList.size(); i++)
        {
            if (waitList.get(i).getName().equals(name))
            {
                waitList.remove(i);
                return true;
            }
        }

        return false;
    }


    // ----------------------------------------------------------
    /**
     * Determines which party needs to be seated.
     *
     * @return the party up next to be seated, or null if the waitlist is empty
     */
    public Party seatNextParty()
    {
        if (waitList.isEmpty())
        {
            return null;
        }

        return waitList.remove(0);
    }


    // ----------------------------------------------------------
    /**
     * Finds a party in the waiting list.
     *
     * @param name
     *            name of the party
     * @return true if found, false if not
     */
    public boolean findParty(String name)
    {
        for (int i = 0; i < waitList.size(); i++)
        {
            if (waitList.get(i).getName().equals(name))
            {
                return true;
            }
        }

        return false;
    }


    // ----------------------------------------------------------
    /**
     * Finds the wait time for a given party.
     *
     * @param party
     *            the party needed to calculate wait time for
     * @return wait time in minutes
     */
    public int getWaitTime(Party party)
    {
        if (party == null)
        {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < waitList.size(); i++)
        {
            if (waitList.get(i).getName().equals(party.getName()))
            {
                return count * 45;
            }

            count++;
        }

        return 0;
    }


    // ----------------------------------------------------------
    /**
     * Prints the waitlist in order.
     */
    public void displayWaitlist()
    {
        if (waitList.isEmpty())
        {
            System.out.println("The waitlist is empty.");
            return;
        }

        for (int i = 0; i < waitList.size() - 1; i++)
        {
            System.out.print(waitList.get(i).getName() + " -> ");
        }

        System.out.println(
            waitList.get(waitList.size() - 1).getName());
    }


    // ----------------------------------------------------------
    /**
     * Checks if the waitlist is empty.
     *
     * @return true if empty, false if it contains elements
     */
    public boolean isEmpty()
    {
        return waitList.isEmpty();
    }


    // ----------------------------------------------------------
    /**
     * Gets the size of the waitlist.
     *
     * @return the size of the waitlist
     */
    public int size()
    {
        return waitList.size();
    }
}