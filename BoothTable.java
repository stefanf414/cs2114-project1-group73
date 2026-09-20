/**
 * Represents a booth table.
 *
 * @author Noah Bierman
 * @version 2026.09.20
 */

package mainClass;

public class BoothTable extends Table
{
    /**
     * Creates a booth table.
     *
     * @param tableNumber
     *            the table number
     * @param seats
     *            the number of seats
     */
    public BoothTable(int tableNumber, int seats)
    {
        super(tableNumber, seats);
    }


    /**
     * Gets the table type.
     *
     * @return Booth
     */
    public String getTableType()
    {
        return "Booth";
    }
}