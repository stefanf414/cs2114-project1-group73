package mainClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the collection of all restaurant tables, tracking occupation status
 * and table availability by type and size capacity.
 *
 * @author Stefan Fernandez
 * @version 2026.09.21
 */
public class TableManager {
    // ~ Fields ................................................................

    /** Internal collection of all restaurant tables. */
    private List<Table> tables = new ArrayList<>();

    // ~ Public Methods ........................................................

    /**
     * Registers a new table in the system layout.
     *
     * @param table
     *            Table object to add
     */
    public void addTable(Table table) {
        if (table != null) {
            tables.add(table);
        }
    }


    /**
     * Assigns/occupies the first available table matching the required size and
     * table type preference.
     *
     * @param partySize
     *            amount of guests needing seating
     * @param type
     *            preferred table type ("booth", "window", "regular")
     * @return true if a table was found and marked occupied; false otherwise
     */
    public boolean assignTable(int partySize, String type) {
        for (Table table : tables) {
            if (!table.isOccupied() && table.getTableType().equalsIgnoreCase(
                type) && table.getCapacity() >= partySize) {
                table.setOccupied(true);
                return true;
            }
        }
        return false;
    }


    /**
     * Clears a specific occupied table by its ID number, making it available
     * again.
     *
     * @param tableId
     *            the ID of the table to clear
     * @return true if the table was found and cleared; false if it was already
     *         free or not found
     */
    public boolean clearTableById(int tableId) {
        for (Table table : tables) {
            if (table.getTableId() == tableId) {
                if (table.isOccupied()) {
                    table.setOccupied(false);
                    return true;
                }
                return false; // Table was already open
            }
        }
        return false; // Table ID not found
    }


    /**
     * Clears the first currently occupied table of the given type, making it
     * available again.
     *
     * @param type
     *            the type of table to clear ("booth", "window", "regular")
     * @return true if an occupied table of that type was found and cleared;
     *         false otherwise
     */
    public boolean clearFirstOccupiedByType(String type) {
        for (Table table : tables) {
            if (table.isOccupied() && table.getTableType().equalsIgnoreCase(
                type)) {
                table.setOccupied(false);
                return true;
            }
        }
        return false;
    }


    /**
     * Counts how many tables of a specific type are currently open
     * (unoccupied).
     *
     * @param type
     *            table type ("booth", "window", "regular")
     * @return count of available tables
     */
    public int getAvailableCountByType(String type) {
        int count = 0;
        for (Table table : tables) {
            if (!table.isOccupied() && table.getTableType().equalsIgnoreCase(
                type)) {
                count++;
            }
        }
        return count;
    }


    /**
     * Counts total registered tables of a specific type.
     *
     * @param type
     *            table type ("booth", "window", "regular")
     * @return total table count
     */
    public int getTotalCountByType(String type) {
        int count = 0;
        for (Table table : tables) {
            if (table.getTableType().equalsIgnoreCase(type)) {
                count++;
            }
        }
        return count;
    }


    /**
     * Gets total count of unoccupied tables across all types.
     *
     * @return total available tables
     */
    public int getAvailableTableCount() {
        int count = 0;
        for (Table table : tables) {
            if (!table.isOccupied()) {
                count++;
            }
        }
        return count;
    }


    /**
     * Gets total registered table count.
     *
     * @return total table count
     */
    public int getTotalTableCount() {
        return tables.size();
    }


    /**
     * Returns the full list of tables.
     *
     * @return list of Table objects
     */
    public List<Table> getTables() {
        return tables;
    }
}
