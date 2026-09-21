package mainClass;

/**
 * Represents a standard restaurant table. Serves as the base class
 * for specialized table types (e.g., BoothTable, WindowTable).
 *
 * @author Noah Bierman
 * @version 2026.09.21
 */
public class Table {
    // ~ Fields ................................................................

    private int tableId;
    private int capacity;
    private boolean occupied;

    // ~ Constructors ..........................................................

    /**
     * Constructs a Table with a unique ID and maximum seating capacity.
     * Tables are initialized as unoccupied (open).
     *
     * @param tableId
     *            unique identifier for the table
     * @param capacity
     *            maximum party size the table can accommodate
     */
    public Table(int tableId, int capacity) {
        this.tableId = tableId;
        this.capacity = capacity;
        this.occupied = false;
    }

    // ~ Getters & Setters .....................................................


    /**
     * Gets the table ID.
     *
     * @return table ID
     */
    public int getTableId() {
        return tableId;
    }


    /**
     * Gets the maximum seating capacity of the table.
     *
     * @return table capacity
     */
    public int getCapacity() {
        return capacity;
    }


    /**
     * Checks whether the table is currently occupied.
     *
     * @return true if occupied, false if open/available
     */
    public boolean isOccupied() {
        return occupied;
    }


    /**
     * Sets the occupied status of the table.
     *
     * @param occupied
     *            true to mark table occupied, false to clear/free table
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }


    /**
     * Retrieves the category type of the table.
     *
     * @return table type string ("regular")
     */
    public String getTableType() {
        return "regular";
    }
}
