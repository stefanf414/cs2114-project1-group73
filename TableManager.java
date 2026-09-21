package mainClass;

import java.util.ArrayList;

/**
 * Manages restaurant tables and provides separate count trackers and lookup
 * methods
 * for Booth, Window, and Regular table types.
 *
 * @author Stefan Fernandez
 * @version 2026.09.20
 */
public class TableManager {
    // ~ Fields ................................................................

    /** Collection of all tables registered in the restaurant layout. */
    private final ArrayList<Table> tables = new ArrayList<>();

    // ~ Public Methods ........................................................

    /**
     * Adds a table to the manager if it is non-null and not a duplicate table
     * number.
     *
     * @param table
     *            the Table instance to add
     */
    public void addTable(Table table) {
        if (table == null || findTableByNumber(table
            .getTableNumber()) != null) {
            return;
        }
        tables.add(table);
    }


    /**
     * Finds the smallest available table matching the requested type and party
     * size capacity.
     *
     * @param partySize
     *            number of guests in the party
     * @param tableType
     *            requested table type ("booth", "window", "regular")
     * @return best matching Table instance, or null if no appropriate table is
     *         available
     */
    public Table findAvailableTable(int partySize, String tableType) {
        if (partySize <= 0) {
            return null;
        }

        Table bestTable = null;
        for (Table table : tables) {
            boolean matchesType = matchesType(table, tableType);

            if (table.isAvailable() && matchesType && table
                .getSeats() >= partySize && (bestTable == null || table
                    .getSeats() < bestTable.getSeats())) {
                bestTable = table;
            }
        }
        return bestTable;
    }


    /**
     * Assigns and occupies an available table of the specified type for a
     * party.
     *
     * @param partySize
     *            number of guests
     * @param tableType
     *            requested table type ("booth", "window", "regular")
     * @return true if a table was successfully assigned, false otherwise
     */
    public boolean assignTable(int partySize, String tableType) {
        Table table = findAvailableTable(partySize, tableType);
        if (table == null) {
            return false;
        }
        table.occupy();
        return true;
    }


    /**
     * Marks an existing occupied table as available for future parties.
     *
     * @param tableNumber
     *            number ID of the table to release
     * @return true if successfully released, false if table was not found
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
     * Gets total count of open available tables of a specific table type.
     *
     * @param type
     *            table type ("booth", "window", "regular")
     * @return open table count for that specific type
     */
    public int getAvailableCountByType(String type) {
        int count = 0;
        for (Table table : tables) {
            if (table.isAvailable() && matchesType(table, type)) {
                count++;
            }
        }
        return count;
    }


    /**
     * Gets the total overall count of a specific table type in the restaurant.
     *
     * @param type
     *            table type ("booth", "window", "regular")
     * @return total table count for that specific type
     */
    public int getTotalCountByType(String type) {
        int count = 0;
        for (Table table : tables) {
            if (matchesType(table, type)) {
                count++;
            }
        }
        return count;
    }


    /**
     * Counts the total number of available tables across all table types.
     *
     * @return count of all currently available tables
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
     * Gets the total count of all tables regardless of type or occupancy
     * status.
     *
     * @return total table count
     */
    public int getTotalTableCount() {
        return tables.size();
    }

    // ~ Private Helper Methods ................................................


    /**
     * Maps user menu table type strings ("regular") to table implementation
     * labels ("Standard").
     *
     * @param table
     *            the table instance being checked
     * @param type
     *            the target table type string from user prompt
     * @return true if table matches the target type, false otherwise
     */
    private boolean matchesType(Table table, String type) {
        if (type.equalsIgnoreCase("regular")) {
            return table.getTableType().equalsIgnoreCase("Standard");
        }
        return table.getTableType().equalsIgnoreCase(type);
    }


    /**
     * Searches the restaurant tables array by table number ID.
     *
     * @param tableNumber
     *            unique integer identifier of the table
     * @return matching Table object, or null if not found
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
