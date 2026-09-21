/**
 * Represents a table in the restaurant.
 * A table has a table number, a number of seats,
 * and an availability status.
 *
 * @author Noah Bierman
 * @version 2026.09.20
 */

package mainClass; 

public class Table
{
    private int tableNumber;
    private int seats;
    private boolean available;


    /**
     * Creates a new table.
     *
     * @param tableNumber
     *            the table number
     * @param seats
     *            the number of seats at the table
     * @throws IllegalArgumentException
     *             if the table number or number of seats is not positive
     */
    public Table(int tableNumber, int seats)
    {
        if (tableNumber <= 0)
        {
            throw new IllegalArgumentException(
                "Table number must be greater than 0.");
        }

        if (seats <= 0)
        {
            throw new IllegalArgumentException(
                "Number of seats must be greater than 0.");
        }

        this.tableNumber = tableNumber;
        this.seats = seats;
        this.available = true;
    }


    /**
     * Gets the table number.
     *
     * @return the table number
     */
    public int getTableNumber()
    {
        return tableNumber;
    }


    /**
     * Gets the number of seats at the table.
     *
     * @return the number of seats
     */
    public int getSeats()
    {
        return seats;
    }


    /**
     * Determines whether the table is available.
     *
     * @return true if available, false otherwise
     */
    public boolean isAvailable()
    {
        return available;
    }


    /**
     * Marks the table as occupied.
     */
    public void occupy()
    {
        available = false;
    }


    /**
     * Marks the table as available.
     */
    public void makeAvailable()
    {
        available = true;
    }


    /**
     * Returns the type of table.
     * Subclasses can override this method.
     *
     * @return the table type
     */
    public String getTableType()
    {
        return "Standard";
    }


    /**
     * Returns a readable description of the table.
     *
     * @return table information
     */
    @Override
    public String toString()
    {
        String status;

        if (available)
        {
            status = "Available";
        }
        else
        {
            status = "Occupied";
        }

        return "Table " + tableNumber
            + " (" + getTableType()
            + ", " + seats + " seats)"
            + " - " + status;
    }
}