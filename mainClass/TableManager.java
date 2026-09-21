package mainClass;
import java.util.ArrayList;

/**
 * Manages the restaurant's collection of tables and their availability.
 * This class can add tables, find an appropriately sized available table,
 * assign tables to parties, release tables, and count available tables.
 *
 * @author Stefan Fernandez
 */
public class TableManager {
    private final ArrayList<Table> tables = new ArrayList<>();

    /**
     * Adds a table to the manager when it is non-null and its table number
     * does not already exist in the collection.
     *
     * @param table the table to add
     */
    public void addTable(Table table) {
        if (table == null || findTableByNumber(table
            .getTableNumber()) != null) {
            return;
        }
        tables.add(table);
    }


    /**
     * Finds the smallest available table that has enough seats for a party.
     *
     * @param partySize the number of guests in the party
     * @return the smallest available fitting table, or null when the
     *         party size is not positive or no table can fit the party
     */
    public Table findAvailableTable(int partySize) {
        if (partySize <= 0) {
            return null;
        }

        Table bestTable = null;
        for (Table table : tables) {
            if (table.isAvailable() && table.getSeats() >= partySize
                && (bestTable == null || table.getSeats() < bestTable
                    .getSeats())) {
                bestTable = table;
            }
        }
        return bestTable;
    }


    /**
     * Finds an available fitting table and marks it as occupied.
     *
     * @param partySize the number of guests in the party
     * @return true if a table was assigned; code false if no
     * available table can fit the party or the party size is invalid
     */
    public boolean assignTable(int partySize) {
        Table table = findAvailableTable(partySize);
        if (table == null) {
            return false;
        }
        table.occupy();
        return true;
    }


    /**
     * Marks an existing table as available.
     *
     * @param tableNumber the unique number of the table to release
     * @return true if the table exists and was released return, false 
     * if no table has the supplied number
     */
    public boolean releaseTable(int tableNumber) {
        Table table = findTableByNumber(tableNumber);
        if (table == null) {
            return false;
        }
        table.makeAvailable();
        return true;
    }


    /**
     * Counts the tables that are currently available.
     *
     * @return the number of available tables
     */
    public int getAvailableTableCount() {
        int count = 0;
        for (Table table : tables) {
            if (table.isAvailable()) {
                count++;
            }
        }
        return count;
    }


    /**
     * Searches for a table using its unique table number.
     *
     * @param tableNumber the number of the table to locate
     * @return the matching table, or null if no matching table exists
     */
    private Table findTableByNumber(int tableNumber) {
        for (Table table : tables) {
            if (table.getTableNumber() == tableNumber) {
                return table;
            }
        }
        return null;
    }
}