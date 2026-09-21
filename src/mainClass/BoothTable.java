package mainClass;

/**
 * Represents a booth-style table in the restaurant layout.
 * Inherits core functionality from {@link Table} and overrides table type
 * identification.
 *
 * @author Noah Bierman
 * @version 2026.09.21
 */
public class BoothTable extends Table {
    // ~ Constructors ..........................................................

    /**
     * Constructs a BoothTable instance with a unique ID and capacity limit.
     * Initializes as unoccupied by invoking the superclass constructor.
     *
     * @param tableId
     *            unique numeric identifier for this table
     * @param capacity
     *            maximum seating capacity of the booth
     */
    public BoothTable(int tableId, int capacity) {
        super(tableId, capacity);
    }

    // ~ Overridden Public Methods .............................................


    /**
     * Identifies the category type of this table subclass.
     *
     * @return the string literal "booth"
     */
    @Override
    public String getTableType() {
        return "booth";
    }
}
