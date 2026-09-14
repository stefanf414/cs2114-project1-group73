
// -------------------------------------------------------------------------
/**
 * This class creates an arrayList that holds all the parties in the waitlist
 * and calculates the wait time.
 * 
 * @author Samuel
 * @version Sep 14, 2026
 */
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * Creates the class to hold and perform all methods with the waiting list
 * 
 * @author Samuel
 * @version Sep 14, 2026
 */
public class Waitlist
{
    /**
     * 
     */
    // ~ Fields ................................................................
    ArrayList<Party> waitList = new ArrayList<>();

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param party
     *            party object needed to add to the waitlist.
     */
    // ~Public Methods ........................................................
    public void addParty(Party party)
    {
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
     * Will remove specific name in the waitlist.
     * 
     * @param name
     *            name of the party
     * @return returns true if the removal was sucessful, false if not
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
     * @return The party up next to be seated
     */
    public Party seatNextParty()
    {
        return waitList.remove(0);
    }


    // ----------------------------------------------------------
    /**
     * Finds party in the waiting list.
     * 
     * @param name
     *            Name of the party
     * @return true if found, false if not.
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
     * Finds the wait time for a given party
     * 
     * @param party
     *            The party needed to calculate wait time for
     * @return Wait time, 0 if there return 0. If there is at least 1 party in
     *             line, it is the amount of people ahead times 45 (minutes)
     */
    public int getWaitTime(Party party)
    {
        int count = 0;
        for (int i = 0; i < waitList.size(); i++)
        {
            if (waitList.get(i).getName().equals(party.getName()))
            {
                break;
            }
            count++;
        }
        if (count == 0)
        {
            return 0;
        }
        return count * 45;
    }


    // ----------------------------------------------------------
    /**
     * Prints the waitlist per each party
     */
    public void displayWaitlist()
    {
        for (int i = 0; i < waitList.size() - 1; i++)
        {
            System.out.print(waitList.get(i).getName() + " -> ");
        }
        System.out.println(waitList.get(waitList.size() - 1).getName());
    }


    // ----------------------------------------------------------
    /**
     * Checks if the waitList is empty.
     * 
     * @return true if empty, false if it contains elements
     */
    public boolean isEmpty()
    {
        if (waitList.size() == 0)
        {
            return true;
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * getter for size
     * 
     * @return the size of the waitlist.
     */
    public int size()
    {
        return waitList.size();
    }
}
