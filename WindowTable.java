/**
 * Represents a window table.
 *
 * @author Noah Bierman
 * @version 2026.09.20
 */

package mainClass;

public class WindowTable extends Table
{
    /**
     * Creates a window table.
     *
     * @param tableNumber
     *            the table number
     * @param seats
     *            the number of seats
     */
    public WindowTable(int tableNumber, int seats)
    {
        super(tableNumber, seats);
    }


    /**
     * Gets the table type.
     *
     * @return Window
     */
    public String getTableType()
    {
        return "Window";
    }
}