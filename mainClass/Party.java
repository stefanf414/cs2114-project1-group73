package mainClass;
// -------------------------------------------------------------------------
/**
 * This is the party class, where each restaurant party gives name, size, and
 * table preference
 * 
 * @author Samuel
 * @version Sep 14, 2026
 */
public class Party
{
    // ~ Fields ................................................................
    private String name;
    private int arrivalTime;
    private int size;

    // ----------------------------------------------------------
    /**
     * Create a new Party object.
     * 
     * @param name
     *            The name of the party
     * @param arrivalTime
     *            The arrival of the party (Military time)
     * @param size
     *            How many people are in the party
     */
    // ~ Constructors ..........................................................
    Party(String name, int arrivalTime, int size)
    {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.size = size;
    }


    // ----------------------------------------------------------
    /**
     * Getter for the party name
     * 
     * @return Name of the party
     */
    // ~Public Methods ........................................................
    public String getName()
    {
        return name;
    }


    // ----------------------------------------------------------
    /**
     * Setter for the party name
     * 
     * @param name
     *            New name for the party
     */
    public void setName(String name)
    {
        this.name = name;
    }


    // ----------------------------------------------------------
    /**
     * Getter for the Party's arrival time
     * 
     * @return arrival time of the party
     */
    public int getArrivalTime()
    {
        return arrivalTime;
    }


    // ----------------------------------------------------------
    /**
     * Setter for the arrival time
     * 
     * @param arrivalTime
     */
    public void setArrivalTime(int arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }


    // ----------------------------------------------------------
    /**
     * Getter for the int size
     * 
     * @return size of the group
     */
    public int getSize()
    {
        return size;
    }


    // ----------------------------------------------------------
    /**
     * Setter for the int size
     * 
     * @param size
     *            Size of the new group
     */
    public void setSize(int size)
    {
        this.size = size;
    }


    public String toString()
    {
        return "The party for " + getName() + " coming at  " + getArrivalTime()
            + " has " + getSize() + " people.";
    }
    

}
