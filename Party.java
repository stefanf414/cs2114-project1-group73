package mainClass;

/**
 * Represents a restaurant party with a name, arrival time, size, and table type
 * preference.
 * Implements {@link Comparable} to allow sorting parties chronologically by
 * arrival time.
 *
 * @author Samuel
 * @author Noah Bierman
 * @version 2026.09.20
 */
public class Party implements Comparable<Party> {
    // ~ Fields ................................................................

    /** The name of the party making the reservation or on the waitlist. */
    private String name;

    /**
     * The arrival time of the party stored in 4-digit military time format
     * (e.g., 1830).
     */
    private int arrivalTime;

    /** The total number of guests in the party. */
    private int size;

    /**
     * The preferred table type for the party: "booth", "window", or "regular".
     */
    private String tableType;

    // ~ Constructors ..........................................................

    /**
     * Creates a new Party object with specified details and table preference.
     *
     * @param name
     *            the name of the party making the reservation
     * @param arrivalTime
     *            the arrival time in 4-digit military format (e.g., 1900)
     * @param size
     *            the number of guests in the party
     * @param tableType
     *            the preferred table type ("booth", "window", "regular")
     */
    public Party(String name, int arrivalTime, int size, String tableType) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.size = size;
        this.tableType = tableType.toLowerCase();
    }

    // ~ Public Methods ........................................................


    /**
     * Gets the party name.
     *
     * @return the party name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the party name.
     *
     * @param name
     *            the new party name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the party's arrival time.
     *
     * @return the arrival time in military time
     */
    public int getArrivalTime() {
        return arrivalTime;
    }


    /**
     * Sets the party's arrival time.
     *
     * @param arrivalTime
     *            the new arrival time in military time
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }


    /**
     * Gets the party size.
     *
     * @return the number of guests in the party
     */
    public int getSize() {
        return size;
    }


    /**
     * Sets the size of the party.
     *
     * @param size
     *            the new party size
     */
    public void setSize(int size) {
        this.size = size;
    }


    /**
     * Gets the party's table preference.
     *
     * @return table preference type
     */
    public String getTableType() {
        return tableType;
    }


    /**
     * Sets the party's table preference.
     *
     * @param tableType
     *            table preference type ("booth", "window", "regular")
     */
    public void setTableType(String tableType) {
        this.tableType = tableType.toLowerCase();
    }


    /**
     * Compares this party with another party based on arrival time.
     *
     * @param other
     *            the other party to compare to
     * @return a negative integer, zero, or a positive integer as this party's
     *         arrival time is less than, equal to, or greater than the
     *         specified party's
     */
    @Override
    public int compareTo(Party other) {
        return Integer.compare(this.arrivalTime, other.arrivalTime);
    }


    /**
     * Returns a string representation of the party.
     *
     * @return a formatted string with party details
     */
    @Override
    public String toString() {
        return "The party for " + getName() + " coming at " + getArrivalTime()
            + " has " + getSize() + " people (" + getTableType() + ").";
    }
}
